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
public class StopArea {
    @SerializedName("comment")
    private String comment = "";

    @SerializedName("codes")
    private List<Code> codes = new ArrayList<>();

    @SerializedName("name")
    private String name = "";

    @SerializedName("links")
    private List<LinkSchema> links = new ArrayList<>();

    @SerializedName("physical_modes")
    private List<PhysicalMode> physicalModes = new ArrayList<>();

    @SerializedName("comments")
    private List<Comment> comments = new ArrayList<>();

    @SerializedName("label")
    private String label = "";

    @SerializedName("commercial_modes")
    private List<CommercialMode> commercialModes = new ArrayList<>();

    @SerializedName("coord")
    private Coord coord = new Coord();

    @SerializedName("administrative_regions")
    private List<Admin> administrativeRegions = new ArrayList<>();

    @SerializedName("timezone")
    private String timezone = "";

    @SerializedName("stop_points")
    private List<StopPoint> stopPoints = new ArrayList<>();

    @SerializedName("id")
    private String id = "";

    /**
     * Get comment
     *
     * @return comment
     **/
    public String getComment() {
        return comment;
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
     * Get comments
     *
     * @return comments
     **/
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Label of the stop area. The name is directly taken from the data whereas the label is something we compute for
     * better traveler information. If you don't know what to display, display the label.
     *
     * @return label
     **/
    public String getLabel() {
        return label;
    }

    /**
     * Get commercialModes
     *
     * @return commercialModes
     **/
    public List<CommercialMode> getCommercialModes() {
        return commercialModes;
    }

    /**
     * Get coord
     *
     * @return coord
     **/
    public Coord getCoord() {
        return coord;
    }

    /**
     * Get administrativeRegions
     *
     * @return administrativeRegions
     **/
    public List<Admin> getAdministrativeRegions() {
        return administrativeRegions;
    }

    /**
     * Get timezone
     *
     * @return timezone
     **/
    public String getTimezone() {
        return timezone;
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
     * Identifier of the object
     *
     * @return id
     **/
    public String getId() {
        return id;
    }

}
