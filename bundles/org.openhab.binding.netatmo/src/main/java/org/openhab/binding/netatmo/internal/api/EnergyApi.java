/**
 * Copyright (c) 2010-2021 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.netatmo.internal.api;

import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.netatmo.internal.api.NetatmoConstants.SetpointMode;
import org.openhab.binding.netatmo.internal.api.dto.NADeviceDataBody;
import org.openhab.binding.netatmo.internal.api.dto.NAHome;
import org.openhab.binding.netatmo.internal.api.dto.NAHomeData;
import org.openhab.binding.netatmo.internal.api.dto.NAPlug;
import org.openhab.binding.netatmo.internal.api.dto.energy.Homestatus;

/**
 *
 * @author Gaël L'hopital - Initial contribution
 *
 */

@NonNullByDefault
public class EnergyApi extends RestManager {
    public static final String URL_HOMESTATUS = "homestatus";
    public static final String URL_HOMESDATA = "homesdata";

    public static final String URL_THERMOSTAT = "getthermostatsdata";

    private class NAThermostatDataResponse extends ApiResponse<NADeviceDataBody<NAPlug>> {
    }

    public EnergyApi(ApiBridge apiClient) {
        super(apiClient, NetatmoConstants.ALL_SCOPES);
    }

    public class NAHomesDataResponse extends ApiResponse<NAHomeData> {
    }

    public List<NAHome> getHomeList(@Nullable ModuleType type) throws NetatmoException {
        String req = URL_HOMESDATA;
        if (type != null) {
            req += "?gateway_types=" + type.name();
        }
        NAHomesDataResponse response = get(req, NAHomesDataResponse.class);
        return response.getBody().getHomes();
    }

    public NAHome getHomesData(String homeId) throws NetatmoException {
        String req = URL_HOMESDATA + "?home_id=" + homeId + "&gateway_types=NAPlug";
        NAHomesDataResponse response = get(req, NAHomesDataResponse.class);
        return response.getBody().getHomes().get(0);
    }

    public Homestatus getHomeStatus(String homeId) throws NetatmoException {
        String req = URL_HOMESTATUS + "?home_id=" + homeId + "&device_types=NAPlug&device_types=NATherm&device_types=NRV";
        Homestatus response = get(req, Homestatus.class);
        return response;
    }

    // -- deprecated 
    private NAThermostatDataResponse getThermostatsData(@Nullable String equipmentId) throws NetatmoException {
        String req = URL_THERMOSTAT;
        if (equipmentId != null) {
            req += "?device_id=" + equipmentId;
        }
        return get(req, NAThermostatDataResponse.class);
    }

    public NADeviceDataBody<NAPlug> getThermostatsDataBody(@Nullable String equipmentId) throws NetatmoException {
        return getThermostatsData(equipmentId).getBody();
    }

    public NAPlug getThermostatData(String equipmentId) throws NetatmoException {
        NADeviceDataBody<NAPlug> answer = getThermostatsData(equipmentId).getBody();
        NAPlug plug = answer.getDevice(equipmentId);
        if (plug != null) {
            return plug;
        }
        throw new NetatmoException(String.format("Unexpected answer searching device '%s' : not found.", equipmentId));
    }

    /**
     *
     * The method switchschedule switches the home&#x27;s schedule to another existing schedule.
     *
     * @param homeId The id of home (required)
     * @param scheduleId The schedule id. It can be found in the getthermstate response, under the keys
     *            therm_program_backup and therm_program. (required)
     * @return boolean success
     * @throws NetatmoException If fail to call the API, e.g. server error or cannot deserialize the
     *             response body
     */
    public boolean switchSchedule(String homeId, String scheduleId) throws NetatmoException {
        String req = "switchschedule";
        String payload = String.format("{\"home_id\":\"%s\",\"schedule_id\":\"%s\"}", homeId, scheduleId);
        ApiOkResponse response = post(req, payload, ApiOkResponse.class, false);
        if (!response.isSuccess()) {
            throw new NetatmoException(String.format("Unsuccessfull schedule change : %s", response.getStatus()));
        }
        return true;
    }

    /**
     *
     * This endpoint permits to control the heating of a specific home. A home can be set in 3 differents modes:
     *  "schedule" mode in which the home will follow the user schedule
     *  "away" mode which will put the whole house to away (default is 12° but can be changed by the user in its settings)
     *   "hg" corresponds to frostguard mode (7° by default)
     *
     * @param homeId The id of home (required)
     * @param mode The mode. (required)
     * @return boolean success
     * @throws NetatmoException If fail to call the API, e.g. server error or cannot deserialize the
     *             response body
     */
    public boolean setthermmode(String homeId, String mode) throws NetatmoException {
        String req = "setthermmode";
        String payload = String.format("{\"home_id\":\"%s\",\"mode\":\"%s\"}", homeId, mode);
        ApiOkResponse response = post(req, payload, ApiOkResponse.class, false);
        if (!response.isSuccess()) {
            throw new NetatmoException(String.format("Unsuccessfull schedule change : %s", response.getStatus()));
        }
        return true;
    }
    /**
     *
     * The method setroomthermpoint changes the Thermostat manual temperature setpoint.
     *
     * @param homeId The id of home (required)
     * @param roomId The id of the room (required)
     * @param mode The mode. (required)
     * @param endtime When using the manual or max setpoint_mode, this parameter defines when the setpoint
     *            expires. (optional)
     * @param temp When using the manual setpoint_mode, this parameter defines the temperature setpoint (in
     *            Celcius) to use. (optional)
     * @return ApiOkResponse
     * @throws NetatmoCommunicationException If fail to call the API, e.g. server error or cannot deserialize the
     *             response body
     */
    public boolean setroomthermpoint(String homeId, String roomId, SetpointMode mode, long endtime,
            double temp) throws NetatmoException {
        String req = "setroomthermpoint?home_id=%s&room_id=%s&mode=%s";
        req = String.format(req, homeId, roomId, mode.getDescriptor());
        if (mode == SetpointMode.MANUAL || mode == SetpointMode.MAX) {
            req += "&endtime=" + endtime;
            if (mode == SetpointMode.MANUAL) {
                req += "&temp=" + temp;
            }
        }

        ApiOkResponse response = post(req, null, ApiOkResponse.class, true);
        if (!response.isSuccess()) {
            throw new NetatmoException(String.format("Unsuccessfull setpoint change : %s", response.getStatus()));
        }
        return true;
    }
}
