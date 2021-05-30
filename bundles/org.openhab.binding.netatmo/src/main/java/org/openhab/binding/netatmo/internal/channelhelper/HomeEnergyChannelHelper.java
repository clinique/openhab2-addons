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

import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.CHANNEL_PLANNING;
import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.CHANNEL_SETPOINT_DURATION;
import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.CHANNEL_SETPOINT_END_TIME;
import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.CHANNEL_SETPOINT_MODE;
import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.GROUP_HOME_ENERGY;
import static org.openhab.binding.netatmo.internal.utils.ChannelTypeUtils.toDateTimeType;
import static org.openhab.binding.netatmo.internal.utils.ChannelTypeUtils.toQuantityType;
import static org.openhab.binding.netatmo.internal.utils.ChannelTypeUtils.toStringType;
import static org.openhab.binding.netatmo.internal.utils.NetatmoCalendarUtils.getProgramBaseTime;
import static org.openhab.binding.netatmo.internal.utils.NetatmoCalendarUtils.getTimeDiff;

import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.netatmo.internal.api.NetatmoConstants.SetpointMode;
import org.openhab.binding.netatmo.internal.api.dto.NAHome;
import org.openhab.binding.netatmo.internal.api.dto.NAThermProgram;
import org.openhab.binding.netatmo.internal.api.dto.NAThing;
import org.openhab.binding.netatmo.internal.api.dto.NATimeTableItem;
import org.openhab.core.i18n.TimeZoneProvider;
import org.openhab.core.library.types.StringType;
import org.openhab.core.library.unit.Units;
import org.openhab.core.thing.Thing;
import org.openhab.core.types.State;
import org.openhab.core.types.UnDefType;


/**
 * The {@link HomeEnergyChannelHelper} handle specific behavior
 * of modules using batteries
 *
 * @author Gaël L'hopital - Initial contribution
 *
 */
@NonNullByDefault
public class HomeEnergyChannelHelper extends AbstractChannelHelper {

    public HomeEnergyChannelHelper(Thing thing, TimeZoneProvider timeZoneProvider) {
        super(thing, timeZoneProvider, GROUP_HOME_ENERGY);
    }

    @Override
    protected @Nullable State internalGetProperty(NAThing naThing, String channelId) {
        NAHome localThing = (NAHome) naThing;
        NAThermProgram currentProgram = localThing.getActiveProgram();
        SetpointMode thermMode = localThing.getThermMode();
 
        switch (channelId) {
            case CHANNEL_SETPOINT_DURATION:
                return toQuantityType(localThing.getThermSetpointDefaultDuration(), Units.MINUTE);
            case CHANNEL_PLANNING:
                return (currentProgram != null ? toStringType(currentProgram.getName()) : null);
            case CHANNEL_SETPOINT_MODE:
                if (thermMode != null) {
                    switch (thermMode) {
                        case PROGRAM:
                        case HOME:
                        case SCHEDULE:
                            NATimeTableItem currentProgramMode = getCurrentProgramMode(localThing.getActiveProgram());
                            if (currentProgram != null && currentProgramMode != null) {
                                return new StringType(currentProgram
                                        .getZone(String.valueOf(currentProgramMode.getZoneId())).getName());
                            }
                        case AWAY:
                        case MANUAL:
                        case FROST_GUARD:
                            return new StringType(thermMode.name());
                        case OFF:
                        case MAX:
                        case UNKNOWN:
                            return UnDefType.UNDEF;
                    }
                }
                return null;
            case CHANNEL_SETPOINT_END_TIME:
                if (thermMode != null) {
                    switch (thermMode) {
                        case PROGRAM:
                        case HOME:
                        case SCHEDULE:
                            return toDateTimeType(getNextProgramTime(localThing.getActiveProgram()),zoneId);
                        case AWAY:
                        case MANUAL:
                        case FROST_GUARD:
                            return (localThing.getThermModeEndTime() > 0 ? toDateTimeType(localThing.getThermModeEndTime(),zoneId) : UnDefType.UNDEF);
                        case OFF:
                        case MAX:
                        case UNKNOWN:
                            return UnDefType.UNDEF;
                    }
                }
                return null;
          }
        return null;
    }

    private @Nullable NATimeTableItem getCurrentProgramMode(@Nullable NAThermProgram activeProgram) {
        if (activeProgram != null) {
            long diff = getTimeDiff();
            return activeProgram.getTimetable().stream().filter(t -> t.getMOffset() < diff)
                    .reduce((first, second) -> second).orElse(null);
        }
        return null;
    }

    private long getNextProgramTime(@Nullable NAThermProgram activeProgram) {
        long diff = getTimeDiff();
        if (activeProgram != null) {
            // By default we'll use the first slot of next week - this case will be true if
            // we are in the last schedule of the week so below loop will not exit by break
            List<NATimeTableItem> timetable = activeProgram.getTimetable();
            int next = timetable.get(0).getMOffset() + (7 * 24 * 60);
            for (NATimeTableItem timeTable : timetable) {
                if (timeTable.getMOffset() > diff) {
                    next = timeTable.getMOffset();
                    break;
                }
            }
            return next * 60 + getProgramBaseTime();
        }
        return -1;
    }
}
