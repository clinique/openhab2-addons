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
package org.openhab.binding.sncf.internal.discovery;

import static org.openhab.binding.sncf.internal.SncfBindingConstants.*;

import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.config.discovery.AbstractDiscoveryService;
import org.eclipse.smarthome.config.discovery.DiscoveryResultBuilder;
import org.eclipse.smarthome.core.i18n.LocationProvider;
import org.eclipse.smarthome.core.library.types.PointType;
import org.eclipse.smarthome.core.thing.ThingUID;
import org.openhab.binding.sncf.internal.dto.PlaceNearby;
import org.openhab.binding.sncf.internal.handler.SncfBridgeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link SncfDiscoveryService} searches for available
 * station discoverable through API
 *
 * @author Gaël L'hopital - Initial contribution
 */
@NonNullByDefault
public class SncfDiscoveryService extends AbstractDiscoveryService {
    private static final int SEARCH_TIME = 2;
    private int searchRange = 2500;
    private final Logger logger = LoggerFactory.getLogger(SncfDiscoveryService.class);
    private final SncfBridgeHandler bridgeHandler;
    private final LocationProvider locationProvider;

    public SncfDiscoveryService(SncfBridgeHandler bridgeHandler, LocationProvider locationProvider) {
        super(SUPPORTED_THING_TYPES_UIDS, SEARCH_TIME);
        this.bridgeHandler = bridgeHandler;
        this.locationProvider = locationProvider;
    }

    @Override
    public void startScan() {
        PointType location = locationProvider.getLocation();
        if (location != null) {
            List<PlaceNearby> places = bridgeHandler.discoverNearby(location, searchRange);
            places.forEach(place -> {
                String thingId = place.getId().replace(":", "_").replace("-", "_").replace("stop_point_", "");
                thingDiscovered(DiscoveryResultBuilder.create(new ThingUID(STATION_THING_TYPE, thingId))
                        .withLabel(place.getName()).withBridge(bridgeHandler.getThing().getUID())
                        .withProperty(STOP_POINT_ID, place.getId()).build());
            });
            searchRange += 500;
        }
        stopScan();
    }
}
