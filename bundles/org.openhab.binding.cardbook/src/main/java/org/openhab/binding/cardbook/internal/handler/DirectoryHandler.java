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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.thing.Thing;
import org.openhab.binding.cardbook.internal.DirectoryConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NonNullByDefault
/**
 * The {@link CardDAVHandler} is for getting informations from a local directory
 * and making them available to OH2
 *
 * @author Gaël L'hopital - Initial contribution
 */
public class DirectoryHandler extends CardBookHandler {
    private final Logger logger = LoggerFactory.getLogger(DirectoryHandler.class);
    private @NonNullByDefault({}) DirectoryConfiguration config;
    private @NonNullByDefault({}) File cardbook;

    public DirectoryHandler(Thing thing) {
        super(thing);
    }

    @Override
    public void initialize() {
        super.initialize();
        config = getConfigAs(DirectoryConfiguration.class);
        cardbook = new File(config.rootDirectory);
    }

    @Override
    protected List<String> getRawCards() {
        List<String> result = new ArrayList<>();
        for (File file : cardbook.listFiles((d, name) -> name.endsWith(".vcf"))) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
                result.add(content);

            } catch (IOException e) {
                logger.warn("Error accessing CardDAV folder '{}' : {}", config.rootDirectory, e.getMessage());
            }
        }
        return result;
    }
}
