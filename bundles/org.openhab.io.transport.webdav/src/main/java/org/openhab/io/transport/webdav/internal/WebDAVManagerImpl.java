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
package org.openhab.io.transport.webdav.internal;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.io.transport.webdav.Resource;
import org.openhab.io.transport.webdav.WebDAVEndpoint;
import org.openhab.io.transport.webdav.WebDAVManager;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;

/**
 * This service provides functionality around ephemeris services and is the central service to be used directly by
 * others.
 *
 * @author Gaël L'hopital - Initial contribution
 */
@Component(name = "org.openhab.io.transport.webdav", property = {
        Constants.SERVICE_PID + "=org.openhab.io.transport.webdav" })
@NonNullByDefault
public class WebDAVManagerImpl implements WebDAVManager {

    private final Logger logger = LoggerFactory.getLogger(WebDAVManagerImpl.class);

    private final Map<WebDAVEndpoint, Sardine> factories = new HashMap<>();

    @Activate
    public WebDAVManagerImpl() {
        logger.info("WebDAV Manager started");
    }

    @Override
    public List<Resource> list(String path) throws MalformedURLException, IOException {
        List<DavResource> result1 = getSardine(path).list(path);
        List<Resource> result2 = new ArrayList<>();
        result1.forEach(r -> {
            try {
                result2.add(new Resource(r));
            } catch (URISyntaxException e) {
                logger.warn("Error on DAVResource : {}", e.getMessage());
            }

        });
        return result2;
    }

    private String get(String path) throws IOException {
        Sardine factory = getSardine(path);
        InputStream inputStream = factory.get(path.toString());
        byte[] bytes = IOUtils.toByteArray(inputStream);
        return new String(bytes);
    }

    private Sardine getSardine(String address) throws IOException {
        WebDAVEndpoint endpoint = new WebDAVEndpoint(address);
        if (factories.containsKey(endpoint)) {
            return factories.get(endpoint);
        }
        throw new IOException(String.format("Domain '{}' not found", endpoint.toString()));
    }

    @Override
    public void defineEnpoint(String url, String username, String password) throws MalformedURLException {
        WebDAVEndpoint endpoint = new WebDAVEndpoint(url, username, password);
        factories.getOrDefault(endpoint, SardineFactory.begin(username, password));
    }

    @Override
    public String get(URL url) throws IOException {
        return get(url.toString());
    }
}
