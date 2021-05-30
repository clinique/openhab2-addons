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

/**
 *
 * @author Markus Dillmann - Initial contribution
 *
 */

public class NAEnergyModule extends NADevice {
    private int firmware_revision = -1;
    private int rf_strength;
    private String battery_state = "";

    public String getBatteryState() {
        return this.battery_state;
    }

    public void setBatteryState(String battery_state) {
        this.battery_state = battery_state;
    }

    public int getRfStrength() {
        return this.rf_strength;
    }

    public void setRfStrength(int rf_strength) {
        this.rf_strength = rf_strength;
    }

    public int getFirmware_revision() {
        return this.firmware_revision;
    }

    public void setFirmware_revision(int firmware_revision) {
        this.firmware_revision = firmware_revision;
    }
}
