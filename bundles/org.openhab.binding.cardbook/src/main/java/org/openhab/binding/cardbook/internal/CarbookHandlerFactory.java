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

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory;
import org.openhab.binding.cardbook.internal.handler.CardDAVHandler;
import org.openhab.binding.cardbook.internal.handler.DirectoryHandler;
import org.openhab.io.transport.webdav.WebDAVManager;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The {@link CarbookHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Gaël L'hopital - Initial contribution
 */
@NonNullByDefault
@Component(service = ThingHandlerFactory.class, configurationPid = "binding.cardbook")
public class CarbookHandlerFactory extends BaseThingHandlerFactory {
    private @NonNullByDefault({}) WebDAVManager webDAVManager;

    // List of all supported things
    public static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Stream
            .of(CardbookBindingConstants.THING_TYPE_CARDDAV, CardbookBindingConstants.THING_TYPE_DIRECTORY)
            .collect(Collectors.toSet());

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected @Nullable ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (thingTypeUID.equals(CardbookBindingConstants.THING_TYPE_CARDDAV)) {
            return new CardDAVHandler(thing, webDAVManager);
        } else if (thingTypeUID.equals(CardbookBindingConstants.THING_TYPE_DIRECTORY)) {
            return new DirectoryHandler(thing);
        }
        return null;
    }

    @Reference
    protected void setWebDAVManager(WebDAVManager webDAVManager) {
        this.webDAVManager = webDAVManager;
    }

    protected void unsetWebDAVManager(WebDAVManager webDAVManager) {
        this.webDAVManager = null;
    }
}
