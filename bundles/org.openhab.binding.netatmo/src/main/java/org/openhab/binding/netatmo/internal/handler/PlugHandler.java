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

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.netatmo.internal.NetatmoDescriptionProvider;
import org.openhab.binding.netatmo.internal.api.ApiBridge;
import org.openhab.binding.netatmo.internal.api.NetatmoException;
import org.openhab.binding.netatmo.internal.api.dto.NAPlug;
import org.openhab.binding.netatmo.internal.api.dto.NAThing;
import org.openhab.binding.netatmo.internal.channelhelper.AbstractChannelHelper;
import org.openhab.core.i18n.TimeZoneProvider;
import org.openhab.core.thing.Bridge;
import org.openhab.core.thing.Thing;
import org.openhab.core.thing.ThingStatus;

/**
 * {@link PlugHandler} is the class used to handle the plug
 * device of a thermostat set
 *
 * @author Gaël L'hopital - Initial contribution
 *
 */
@NonNullByDefault
public class PlugHandler extends NetatmoDeviceHandler {

    public PlugHandler(Bridge bridge, List<AbstractChannelHelper> channelHelpers, ApiBridge apiBridge,
            TimeZoneProvider timeZoneProvider, NetatmoDescriptionProvider descriptionProvider) {
        super(bridge, channelHelpers, apiBridge, timeZoneProvider, descriptionProvider);
    }

    public @NonNullByDefault({}) HomeEnergyHandler getHomeHandler() {
        Bridge bridge = getBridge();
        if (bridge != null && bridge.getStatus() == ThingStatus.ONLINE) {
            return (HomeEnergyHandler) bridge.getHandler();
        }
        return null;
    }

    @Override
    protected NAPlug updateReadings() throws NetatmoException {
        return (NAPlug) Objects.requireNonNullElse(getHomeHandler().getHome().getModule(config.id), new NAPlug());
    }

    @Override
    protected void updateProperties(NAThing naThing) {
        NAPlug plug = (NAPlug) naThing;
        int firmware = plug.getFirmware_revision();
        if (firmware != -1) {
            Map<String, String> properties = editProperties();
            properties.put(Thing.PROPERTY_FIRMWARE_VERSION, Integer.toString(firmware));
            updateProperties(properties);
        }
    }
}
