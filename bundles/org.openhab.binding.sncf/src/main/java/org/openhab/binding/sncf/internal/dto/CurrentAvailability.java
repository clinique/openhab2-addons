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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.google.gson.annotations.SerializedName;

@NonNullByDefault
public class CurrentAvailability {
    /**
     * Gets or Sets status
     */
    public enum StatusEnum {
        UNDEFINED("undefined"),
        @SerializedName("unknown")
        UNKNOWN("unknown"),

        @SerializedName("available")
        AVAILABLE("available"),

        @SerializedName("unavailable")
        UNAVAILABLE("unavailable");

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

    @SerializedName("effect")
    private Effect effect = new Effect();

    @SerializedName("cause")
    private Cause cause = new Cause();

    @SerializedName("periods")
    private List<Period> periods = new ArrayList<>();

    @SerializedName("updated_at")
    private String updatedAt = "";

    /**
     * Get status
     * 
     * @return status
     **/
    public StatusEnum getStatus() {
        return status;
    }

    /**
     * Get effect
     * 
     * @return effect
     **/
    public Effect getEffect() {
        return effect;
    }

    /**
     * Get cause
     * 
     * @return cause
     **/
    public Cause getCause() {
        return cause;
    }

    /**
     * Get periods
     * 
     * @return periods
     **/
    public List<Period> getPeriods() {
        return periods;
    }

    /**
     * Get updatedAt
     * 
     * @return updatedAt
     **/
    public String getUpdatedAt() {
        return updatedAt;
    }

}
