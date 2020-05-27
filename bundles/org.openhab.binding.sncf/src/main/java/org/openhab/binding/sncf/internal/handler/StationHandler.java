/**
 * Copyright (c) 2010-2020 Contributors to the openHAB project
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
package org.openhab.binding.sncf.internal.handler;

import static org.openhab.binding.sncf.internal.SncfBindingConstants.*;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.library.types.DateTimeType;
import org.eclipse.smarthome.core.library.types.StringType;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.Channel;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.thing.binding.BridgeHandler;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.types.RefreshType;
import org.eclipse.smarthome.core.types.State;
import org.eclipse.smarthome.core.types.UnDefType;
import org.openhab.binding.sncf.internal.config.StationConfiguration;
import org.openhab.binding.sncf.internal.dto.Passage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link StationHandler} is responsible for handling commands, which are sent
 * to one of the channels.
 *
 * @author Gaël L'hopital - Initial contribution
 */
@NonNullByDefault
public class StationHandler extends BaseThingHandler {
    private final Logger logger = LoggerFactory.getLogger(StationHandler.class);
    private @Nullable ScheduledFuture<?> refreshJob;
    private @NonNullByDefault({}) StationConfiguration configuration;

    public StationHandler(Thing thing) {
        super(thing);
    }

    @Override
    public void initialize() {
        logger.trace("Initializing the Station handler for {}", getThing().getUID());

        SncfBridgeHandler bridgeHandler = getBridgeHandler();
        if (bridgeHandler != null) {
            configuration = getConfigAs(StationConfiguration.class);

            updateStatus(ThingStatus.ONLINE);
            refreshJob = scheduler.schedule(this::queryApiAndUpdateChannels, 10, TimeUnit.SECONDS);
        }
    }

    private void queryApiAndUpdateChannels() {
        SncfBridgeHandler bridgeHandler = getBridgeHandler();
        if (bridgeHandler != null) {
            ZonedDateTime nextEvent = null;
            List<Passage> departures = bridgeHandler.getDepartures(configuration.stopPointId);
            if (departures.size() > 0) {
                getThing().getChannels().stream().map(Channel::getUID)
                        .filter(channelUID -> isLinked(channelUID) && GROUP_DEPARTURE.equals(channelUID.getGroupId()))
                        .forEach(channelUID -> {
                            State state = getValue(channelUID.getIdWithoutGroup(), departures.get(0));
                            updateState(channelUID, state);
                        });
                nextEvent = departures.get(0).getStopDateTime().getDepartureDateTime();
            }
            ZonedDateTime nextArrival = null;
            List<Passage> arrivals = bridgeHandler.getArrivals(configuration.stopPointId);
            if (arrivals.size() > 0) {
                getThing().getChannels().stream().map(Channel::getUID)
                        .filter(channelUID -> isLinked(channelUID) && GROUP_ARRIVAL.equals(channelUID.getGroupId()))
                        .forEach(channelUID -> {
                            State state = getValue(channelUID.getIdWithoutGroup(), arrivals.get(0));
                            updateState(channelUID, state);
                        });
                nextArrival = arrivals.get(0).getStopDateTime().getArrivalDateTime();
                if (nextArrival.isBefore(nextEvent)) {
                    nextEvent = nextArrival;
                }
            }
            updateStatus(ThingStatus.ONLINE);

            Duration untilNextEvent = Duration.between(ZonedDateTime.now(),
                    nextEvent != null ? nextEvent : ZonedDateTime.now().plusMinutes(10));
            freeRefreshJob();
            refreshJob = scheduler.schedule(this::queryApiAndUpdateChannels, untilNextEvent.getSeconds(),
                    TimeUnit.SECONDS);
            /*
             * try {
             * } catch (SncfException e) {
             * logger.warn("Exception occurred during execution: {}", e.getMessage(), e);
             * updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR, e.getMessage());
             * freeRefreshJob();
             * startAutomaticRefresh(5);
             * }
             */
        }
    }

    private State getValue(String channelId, Passage passage) {
        switch (channelId) {
            case DIRECTION:
                return new StringType(passage.getRoute().getDirection().getName());
            case CODE:
                return new StringType(passage.getDisplayInformations().getCode());
            case COMMERCIAL_MODE:
                return new StringType(passage.getDisplayInformations().getCommercialMode());
            case NAME:
                return new StringType(passage.getDisplayInformations().getName());
            case NETWORK:
                return new StringType(passage.getDisplayInformations().getNetwork());
            case ARRIVAL:
                return new DateTimeType(passage.getStopDateTime().getArrivalDateTime());
            case DEPARTURE:
                return new DateTimeType(passage.getStopDateTime().getDepartureDateTime());
        }
        return UnDefType.NULL;
    }

    private void freeRefreshJob() {
        ScheduledFuture<?> refreshJob = this.refreshJob;
        if (refreshJob != null) {
            refreshJob.cancel(true);
            this.refreshJob = null;
        }
    }

    @Override
    public void dispose() {
        freeRefreshJob();
        super.dispose();
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        if (command instanceof RefreshType) {
            queryApiAndUpdateChannels();
        }
    }

    private @Nullable SncfBridgeHandler getBridgeHandler() {
        Bridge bridge = getBridge();
        if (bridge != null) {
            BridgeHandler handler = bridge.getHandler();
            if (handler != null) {
                return (SncfBridgeHandler) handler;
            }
        }
        updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.BRIDGE_UNINITIALIZED);
        return null;
    }

}
