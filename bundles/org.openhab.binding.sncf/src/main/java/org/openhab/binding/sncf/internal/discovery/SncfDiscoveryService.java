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

import java.util.Arrays;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.config.discovery.AbstractDiscoveryService;
import org.eclipse.smarthome.config.discovery.DiscoveryResultBuilder;
import org.eclipse.smarthome.core.i18n.LocationProvider;
import org.eclipse.smarthome.core.thing.ThingUID;
import org.openhab.binding.sncf.internal.handler.SncfBridgeHandler;
import org.openhab.binding.volvooncall.internal.VolvoOnCallException;
import org.openhab.binding.volvooncall.internal.dto.AccountVehicleRelation;
import org.openhab.binding.volvooncall.internal.dto.Vehicles;
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
        String[] relations = bridgeHandler.getVehiclesRelationsURL();
        Arrays.stream(relations).forEach(relationURL -> {
            try {
                AccountVehicleRelation accountVehicle = bridgeHandler.getURL(relationURL, AccountVehicleRelation.class);
                logger.debug("Found vehicle : {}", accountVehicle.vehicleId);

                Vehicles vehicle = bridgeHandler.getURL(accountVehicle.vehicleURL, Vehicles.class);
                Attributes attributes = bridgeHandler.getURL(Attributes.class, vehicle.vehicleId);

                thingDiscovered(
                        DiscoveryResultBuilder.create(new ThingUID(VEHICLE_THING_TYPE, accountVehicle.vehicleId))
                                .withLabel(attributes.vehicleType + " " + attributes.registrationNumber)
                                .withBridge(bridgeHandler.getThing().getUID()).withProperty(VIN, attributes.vin)
                                .withRepresentationProperty(accountVehicle.vehicleId).build());

            } catch (VolvoOnCallException e) {
                logger.warn("Error while discovering vehicle: {}", e.getMessage());
            }
        });

        stopScan();
    }
}
