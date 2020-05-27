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
public class Route {
    @SerializedName("direction")
    private Place direction = new Place();

    @SerializedName("codes")
    private List<Code> codes = new ArrayList<>();

    @SerializedName("name")
    private String name = "";

    @SerializedName("links")
    private List<LinkSchema> links = new ArrayList<>();

    @SerializedName("physical_modes")
    private List<PhysicalMode> physicalModes = new ArrayList<>();

    /**
     * Gets or Sets isFrequence
     */
    public enum IsFrequenceEnum {
        UNDEFINED("undefined"),
        @SerializedName("False")
        FALSE("False");

        private String value;

        IsFrequenceEnum(String value) {
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

    @SerializedName("is_frequence")
    private IsFrequenceEnum isFrequence = IsFrequenceEnum.UNDEFINED;

    @SerializedName("comments")
    private List<Comment> comments = new ArrayList<>();

    @SerializedName("direction_type")
    private String directionType = "";

    @SerializedName("geojson")
    private MultiLineStringSchema geojson = new MultiLineStringSchema();

    @SerializedName("stop_points")
    private List<StopPoint> stopPoints = new ArrayList<>();

    @SerializedName("line")
    private Line line = new Line();

    @SerializedName("id")
    private String id = "";

    /**
     * Get direction
     * 
     * @return direction
     **/
    public Place getDirection() {
        return direction;
    }

    /**
     * Get codes
     * 
     * @return codes
     **/
    public List<Code> getCodes() {
        return codes;
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
     * Get links
     * 
     * @return links
     **/
    public List<LinkSchema> getLinks() {
        return links;
    }

    /**
     * Get physicalModes
     * 
     * @return physicalModes
     **/
    public List<PhysicalMode> getPhysicalModes() {
        return physicalModes;
    }

    /**
     * Get isFrequence
     * 
     * @return isFrequence
     **/
    public IsFrequenceEnum getIsFrequence() {
        return isFrequence;
    }

    /**
     * Get comments
     * 
     * @return comments
     **/
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Get directionType
     * 
     * @return directionType
     **/
    public String getDirectionType() {
        return directionType;
    }

    /**
     * Get geojson
     * 
     * @return geojson
     **/
    public MultiLineStringSchema getGeojson() {
        return geojson;
    }

    /**
     * Get stopPoints
     * 
     * @return stopPoints
     **/
    public List<StopPoint> getStopPoints() {
        return stopPoints;
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
     * Identifier of the object
     * 
     * @return id
     **/
    public String getId() {
        return id;
    }

}
