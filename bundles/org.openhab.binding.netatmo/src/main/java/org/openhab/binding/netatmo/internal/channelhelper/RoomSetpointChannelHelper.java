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
package org.openhab.binding.netatmo.internal.channelhelper;

import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.CHANNEL_SETPOINT_END_TIME;
import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.CHANNEL_SETPOINT_MODE;
import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.CHANNEL_SETPOINT_START_TIME;
import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.CHANNEL_VALUE;
import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.GROUP_TH_SETPOINT;
import static org.openhab.binding.netatmo.internal.utils.ChannelTypeUtils.toDateTimeType;
import static org.openhab.binding.netatmo.internal.utils.ChannelTypeUtils.toQuantityType;
import static org.openhab.binding.netatmo.internal.utils.NetatmoCalendarUtils.getTimeDiff;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.netatmo.internal.api.NetatmoConstants.MeasureClass;
import org.openhab.binding.netatmo.internal.api.NetatmoConstants.SetpointMode;
import org.openhab.binding.netatmo.internal.api.dto.NARoom;
import org.openhab.binding.netatmo.internal.api.dto.NAThermProgram;
import org.openhab.binding.netatmo.internal.api.dto.NAThing;
import org.openhab.binding.netatmo.internal.api.dto.NATimeTableItem;
import org.openhab.core.i18n.TimeZoneProvider;
import org.openhab.core.library.types.StringType;
import org.openhab.core.thing.Thing;
import org.openhab.core.types.State;
import org.openhab.core.types.UnDefType;

/**
 * The {@link RoomSetpointChannelHelper} handle specific behavior
 * of the thermostat module
 *
 * @author Gaël L'hopital - Initial contribution
 *
 */
@NonNullByDefault
public class RoomSetpointChannelHelper extends AbstractChannelHelper {

    public RoomSetpointChannelHelper(Thing thing, TimeZoneProvider timeZoneProvider) {
        super(thing, timeZoneProvider, GROUP_TH_SETPOINT);
    }

    @Override
    protected @Nullable State internalGetProperty(NAThing naThing, String channelId) {
        NARoom room = (NARoom) naThing;
        switch (channelId) {
            case CHANNEL_VALUE:
                return getCurrentSetpoint(room);
            case CHANNEL_SETPOINT_MODE:
                return new StringType(room.getThermSetpointMode().name());
            case CHANNEL_SETPOINT_START_TIME:
                return toDateTimeType(room.getThermSetpointStartTime(), zoneId);    
            case CHANNEL_SETPOINT_END_TIME:
                return (room.getThermSetpointEndTime() > 0 ? toDateTimeType(room.getThermSetpointEndTime(), zoneId) : null);    
        }
        return null;
    }

    private State getCurrentSetpoint(NARoom room) {
        SetpointMode thermSetPointMode = room.getThermSetpointMode();
        if (thermSetPointMode != null) {
//            SetpointMode currentMode = SetpointMode.valueOf(thermSetPoint.toUpperCase());
            // NAThermProgram currentProgram = room.getActiveProgram();
            switch (thermSetPointMode) {
                case AWAY:
                case MANUAL:
                case SCHEDULE:
                case FROST_GUARD:
                case PROGRAM:
                    return toQuantityType(room.getThermSetpointTemperature(), MeasureClass.INTERIOR_TEMPERATURE);
                case OFF:
                case MAX:
                case UNKNOWN:
                    return UnDefType.UNDEF;
            }
        }
        return UnDefType.NULL;
    }

    private @Nullable NATimeTableItem getCurrentProgramMode(@Nullable NAThermProgram activeProgram) {
        if (activeProgram != null) {
            long diff = getTimeDiff();
            return activeProgram.getTimetable().stream().filter(t -> t.getMOffset() < diff)
                    .reduce((first, second) -> second).orElse(null);
        }
        return null;
    }
}
