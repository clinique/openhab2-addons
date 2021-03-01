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
public class Room implements Serializable {

    private Boolean anticipating;
    private Integer heatingPowerRequest;
    private String id;
    private Boolean openWindow;
    private Boolean reachable;
    private Double thermMeasuredTemperature;
    private String thermSetpointMode;
    private Integer thermSetpointTemperature;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4756273979865765465L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Room() {
    }

    /**
     * 
     * @param heatingPowerRequest
     * @param openWindow
     * @param id
     * @param anticipating
     * @param thermSetpointMode
     * @param reachable
     * @param thermMeasuredTemperature
     * @param thermSetpointTemperature
     */
    public Room(Boolean anticipating, Integer heatingPowerRequest, String id, Boolean openWindow, Boolean reachable,
            Double thermMeasuredTemperature, String thermSetpointMode, Integer thermSetpointTemperature) {
        super();
        this.anticipating = anticipating;
        this.heatingPowerRequest = heatingPowerRequest;
        this.id = id;
        this.openWindow = openWindow;
        this.reachable = reachable;
        this.thermMeasuredTemperature = thermMeasuredTemperature;
        this.thermSetpointMode = thermSetpointMode;
        this.thermSetpointTemperature = thermSetpointTemperature;
    }

    public Boolean getAnticipating() {
        return anticipating;
    }

    public void setAnticipating(Boolean anticipating) {
        this.anticipating = anticipating;
    }

    public Room withAnticipating(Boolean anticipating) {
        this.anticipating = anticipating;
        return this;
    }

    public Integer getHeatingPowerRequest() {
        return heatingPowerRequest;
    }

    public void setHeatingPowerRequest(Integer heatingPowerRequest) {
        this.heatingPowerRequest = heatingPowerRequest;
    }

    public Room withHeatingPowerRequest(Integer heatingPowerRequest) {
        this.heatingPowerRequest = heatingPowerRequest;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Room withId(String id) {
        this.id = id;
        return this;
    }

    public Boolean getOpenWindow() {
        return openWindow;
    }

    public void setOpenWindow(Boolean openWindow) {
        this.openWindow = openWindow;
    }

    public Room withOpenWindow(Boolean openWindow) {
        this.openWindow = openWindow;
        return this;
    }

    public Boolean getReachable() {
        return reachable;
    }

    public void setReachable(Boolean reachable) {
        this.reachable = reachable;
    }

    public Room withReachable(Boolean reachable) {
        this.reachable = reachable;
        return this;
    }

    public Double getThermMeasuredTemperature() {
        return thermMeasuredTemperature;
    }

    public void setThermMeasuredTemperature(Double thermMeasuredTemperature) {
        this.thermMeasuredTemperature = thermMeasuredTemperature;
    }

    public Room withThermMeasuredTemperature(Double thermMeasuredTemperature) {
        this.thermMeasuredTemperature = thermMeasuredTemperature;
        return this;
    }

    public String getThermSetpointMode() {
        return thermSetpointMode;
    }

    public void setThermSetpointMode(String thermSetpointMode) {
        this.thermSetpointMode = thermSetpointMode;
    }

    public Room withThermSetpointMode(String thermSetpointMode) {
        this.thermSetpointMode = thermSetpointMode;
        return this;
    }

    public Integer getThermSetpointTemperature() {
        return thermSetpointTemperature;
    }

    public void setThermSetpointTemperature(Integer thermSetpointTemperature) {
        this.thermSetpointTemperature = thermSetpointTemperature;
    }

    public Room withThermSetpointTemperature(Integer thermSetpointTemperature) {
        this.thermSetpointTemperature = thermSetpointTemperature;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Room withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}
