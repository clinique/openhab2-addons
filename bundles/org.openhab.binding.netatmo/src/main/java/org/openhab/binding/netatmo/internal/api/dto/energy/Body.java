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
public class Body implements Serializable {

    private Home home;
    private Map<String, Object> additionalProperties = new HashMap<>();
    private final static long serialVersionUID = 4318969274668449496L;

    /**
     * No args constructor for use in serialization
     * 
     */
    // public Body() {
    // }

    /**
     * 
     * @param home
     */
    public Body(Home home) {
        super();
        this.home = home;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public Body withHome(Home home) {
        this.home = home;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Body withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}
