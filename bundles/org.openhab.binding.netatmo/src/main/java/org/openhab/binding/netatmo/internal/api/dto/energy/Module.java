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
public class Module implements Serializable {

    private Integer firmwareRevision;
    private String id;
    private Integer rfStrength;
    private String type;
    private Integer wifiStrength;
    private String batteryState;
    private String bridge;
    private Boolean reachable;
    private Map<String, Object> additionalProperties = new HashMap<>();
    private final static long serialVersionUID = 1685165670279602787L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Module() {
    }

    /**
     * 
     * @param wifiStrength
     * @param type
     * @param rfStrength
     * @param reachable
     * @param batteryState
     * @param id
     * @param bridge
     * @param firmwareRevision
     */
    public Module(Integer firmwareRevision, String id, Integer rfStrength, String type, Integer wifiStrength,
            Boolean reachable, String bridge, String batteryState) {
        super();
        this.firmwareRevision = firmwareRevision;
        this.id = id;
        this.rfStrength = rfStrength;
        this.type = type;
        this.wifiStrength = wifiStrength;
        this.reachable = reachable;
        this.bridge = bridge;
        this.batteryState = batteryState;
    }

    public Integer getFirmwareRevision() {
        return firmwareRevision;
    }

    public void setFirmwareRevision(Integer firmwareRevision) {
        this.firmwareRevision = firmwareRevision;
    }

    public Module withFirmwareRevision(Integer firmwareRevision) {
        this.firmwareRevision = firmwareRevision;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Module withId(String id) {
        this.id = id;
        return this;
    }

    public Integer getRfStrength() {
        return rfStrength;
    }

    public void setRfStrength(Integer rfStrength) {
        this.rfStrength = rfStrength;
    }

    public Module withRfStrength(Integer rfStrength) {
        this.rfStrength = rfStrength;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Module withType(String type) {
        this.type = type;
        return this;
    }

    public Integer getWifiStrength() {
        return wifiStrength;
    }

    public void setWifiStrength(Integer wifiStrength) {
        this.wifiStrength = wifiStrength;
    }

    public Module withWifiStrength(Integer wifiStrength) {
        this.wifiStrength = wifiStrength;
        return this;
    }

    public Boolean getReachable() {
        return reachable;
    }

    public void setReachable(Boolean reachable) {
        this.reachable = reachable;
    }

    public Module withReachable(Boolean reachable) {
        this.reachable = reachable;
        return this;
    }

    public String getBridge() {
        return bridge;
    }

    public void setBridge(String bridge) {
        this.bridge = bridge;
    }

    public Module withBridge(String bridge) {
        this.bridge = bridge;
        return this;
    }

    public String getBatteryState() {
        return batteryState;
    }

    public void setBatteryState(String batteryState) {
        this.batteryState = batteryState;
    }

    public Module withBatteryState(String batteryState) {
        this.batteryState = batteryState;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Module withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}
