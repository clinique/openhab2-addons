/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
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
package org.openhab.binding.cardbook.internal;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * The {@link CardbookBindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Gaël L'hopital - Initial contribution
 */
@NonNullByDefault
public class CardbookBindingConstants {
    public static final String BINDING_ID = "cardbook";
    public static final ThingTypeUID THING_TYPE_CARDDAV = new ThingTypeUID(BINDING_ID, "carddav");
    public static final ThingTypeUID THING_TYPE_DIRECTORY = new ThingTypeUID(BINDING_ID, "directory");
}
