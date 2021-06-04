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

import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.*;
import static org.openhab.binding.netatmo.internal.utils.ChannelTypeUtils.toStringType;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.netatmo.internal.api.dto.NAModule;
import org.openhab.binding.netatmo.internal.api.dto.NAThing;
import org.openhab.core.i18n.TimeZoneProvider;
import org.openhab.core.thing.Thing;
import org.openhab.core.types.State;

/**
 * The {@link BatteryEnergyHelper} handle specific behavior
 * of energy modules which only supply a battery state 
 * 
 * should be combined later with the standard helpers but for now helpers only accept a single group
 *
 * @author Markus Dillmann - Initial contribution
 *
 */
@NonNullByDefault
public class BatteryEnergyHelper extends AbstractChannelHelper {

    public BatteryEnergyHelper(Thing thing, TimeZoneProvider timeZoneProvider) {
        super(thing, timeZoneProvider, GROUP_ENERGY_BATTERY);
    }

    @Override
    protected @Nullable State internalGetProperty(NAThing naThing, String channelId) {
        if (naThing instanceof NAModule) {
            NAModule module = (NAModule) naThing;
            if (CHANNEL_BATTERY_STATUS.equals(channelId)) {
                String status = module.getBatteryState();
                return toStringType(status);
            }
        }
        return null;
    }
}
