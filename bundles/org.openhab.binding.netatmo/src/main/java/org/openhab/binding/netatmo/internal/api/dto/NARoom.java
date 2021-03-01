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
package org.openhab.binding.netatmo.internal.api.dto;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Gaël L'hopital - Initial contribution
 *
 */

public class NARoom extends NAObject {
    private boolean anticipating;
    private int heating_power_request;
    private boolean open_window;
    private boolean reachable;
    private int therm_measured_temperature;
    private String therm_setpoint_mode;
    private int therm_setpoint_temperature;

    private String type;

    @SerializedName(value = "last_message", alternate = { "last_activity" })
    private long lastMessage;

    public long getLastMessage() {
        return lastMessage;
    }

    /**
     * @return the anticipating
     */
    public boolean isAnticipating() {
        return anticipating;
    }

    /**
     * @param anticipating the anticipating to set
     */
    public void setAnticipating(boolean anticipating) {
        this.anticipating = anticipating;
    }

    /**
     * @return the heating_power_request
     */
    public int getHeating_power_request() {
        return heating_power_request;
    }

    /**
     * @param heating_power_request the heating_power_request to set
     */
    public void setHeating_power_request(int heating_power_request) {
        this.heating_power_request = heating_power_request;
    }

    /**
     * @return the open_window
     */
    public boolean isOpen_window() {
        return open_window;
    }

    /**
     * @param open_window the open_window to set
     */
    public void setOpen_window(boolean open_window) {
        this.open_window = open_window;
    }

    /**
     * @return the reachable
     */
    public boolean isReachable() {
        return reachable;
    }

    /**
     * @param reachable the reachable to set
     */
    public void setReachable(boolean reachable) {
        this.reachable = reachable;
    }

    /**
     * @return the therm_measured_temperature
     */
    public int getTherm_measured_temperature() {
        return therm_measured_temperature;
    }

    /**
     * @param therm_measured_temperature the therm_measured_temperature to set
     */
    public void setTherm_measured_temperature(int therm_measured_temperature) {
        this.therm_measured_temperature = therm_measured_temperature;
    }

    /**
     * @return the therm_setpoint_mode
     */
    public String getTherm_setpoint_mode() {
        return therm_setpoint_mode;
    }

    /**
     * @param therm_setpoint_mode the therm_setpoint_mode to set
     */
    public void setTherm_setpoint_mode(String therm_setpoint_mode) {
        this.therm_setpoint_mode = therm_setpoint_mode;
    }

    /**
     * @return the therm_setpoint_temperature
     */
    public int getTherm_setpoint_temperature() {
        return therm_setpoint_temperature;
    }

    /**
     * @param therm_setpoint_temperature the therm_setpoint_temperature to set
     */
    public void setTherm_setpoint_temperature(int therm_setpoint_temperature) {
        this.therm_setpoint_temperature = therm_setpoint_temperature;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}
