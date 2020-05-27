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

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.google.gson.annotations.SerializedName;

@NonNullByDefault
public class ImpactedStop {
    @SerializedName("amended_arrival_time")
    private String amendedArrivalTime = "";

    @SerializedName("stop_point")
    private StopPoint stopPoint = new StopPoint();

    /**
     * Gets or Sets stopTimeEffect
     */
    public enum StopTimeEffectEnum {
        UNDEFINED("undefined"),
        @SerializedName("delayed")
        DELAYED("delayed"),

        @SerializedName("added")
        ADDED("added"),

        @SerializedName("deleted")
        DELETED("deleted"),

        @SerializedName("unchanged")
        UNCHANGED("unchanged");

        private String value;

        StopTimeEffectEnum(String value) {
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

    @SerializedName("stop_time_effect")
    private StopTimeEffectEnum stopTimeEffect = StopTimeEffectEnum.UNDEFINED;

    @SerializedName("departure_status")
    private String departureStatus = "";

    @SerializedName("is_detour")
    private boolean isDetour;

    @SerializedName("amended_departure_time")
    private String amendedDepartureTime = "";

    @SerializedName("base_arrival_time")
    private String baseArrivalTime = "";

    @SerializedName("cause")
    private String cause = "";

    @SerializedName("base_departure_time")
    private String baseDepartureTime = "";

    @SerializedName("arrival_status")
    private String arrivalStatus = "";

    /**
     * Get amendedArrivalTime
     * 
     * @return amendedArrivalTime
     **/
    public String getAmendedArrivalTime() {
        return amendedArrivalTime;
    }

    /**
     * Get stopPoint
     * 
     * @return stopPoint
     **/
    public StopPoint getStopPoint() {
        return stopPoint;
    }

    /**
     * Get stopTimeEffect
     * 
     * @return stopTimeEffect
     **/
    public StopTimeEffectEnum getStopTimeEffect() {
        return stopTimeEffect;
    }

    /**
     * Get departureStatus
     * 
     * @return departureStatus
     **/
    public String getDepartureStatus() {
        return departureStatus;
    }

    /**
     * Get isDetour
     * 
     * @return isDetour
     **/
    public boolean getIsDetour() {
        return isDetour;
    }

    /**
     * Get amendedDepartureTime
     * 
     * @return amendedDepartureTime
     **/
    public String getAmendedDepartureTime() {
        return amendedDepartureTime;
    }

    /**
     * Get baseArrivalTime
     * 
     * @return baseArrivalTime
     **/
    public String getBaseArrivalTime() {
        return baseArrivalTime;
    }

    /**
     * Get cause
     * 
     * @return cause
     **/
    public String getCause() {
        return cause;
    }

    /**
     * Get baseDepartureTime
     * 
     * @return baseDepartureTime
     **/
    public String getBaseDepartureTime() {
        return baseDepartureTime;
    }

    /**
     * Get arrivalStatus
     * 
     * @return arrivalStatus
     **/
    public String getArrivalStatus() {
        return arrivalStatus;
    }

}
