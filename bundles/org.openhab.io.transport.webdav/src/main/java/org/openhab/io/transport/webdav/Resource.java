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

import java.net.URI;

/**
 * This service provides functionalities to access WebDAV resources
 *
 * @author Gaël L'hopital - Initial contribution
 */
public final class Resource {
    private boolean directory;
    private String name;
    private URI Href;

    public boolean isDirectory() {
        return this.directory;
    }

    public String getName() {
        return this.name;
    }

    public URI getHref() {
        return this.Href;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHref(URI href) {
        Href = href;
    }
}
