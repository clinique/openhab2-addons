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
public class EquipmentDetails {
    /**
     * Gets or Sets embeddedType
     */
    public enum EmbeddedTypeEnum {
        UNDEFINED("undefined"),
        @SerializedName("escalator")
        ESCALATOR("escalator"),

        @SerializedName("elevator")
        ELEVATOR("elevator");

        private String value;

        EmbeddedTypeEnum(String value) {
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

    @SerializedName("embedded_type")
    private EmbeddedTypeEnum embeddedType = EmbeddedTypeEnum.UNDEFINED;

    @SerializedName("id")
    private String id = "";

    @SerializedName("name")
    private String name = "";

    @SerializedName("current_availability")
    private CurrentAvailability currentAvailability = new CurrentAvailability();

    /**
     * Get embeddedType
     * 
     * @return embeddedType
     **/
    public EmbeddedTypeEnum getEmbeddedType() {
        return embeddedType;
    }

    /**
     * Get id
     * 
     * @return id
     **/
    public String getId() {
        return id;
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
     * Get currentAvailability
     * 
     * @return currentAvailability
     **/
    public CurrentAvailability getCurrentAvailability() {
        return currentAvailability;
    }

}
