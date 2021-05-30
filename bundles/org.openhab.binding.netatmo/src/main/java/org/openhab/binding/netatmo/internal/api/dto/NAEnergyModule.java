package org.openhab.binding.netatmo.internal.api.dto;

/**
 *
 * @author Markus Dillmann - Initial contribution
 *
 */

public class NAEnergyModule extends NADevice{
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
