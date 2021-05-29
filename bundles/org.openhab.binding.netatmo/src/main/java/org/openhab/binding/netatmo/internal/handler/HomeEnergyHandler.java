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
package org.openhab.binding.netatmo.internal.handler;

import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.CHANNEL_PLANNING;
import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.CHANNEL_SETPOINT_MODE;
import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.GROUP_HOME_ENERGY;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.netatmo.internal.NetatmoDescriptionProvider;
import org.openhab.binding.netatmo.internal.api.ApiBridge;
import org.openhab.binding.netatmo.internal.api.NetatmoConstants.SetpointMode;
import org.openhab.binding.netatmo.internal.api.NetatmoException;
import org.openhab.binding.netatmo.internal.api.dto.NAHome;
import org.openhab.binding.netatmo.internal.api.dto.NARoom;
import org.openhab.binding.netatmo.internal.api.dto.energy.Homestatus;
import org.openhab.binding.netatmo.internal.api.dto.energy.Room;
import org.openhab.binding.netatmo.internal.channelhelper.AbstractChannelHelper;
import org.openhab.core.i18n.TimeZoneProvider;
import org.openhab.core.thing.Bridge;
import org.openhab.core.thing.ChannelUID;
import org.openhab.core.types.Command;
import org.openhab.core.types.RefreshType;
import org.openhab.core.types.StateOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link HomeEnergyHandler} is the class used to handle the plug
 * device of a thermostat set
 *
 * @author Gaël L'hopital - Initial contribution
 *
 */
@NonNullByDefault
public class HomeEnergyHandler extends NetatmoDeviceHandler {

    private final Logger logger = LoggerFactory.getLogger(HomeEnergyHandler.class);

    private NAHome home = new NAHome();

    public HomeEnergyHandler(Bridge bridge, List<AbstractChannelHelper> channelHelpers, ApiBridge apiBridge,
            TimeZoneProvider timeZoneProvider, NetatmoDescriptionProvider descriptionProvider) {
        super(bridge, channelHelpers, apiBridge, timeZoneProvider, descriptionProvider);
    }

    public NAHome getHome() {
        return home;
    }

    @Override
    protected NAHome updateReadings() throws NetatmoException {
        home = apiBridge.getEnergyApi().getHomesData(config.id);
        Homestatus status = apiBridge.getEnergyApi().getHomeStatus(home.getId());
        for (Room room : status.getBody().getHome().getRooms()) {
            NARoom naRoom = home.getRoom(room.getId());
            naRoom.setAnticipating(room.getAnticipating());
            naRoom.setHeatingPowerRequest(room.getHeatingPowerRequest());
            naRoom.setThermMeasuredTemperature(room.getThermMeasuredTemperature());
            naRoom.setOpenWindow(room.getOpenWindow());
            SetpointMode localmode = SetpointMode.fromName(room.getThermSetpointMode());
            naRoom.setThermSetpointMode(localmode);
            naRoom.setThermSetpointStartTime(room.getThermSetpointStartTime());
            naRoom.setThermSetpointEndTime(room.getThermSetpointEndTime());
            naRoom.setThermSetpointTemperature(Double.valueOf(room.getThermSetpointTemperature()));
        }
        ChannelUID channelUID = new ChannelUID(getThing().getUID(), GROUP_HOME_ENERGY, CHANNEL_PLANNING);
        descriptionProvider.setStateOptions(channelUID, home.getThermSchedules().stream()
                .map(p -> new StateOption(p.getId(), p.getName())).collect(Collectors.toList()));
        return home;
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        if (command instanceof RefreshType) {
            super.handleCommand(channelUID, command);
        } else {
            String channelName = channelUID.getIdWithoutGroup();
            if (CHANNEL_PLANNING.equals(channelName)) {
                tryApiCall(() -> apiBridge.getEnergyApi().switchSchedule(config.id, command.toString()));
            } else if (channelName.equals(CHANNEL_SETPOINT_MODE)) {
                SetpointMode targetMode = SetpointMode.valueOf(command.toString());
                if (targetMode == SetpointMode.MANUAL) {
//                    updateState(channelUID, toStringType(currentData.getSetpointMode()));
                    logger.info("Switch to 'Manual' is done by setting a setpoint temp, command ignored");
                } else {
                    callSetThermMode(config.id, targetMode);
                }
            }

        }
    }

    public void callSetThermMode(String moduleId, SetpointMode targetMode) {
        tryApiCall(() -> apiBridge.getEnergyApi().setthermmode(config.id, targetMode.getDescriptor()));
    }

}
