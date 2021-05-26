package org.openhab.binding.netatmo.internal.api.dto;

/**
 *
 * @author Markus Dillmann - Initial contribution
 *
 */

public class NAEnergyModule extends NAThing{
    private int firmware_revision;
    private int rf_strength;

    public int getRf_strength() {
        return this.rf_strength;
    }

    public void setRf_strength(int rf_strength) {
        this.rf_strength = rf_strength;
    }

    public int getFirmware_revision() {
        return this.firmware_revision;
    }

    public void setFirmware_revision(int firmware_revision) {
        this.firmware_revision = firmware_revision;
    }
}
