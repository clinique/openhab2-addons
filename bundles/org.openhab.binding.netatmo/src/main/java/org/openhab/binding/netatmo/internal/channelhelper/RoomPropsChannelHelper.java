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

import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.CHANNEL_ROOM_ANTICIPATING;
import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.CHANNEL_ROOM_HEATING_POWER;
import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.CHANNEL_ROOM_WINDOW_OPEN;
import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.GROUP_ROOM_PROPERTIES;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.netatmo.internal.api.dto.NARoom;
import org.openhab.binding.netatmo.internal.api.dto.NAThing;
import org.openhab.core.i18n.TimeZoneProvider;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.thing.Thing;
import org.openhab.core.types.State;

/**
 * The {@link RoomPropsChannelHelper} handle specific behavior
 * of the room
 *
 * @author Markus Dillmann - Initial contribution
 *
 */
@NonNullByDefault
public class RoomPropsChannelHelper extends AbstractChannelHelper {

    public RoomPropsChannelHelper(Thing thing, TimeZoneProvider timeZoneProvider) {
        super(thing, timeZoneProvider, GROUP_ROOM_PROPERTIES);
    }

    @Override
    protected @Nullable State internalGetProperty(NAThing naThing, String channelId) {
        NARoom room = (NARoom) naThing;
        switch (channelId) {
            case CHANNEL_ROOM_WINDOW_OPEN:
                return OnOffType.from(room.isOpenWindow());
            case CHANNEL_ROOM_ANTICIPATING:
                return OnOffType.from(room.isAnticipating());
            case CHANNEL_ROOM_HEATING_POWER:
                return new DecimalType(room.getHeatingPowerRequest());    
        }
        return null;
    }

}
