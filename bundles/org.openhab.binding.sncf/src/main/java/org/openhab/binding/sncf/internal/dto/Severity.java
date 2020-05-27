/**
 * Copyright (c) 2010-2020 Contributors to the openHAB project
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
package org.openhab.binding.sncf.internal.dto;

import static org.openhab.binding.sncf.internal.SncfBindingConstants.UNDEFINED;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.google.gson.annotations.SerializedName;

@NonNullByDefault
public class Severity {
    @SerializedName("color")
    private String color = "";

    @SerializedName("priority")
    private int priority = UNDEFINED;

    @SerializedName("name")
    private String name = "";

    /**
     * Gets or Sets effect
     */
    public enum EffectEnum {
        UNDEFINED("undefined"),
        @SerializedName("NO_SERVICE")
        NO_SERVICE("NO_SERVICE"),

        @SerializedName("REDUCED_SERVICE")
        REDUCED_SERVICE("REDUCED_SERVICE"),

        @SerializedName("SIGNIFICANT_DELAYS")
        SIGNIFICANT_DELAYS("SIGNIFICANT_DELAYS"),

        @SerializedName("DETOUR")
        DETOUR("DETOUR"),

        @SerializedName("ADDITIONAL_SERVICE")
        ADDITIONAL_SERVICE("ADDITIONAL_SERVICE"),

        @SerializedName("MODIFIED_SERVICE")
        MODIFIED_SERVICE("MODIFIED_SERVICE"),

        @SerializedName("OTHER_EFFECT")
        OTHER_EFFECT("OTHER_EFFECT"),

        @SerializedName("UNKNOWN_EFFECT")
        UNKNOWN_EFFECT("UNKNOWN_EFFECT"),

        @SerializedName("STOP_MOVED")
        STOP_MOVED("STOP_MOVED");

        private String value;

        EffectEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    @SerializedName("effect")
    private EffectEnum effect = EffectEnum.UNDEFINED;

    /**
     * Get color
     * 
     * @return color
     **/
    public String getColor() {
        return color;
    }

    /**
     * Get priority
     * 
     * @return priority
     **/
    public int getPriority() {
        return priority;
    }

    /**
     * Get name
     * 
     * @return name
     **/
    public String getName() {
        return name;
    }

    /**
     * Get effect
     * 
     * @return effect
     **/
    public EffectEnum getEffect() {
        return effect;
    }

}
