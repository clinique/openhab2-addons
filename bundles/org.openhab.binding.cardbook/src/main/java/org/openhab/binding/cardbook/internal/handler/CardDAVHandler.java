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
package org.openhab.binding.cardbook.internal.handler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.openhab.binding.cardbook.internal.CardDAVConfiguration;
import org.openhab.io.transport.webdav.Resource;
import org.openhab.io.transport.webdav.WebDAVManager;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NonNullByDefault
/**
 * The {@link CardDAVHandler} is for getting informations from a CardDAV server
 * and making them available to OH2
 *
 * @author Gaël L'hopital - Initial contribution
 */
public class CardDAVHandler extends CardBookHandler {
    private final Logger logger = LoggerFactory.getLogger(CardDAVHandler.class);
    private @NonNullByDefault({}) WebDAVManager webDAVManager;
    private @NonNullByDefault({}) CardDAVConfiguration config;

    public CardDAVHandler(Thing thing) {
        super(thing);
    }

    @Override
    public void initialize() {
        super.initialize();
        config = getConfigAs(CardDAVConfiguration.class);
        try {
            logger.info("Initializing connection to '{}'", config.url);
            webDAVManager.defineEnpoint(config.url, config.username, config.password);
            // Test a directory read to ensure connection is fine
            webDAVManager.list(config.url);
            updateStatus(ThingStatus.ONLINE);
        } catch (MalformedURLException e) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR, e.getMessage());
        } catch (IOException e) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR, e.getMessage());
        }
    }

    @Override
    protected List<String> getRawCards() {
        List<String> result = new ArrayList<>();
        try {
            List<Resource> resources = webDAVManager.list(config.url);
            resources = resources.stream().filter(r -> !r.isDirectory()).filter(r -> r.getName().endsWith(".vcf"))
                    .collect(Collectors.toList());
            resources.forEach(resource -> {
                URI href = resource.getHref();
                try {
                    URL url = new URL(config.url);
                    url = new URL(url.getProtocol(), url.getHost(), url.getPort(), href.toString());
                    String content = webDAVManager.get(url);
                    result.add(content);
                } catch (IOException e) {
                    logger.warn("Error accessing CardDAV folder '{}' : {}", config.url, e.getMessage());
                }
            });
        } catch (IOException e) {
            logger.warn("Error accessing CardDAV folder '{}' : {}", config.url, e.getMessage());
        }
        return result;
    }

    @Reference
    protected void setWebDAVManager(WebDAVManager webDAVManager) {
        this.webDAVManager = webDAVManager;
    }

    protected void unsetWebDAVManager(WebDAVManager webDAVManager) {
        this.webDAVManager = null;
    }

}
