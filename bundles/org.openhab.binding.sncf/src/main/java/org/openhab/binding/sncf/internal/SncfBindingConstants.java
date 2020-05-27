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
package org.openhab.binding.sncf.internal;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * The {@link SncfBindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Gaël L'hopital - Initial contribution
 */
@NonNullByDefault
public class SncfBindingConstants {

    public static final String BINDING_ID = "sncf";

    // Station properties
    public static final String STOP_POINT_ID = "stopPointId";

    // The URL to use to connect to VocAPI with.
    public static final String SERVICE_URL = "https://api.sncf.com/v1/coverage/sncf/";

    // List of Channel groups
    public static final String GROUP_ARRIVAL = "arrival";
    public static final String GROUP_DEPARTURE = "departure";

    // List of Channel id's
    public static final String DIRECTION = "direction";
    public static final String CODE = "code";
    public static final String COMMERCIAL_MODE = "commercialMode";
    public static final String NAME = "name";
    public static final String NETWORK = "network";
    public static final String PHYSICAL_MODE = "physicalMode";
    public static final String ARRIVAL = "arrival";
    public static final String DEPARTURE = "departure";

    // The JSON content type used when talking to VocAPI.
    public static final String JSON_CONTENT_TYPE = "application/json";

    // List of Thing Type UIDs
    public static final ThingTypeUID APIBRIDGE_THING_TYPE = new ThingTypeUID(BINDING_ID, "api");
    public static final ThingTypeUID STATION_THING_TYPE = new ThingTypeUID(BINDING_ID, "station");

    // List of all adressable things in OH = SUPPORTED_DEVICE_THING_TYPES_UIDS + the virtual bridge
    public static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Stream
            .of(APIBRIDGE_THING_TYPE, STATION_THING_TYPE).collect(Collectors.toSet());

    // Default value for undefined integers
    public static final int UNDEFINED = -1;
}
