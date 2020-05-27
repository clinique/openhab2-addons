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
public class PtObject {
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

    @SerializedName("name")
    private String name = "";

    @SerializedName("route")
    private Route route = new Route();

    @SerializedName("stop_area")
    private StopArea stopArea = new StopArea();

    @SerializedName("commercial_mode")
    private CommercialMode commercialMode = new CommercialMode();

    @SerializedName("id")
    private String id = "";

    @SerializedName("line")
    private Line line = new Line();

    @SerializedName("quality")
    private int quality = UNDEFINED;

    @SerializedName("trip")
    private Trip trip = new Trip();

    @SerializedName("network")
    private Network network = new Network();

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
     * Name of the object
     * 
     * @return name
     **/
    public String getName() {
        return name;
    }

    /**
     * Get route
     * 
     * @return route
     **/
    public Route getRoute() {
        return route;
    }

    /**
     * Get stopArea
     * 
     * @return stopArea
     **/
    public StopArea getStopArea() {
        return stopArea;
    }

    /**
     * Get commercialMode
     * 
     * @return commercialMode
     **/
    public CommercialMode getCommercialMode() {
        return commercialMode;
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
     * Get line
     * 
     * @return line
     **/
    public Line getLine() {
        return line;
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
     * Get trip
     * 
     * @return trip
     **/
    public Trip getTrip() {
        return trip;
    }

    /**
     * Get network
     * 
     * @return network
     **/
    public Network getNetwork() {
        return network;
    }

}
