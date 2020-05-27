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
public class Place {
    /**
     * Gets or Sets embeddedType
     */
    public enum EmbeddedTypeEnum {
        UNDEFINED("undefined"),
        @SerializedName("line")
        LINE("line"),

        @SerializedName("journey_pattern")
        JOURNEY_PATTERN("journey_pattern"),

        @SerializedName("vehicle_journey")
        VEHICLE_JOURNEY("vehicle_journey"),

        @SerializedName("stop_point")
        STOP_POINT("stop_point"),

        @SerializedName("stop_area")
        STOP_AREA("stop_area"),

        @SerializedName("network")
        NETWORK("network"),

        @SerializedName("physical_mode")
        PHYSICAL_MODE("physical_mode"),

        @SerializedName("commercial_mode")
        COMMERCIAL_MODE("commercial_mode"),

        @SerializedName("connection")
        CONNECTION("connection"),

        @SerializedName("journey_pattern_point")
        JOURNEY_PATTERN_POINT("journey_pattern_point"),

        @SerializedName("company")
        COMPANY("company"),

        @SerializedName("route")
        ROUTE("route"),

        @SerializedName("poi")
        POI("poi"),

        @SerializedName("contributor")
        CONTRIBUTOR("contributor"),

        @SerializedName("address")
        ADDRESS("address"),

        @SerializedName("poitype")
        POITYPE("poitype"),

        @SerializedName("administrative_region")
        ADMINISTRATIVE_REGION("administrative_region"),

        @SerializedName("calendar")
        CALENDAR("calendar"),

        @SerializedName("line_group")
        LINE_GROUP("line_group"),

        @SerializedName("impact")
        IMPACT("impact"),

        @SerializedName("dataset")
        DATASET("dataset"),

        @SerializedName("trip")
        TRIP("trip");

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

    @SerializedName("stop_point")
    private StopPoint stopPoint = new StopPoint();

    @SerializedName("administrative_region")
    private Admin administrativeRegion = new Admin();

    @SerializedName("name")
    private String name = "";

    @SerializedName("distance")
    private String distance = "";

    @SerializedName("address")
    private Address address = new Address();

    @SerializedName("poi")
    private Poi poi = new Poi();

    @SerializedName("quality")
    private int quality = UNDEFINED;

    @SerializedName("id")
    private String id = "";

    @SerializedName("stop_area")
    private StopArea stopArea = new StopArea();

    /**
     * Get embeddedType
     * 
     * @return embeddedType
     **/
    public EmbeddedTypeEnum getEmbeddedType() {
        return embeddedType;
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
     * Get administrativeRegion
     * 
     * @return administrativeRegion
     **/
    public Admin getAdministrativeRegion() {
        return administrativeRegion;
    }

    /**
     * Name of the object
     * 
     * @return name
     **/
    public String getName() {
        return name;
    }

    /**
     * Distance to the object in meters
     * 
     * @return distance
     **/
    public String getDistance() {
        return distance;
    }

    /**
     * Get address
     * 
     * @return address
     **/
    public Address getAddress() {
        return address;
    }

    /**
     * Get poi
     * 
     * @return poi
     **/
    public Poi getPoi() {
        return poi;
    }

    /**
     * Get quality
     * 
     * @return quality
     **/
    public Integer getQuality() {
        return quality;
    }

    /**
     * Identifier of the object
     * 
     * @return id
     **/
    public String getId() {
        return id;
    }

    /**
     * Get stopArea
     * 
     * @return stopArea
     **/
    public StopArea getStopArea() {
        return stopArea;
    }

}
