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
public class Stands {
    /**
     * Gets or Sets status
     */
    public enum StatusEnum {
        UNDEFINED("undefined"),
        @SerializedName("unavailable")
        UNAVAILABLE("unavailable"),

        @SerializedName("closed")
        CLOSED("closed"),

        @SerializedName("open")
        OPEN("open");

        private String value;

        StatusEnum(String value) {
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

    @SerializedName("status")
    private StatusEnum status = StatusEnum.UNDEFINED;

    @SerializedName("available_places")
    private int availablePlaces = UNDEFINED;

    @SerializedName("available_bikes")
    private int availableBikes = UNDEFINED;

    @SerializedName("total_stands")
    private int totalStands = UNDEFINED;

    /**
     * Get status
     * 
     * @return status
     **/
    public StatusEnum getStatus() {
        return status;
    }

    /**
     * Get availablePlaces
     * 
     * @return availablePlaces
     **/
    public int getAvailablePlaces() {
        return availablePlaces;
    }

    /**
     * Get availableBikes
     * 
     * @return availableBikes
     **/
    public int getAvailableBikes() {
        return availableBikes;
    }

    /**
     * Get totalStands
     * 
     * @return totalStands
     **/
    public int getTotalStands() {
        return totalStands;
    }

}
