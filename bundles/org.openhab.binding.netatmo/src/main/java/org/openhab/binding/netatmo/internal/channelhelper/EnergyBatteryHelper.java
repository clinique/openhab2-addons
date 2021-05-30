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

import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.CHANNEL_BATTERY_STATUS;
import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.GROUP_ENERGY_BATTERY;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.netatmo.internal.api.dto.NAEnergyModule;
import org.openhab.binding.netatmo.internal.api.dto.NAThing;
import org.openhab.core.i18n.TimeZoneProvider;
import org.openhab.core.library.types.StringType;
import org.openhab.core.thing.Thing;
import org.openhab.core.types.State;

/**
 * The {@link EnergyBatteryHelper} handle specific behavior
 * of modules using batteries
 *
 * @author Markus Dillmann - Initial contribution
 *
 */
@NonNullByDefault
public class EnergyBatteryHelper extends AbstractChannelHelper {

    public EnergyBatteryHelper(Thing thing, TimeZoneProvider timeZoneProvider) {
        super(thing, timeZoneProvider, GROUP_ENERGY_BATTERY);
    }

    @Override
    protected @Nullable State internalGetProperty(NAThing naThing, String channelId) {
        if (naThing instanceof NAEnergyModule) {
            NAEnergyModule module = (NAEnergyModule) naThing;
            String status = module.getBatteryState();
            if (CHANNEL_BATTERY_STATUS.equals(channelId)) {
                return new StringType(status);
            }
        }
        return null;
    }
}
