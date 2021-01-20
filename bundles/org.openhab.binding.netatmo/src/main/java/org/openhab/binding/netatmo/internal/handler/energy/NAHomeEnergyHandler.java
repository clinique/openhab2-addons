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
package org.openhab.binding.netatmo.internal.handler.energy;

import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.*;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.netatmo.internal.api.ApiBridge;
import org.openhab.binding.netatmo.internal.api.NetatmoException;
import org.openhab.binding.netatmo.internal.api.home.HomeApi;
import org.openhab.binding.netatmo.internal.api.home.NAHome;
import org.openhab.binding.netatmo.internal.channelhelper.AbstractChannelHelper;
import org.openhab.binding.netatmo.internal.handler.NetatmoDeviceHandler;
import org.openhab.core.i18n.TimeZoneProvider;
import org.openhab.core.thing.Bridge;
import org.openhab.core.thing.ChannelUID;
import org.openhab.core.types.Command;
import org.openhab.core.types.RefreshType;
import org.openhab.core.types.StateOption;

/**
 * {@link NAHomeEnergyHandler} is the class used to handle the plug
 * device of a thermostat set
 *
 * @author Gaël L'hopital - Initial contribution OH2 version
 *
 */
@NonNullByDefault
public class NAHomeEnergyHandler extends NetatmoDeviceHandler {

    private @Nullable NAPlanningDescriptionProvider descriptionProvider;
    private int setpointDefaultDuration;
    private final HomeApi api;

    public NAHomeEnergyHandler(Bridge bridge, List<AbstractChannelHelper> channelHelpers, ApiBridge apiBridge,
            TimeZoneProvider timeZoneProvider) {
        super(bridge, channelHelpers, apiBridge, timeZoneProvider);
        this.api = apiBridge.getHomeApi();
    }

    public void setStateDescriptionProvider(NAPlanningDescriptionProvider descriptionProvider) {
        this.descriptionProvider = descriptionProvider;
    }

    @Override
    protected NAHome updateReadings() throws NetatmoException {
        NAHome home = api.getHomeData(config.id);
        ChannelUID channelUID = new ChannelUID(getThing().getUID(), GROUP_HOME_ENERGY, CHANNEL_PLANNING);
        if (descriptionProvider != null) {
            descriptionProvider.setStateOptions(channelUID, home.getThermSchedules().stream()
                    .map(p -> new StateOption(p.getId(), p.getName())).collect(Collectors.toList()));
        }
        setpointDefaultDuration = home.getThermSetpointDefaultDuration();
        return home;
    }

    public int getSetpointDefaultDuration() {
        return setpointDefaultDuration;
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        if (command instanceof RefreshType) {
            super.handleCommand(channelUID, command);
        } else {
            String channelName = channelUID.getIdWithoutGroup();
            if (CHANNEL_PLANNING.equals(channelName)) {
                tryApiCall(() -> api.switchSchedule(config.id, command.toString()));
            }
            // TODO : did not find how to make this work
            // else if (CHANNEL_SETPOINT_DURATION.equals(channelName)) {
            // QuantityType<?> quantity = commandToQuantity(command, Units.MINUTE);
            // if (quantity != null) {
            // tryApiCall(() -> homeApi.changeSetpointDefaultDuration(config.id, quantity.intValue()));
            // } else {
            // logger.warn("Incorrect value '{}' on channel '{}'", command, channelName);
            // }
            // }
        }
    }
}