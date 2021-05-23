/**
 * Copyright (c) 2010-2021 Contributors to the openHAB project
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
package org.openhab.binding.netatmo.internal.api.dto.energy;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bernhard Kreuz - Initial contribution
 */
public class Homestatus implements Serializable {

    private String status;
    private long timeServer;
    private Body body;
    private Map<String, Object> additionalProperties = new HashMap<>();
    private final static long serialVersionUID = -8532076777888120923L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Homestatus() {
    }

    /**
     * 
     * @param timeServer
     * @param body
     * @param status
     */
    public Homestatus(String status, long timeServer, Body body) {
        super();
        this.status = status;
        this.timeServer = timeServer;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Homestatus withStatus(String status) {
        this.status = status;
        return this;
    }

    public long getTimeServer() {
        return timeServer;
    }

    public void setTimeServer(long timeServer) {
        this.timeServer = timeServer;
    }

    public Homestatus withTimeServer(long timeServer) {
        this.timeServer = timeServer;
        return this;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Homestatus withBody(Body body) {
        this.body = body;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Homestatus withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}
