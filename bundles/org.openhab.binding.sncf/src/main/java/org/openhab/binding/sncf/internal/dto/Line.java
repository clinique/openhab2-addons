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
public class Line {
    @SerializedName("comment")
    private String comment = "";

    @SerializedName("properties")
    private List<Property> properties = new ArrayList<>();

    @SerializedName("code")
    private String code = "";

    @SerializedName("network")
    private Network network = new Network();

    @SerializedName("links")
    private List<LinkSchema> links = new ArrayList<>();

    @SerializedName("color")
    private String color = "";

    @SerializedName("routes")
    private List<Route> routes = new ArrayList<>();

    @SerializedName("geojson")
    private MultiLineStringSchema geojson = new MultiLineStringSchema();

    @SerializedName("text_color")
    private String textColor = "";

    @SerializedName("physical_modes")
    private List<PhysicalMode> physicalModes = new ArrayList<>();

    @SerializedName("codes")
    private List<Code> codes = new ArrayList<>();

    @SerializedName("comments")
    private List<Comment> comments = new ArrayList<>();

    @SerializedName("closing_time")
    private String closingTime = "";

    @SerializedName("opening_time")
    private String openingTime = "";

    @SerializedName("commercial_mode")
    private CommercialMode commercialMode = new CommercialMode();

    @SerializedName("id")
    private String id = "";

    @SerializedName("line_groups")
    private List<LineGroup> lineGroups = new ArrayList<>();

    @SerializedName("name")
    private String name = "";

    /**
     * Get comment
     * 
     * @return comment
     **/
    public String getComment() {
        return comment;
    }

    /**
     * Get properties
     * 
     * @return properties
     **/
    public List<Property> getProperties() {
        return properties;
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
    public Network getNetwork() {
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
     * Get routes
     * 
     * @return routes
     **/
    public List<Route> getRoutes() {
        return routes;
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
     * Get textColor
     * 
     * @return textColor
     **/
    public String getTextColor() {
        return textColor;
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
     * Get codes
     * 
     * @return codes
     **/
    public List<Code> getCodes() {
        return codes;
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
     * Get closingTime
     * 
     * @return closingTime
     **/
    public String getClosingTime() {
        return closingTime;
    }

    /**
     * Get openingTime
     * 
     * @return openingTime
     **/
    public String getOpeningTime() {
        return openingTime;
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
     * Get lineGroups
     * 
     * @return lineGroups
     **/
    public List<LineGroup> getLineGroups() {
        return lineGroups;
    }

    /**
     * Name of the object
     * 
     * @return name
     **/
    public String getName() {
        return name;
    }

}
