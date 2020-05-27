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
public class VJDisplayInformation {
    @SerializedName("direction")
    private String direction = "";

    @SerializedName("code")
    private String code = "";

    @SerializedName("network")
    private String network = "";

    @SerializedName("links")
    private List<LinkSchema> links = new ArrayList<>();

    @SerializedName("color")
    private String color = "";

    @SerializedName("name")
    private String name = "";

    @SerializedName("physical_mode")
    private String physicalMode = "";

    @SerializedName("headsign")
    private String headsign = "";

    @SerializedName("label")
    private String label = "";

    /**
     * Gets or Sets equipments
     */
    public enum EquipmentsEnum {
        UNDEFINED("undefined"),
        @SerializedName("has_wheelchair_accessibility")
        WHEELCHAIR_ACCESSIBILITY("has_wheelchair_accessibility"),

        @SerializedName("has_bike_accepted")
        BIKE_ACCEPTED("has_bike_accepted"),

        @SerializedName("has_air_conditioned")
        AIR_CONDITIONED("has_air_conditioned"),

        @SerializedName("has_visual_announcement")
        VISUAL_ANNOUNCEMENT("has_visual_announcement"),

        @SerializedName("has_audible_announcement")
        AUDIBLE_ANNOUNCEMENT("has_audible_announcement"),

        @SerializedName("has_appropriate_escort")
        APPROPRIATE_ESCORT("has_appropriate_escort"),

        @SerializedName("has_appropriate_signage")
        APPROPRIATE_SIGNAGE("has_appropriate_signage"),

        @SerializedName("has_school_vehicle")
        SCHOOL_VEHICLE("has_school_vehicle"),

        @SerializedName("has_wheelchair_boarding")
        WHEELCHAIR_BOARDING("has_wheelchair_boarding"),

        @SerializedName("has_sheltered")
        SHELTERED("has_sheltered"),

        @SerializedName("has_elevator")
        ELEVATOR("has_elevator"),

        @SerializedName("has_escalator")
        ESCALATOR("has_escalator"),

        @SerializedName("has_bike_depot")
        BIKE_DEPOT("has_bike_depot");

        private String value;

        EquipmentsEnum(String value) {
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

    @SerializedName("equipments")
    private List<EquipmentsEnum> equipments = new ArrayList<>();

    @SerializedName("text_color")
    private String textColor = "";

    @SerializedName("headsigns")
    private List<String> headsigns = new ArrayList<>();

    @SerializedName("commercial_mode")
    private String commercialMode = "";

    @SerializedName("description")
    private String description = "";

    /**
     * Get direction
     * 
     * @return direction
     **/
    public String getDirection() {
        return direction;
    }

    /**
     * Get code
     * 
     * @return code
     **/
    public String getCode() {
        return code;
    }

    /**
     * Get network
     * 
     * @return network
     **/
    public String getNetwork() {
        return network;
    }

    /**
     * Get links
     * 
     * @return links
     **/
    public List<LinkSchema> getLinks() {
        return links;
    }

    /**
     * Get color
     * 
     * @return color
     **/
    public String getColor() {
        return color;
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
     * Get physicalMode
     * 
     * @return physicalMode
     **/
    public String getPhysicalMode() {
        return physicalMode;
    }

    /**
     * Get headsign
     * 
     * @return headsign
     **/
    public String getHeadsign() {
        return headsign;
    }

    /**
     * Get label
     * 
     * @return label
     **/
    public String getLabel() {
        return label;
    }

    /**
     * Get equipments
     * 
     * @return equipments
     **/
    public List<EquipmentsEnum> getEquipments() {
        return equipments;
    }

    /**
     * Get textColor
     * 
     * @return textColor
     **/
    public String getTextColor() {
        return textColor;
    }

    /**
     * Get headsigns
     * 
     * @return headsigns
     **/
    public List<String> getHeadsigns() {
        return headsigns;
    }

    /**
     * Get commercialMode
     * 
     * @return commercialMode
     **/
    public String getCommercialMode() {
        return commercialMode;
    }

    /**
     * Get description
     * 
     * @return description
     **/
    public String getDescription() {
        return description;
    }

}
