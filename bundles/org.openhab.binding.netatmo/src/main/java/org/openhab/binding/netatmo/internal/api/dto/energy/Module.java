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

    private Boolean boilerCable;
    private Integer firmwareRevision;
    private Integer hardwareVersion;
    private String id;
    private Integer rfStrength;
    private String type;
    private Integer wifiStrength;
    private Boolean anticipating;
    private Integer batteryLevel;
    private Boolean boilerStatus;
    private Boolean boilerValveComfortBoost;
    private Boolean reachable;
    private String bridge;
    private String batteryState;
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
     * @param boilerValveComfortBoost
     * @param type
     * @param rfStrength
     * @param reachable
     * @param boilerCable
     * @param batteryState
     * @param boilerStatus
     * @param hardwareVersion
     * @param id
     * @param bridge
     * @param anticipating
     * @param firmwareRevision
     * @param batteryLevel
     */
    public Module(Boolean boilerCable, Integer firmwareRevision, Integer hardwareVersion, String id, Integer rfStrength,
            String type, Integer wifiStrength, Boolean anticipating, Integer batteryLevel, Boolean boilerStatus,
            Boolean boilerValveComfortBoost, Boolean reachable, String bridge, String batteryState) {
        super();
        this.boilerCable = boilerCable;
        this.firmwareRevision = firmwareRevision;
        this.hardwareVersion = hardwareVersion;
        this.id = id;
        this.rfStrength = rfStrength;
        this.type = type;
        this.wifiStrength = wifiStrength;
        this.anticipating = anticipating;
        this.batteryLevel = batteryLevel;
        this.boilerStatus = boilerStatus;
        this.boilerValveComfortBoost = boilerValveComfortBoost;
        this.reachable = reachable;
        this.bridge = bridge;
        this.batteryState = batteryState;
    }

    public Boolean getBoilerCable() {
        return boilerCable;
    }

    public void setBoilerCable(Boolean boilerCable) {
        this.boilerCable = boilerCable;
    }

    public Module withBoilerCable(Boolean boilerCable) {
        this.boilerCable = boilerCable;
        return this;
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

    public Integer getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(Integer hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public Module withHardwareVersion(Integer hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
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

    public Boolean getAnticipating() {
        return anticipating;
    }

    public void setAnticipating(Boolean anticipating) {
        this.anticipating = anticipating;
    }

    public Module withAnticipating(Boolean anticipating) {
        this.anticipating = anticipating;
        return this;
    }

    public Integer getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Integer batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public Module withBatteryLevel(Integer batteryLevel) {
        this.batteryLevel = batteryLevel;
        return this;
    }

    public Boolean getBoilerStatus() {
        return boilerStatus;
    }

    public void setBoilerStatus(Boolean boilerStatus) {
        this.boilerStatus = boilerStatus;
    }

    public Module withBoilerStatus(Boolean boilerStatus) {
        this.boilerStatus = boilerStatus;
        return this;
    }

    public Boolean getBoilerValveComfortBoost() {
        return boilerValveComfortBoost;
    }

    public void setBoilerValveComfortBoost(Boolean boilerValveComfortBoost) {
        this.boilerValveComfortBoost = boilerValveComfortBoost;
    }

    public Module withBoilerValveComfortBoost(Boolean boilerValveComfortBoost) {
        this.boilerValveComfortBoost = boilerValveComfortBoost;
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
