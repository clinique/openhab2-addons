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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * This service provides functionalities to access WebDAV resources
 *
 * @author Gaël L'hopital - Initial contribution
 */
@NonNullByDefault
public interface WebDAVManager {

    public List<Resource> list(String path) throws MalformedURLException, IOException;

    public String get(URL url) throws IOException;

    public void defineEnpoint(String url, String username, String password) throws MalformedURLException;
}
