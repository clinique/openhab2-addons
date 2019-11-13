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
package org.openhab.io.transport.webdav;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * This service provides functionalities to access WebDAV resources
 *
 * @author Gaël L'hopital - Initial contribution
 */
@NonNullByDefault
public final class WebDAVEndpoint {
    private final String hostname;
    private final String protocol;
    private int port = 80;
    private @Nullable String username;
    private @Nullable String password;

    /** Cache the hash code for the string */
    private int hash; // Default to 0

    public WebDAVEndpoint(String url) throws MalformedURLException {
        this(url, null, null);
    }

    public WebDAVEndpoint(String url, @Nullable String username, @Nullable String password)
            throws MalformedURLException {
        URL decoder = new URL(url);
        this.hostname = decoder.getHost();
        this.protocol = decoder.getProtocol();
        this.port = decoder.getPort();
        this.username = username;
        this.password = password;
        this.hash = decoder.hashCode();
    }

    public @Nullable String getPassword() {
        return password;
    }

    public @Nullable String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return protocol + "://" + hostname + ":" + Integer.toString(port);
    }

    @Override
    public int hashCode() {
        return hash;
    }
}
