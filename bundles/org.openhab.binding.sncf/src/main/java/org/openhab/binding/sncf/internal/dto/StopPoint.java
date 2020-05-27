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
public class StopPoint {
    public enum EquipmentsEnum {
        UNDEFINED,
        @SerializedName("has_wheelchair_accessibility")
        WHEELCHAIR_ACCESSIBILITY,
        @SerializedName("has_bike_accepted")
        BIKE_ACCEPTED,
        @SerializedName("has_air_conditioned")
        AIR_CONDITIONED,
        @SerializedName("has_visual_announcement")
        VISUAL_ANNOUNCEMENT,
        @SerializedName("has_audible_announcement")
        AUDIBLE_ANNOUNCEMENT,
        @SerializedName("has_appropriate_escort")
        APPROPRIATE_ESCORT,
        @SerializedName("has_appropriate_signage")
        APPROPRIATE_SIGNAGE,
        @SerializedName("has_school_vehicle")
        SCHOOL_VEHICLE,
        @SerializedName("has_wheelchair_boarding")
        WHEELCHAIR_BOARDING,
        @SerializedName("has_sheltered")
        SHELTERED,
        @SerializedName("has_elevator")
        ELEVATOR,
        @SerializedName("has_escalator")
        ESCALATOR,
        @SerializedName("has_bike_depot")
        BIKE_DEPOT;
    }

    private String comment = "";
    private List<CommercialMode> commercialModes = new ArrayList<>();
    private StopArea stopArea = new StopArea();
    private List<LinkSchema> links = new ArrayList<>();
    private List<Admin> administrativeRegions = new ArrayList<>();
    private List<PhysicalMode> physicalModes = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    private String label = "";
    private List<EquipmentsEnum> equipments = new ArrayList<>();
    private List<Code> codes = new ArrayList<>();
    private Coord coord = new Coord();
    private List<EquipmentDetails> equipmentDetails = new ArrayList<>();
    private Address address = new Address();
    private FareZone fareZone = new FareZone();
    private String id = "";
    private String name = "";

    public String getComment() {
        return comment;
    }

    public List<CommercialMode> getCommercialModes() {
        return commercialModes;
    }

    public StopArea getStopArea() {
        return stopArea;
    }

    public List<LinkSchema> getLinks() {
        return links;
    }

    public List<Admin> getAdministrativeRegions() {
        return administrativeRegions;
    }

    public List<PhysicalMode> getPhysicalModes() {
        return physicalModes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getLabel() {
        return label;
    }

    public List<EquipmentsEnum> getEquipments() {
        return equipments;
    }

    public List<Code> getCodes() {
        return codes;
    }

    public Coord getCoord() {
        return coord;
    }

    public List<EquipmentDetails> getEquipmentDetails() {
        return equipmentDetails;
    }

    public Address getAddress() {
        return address;
    }

    public FareZone getFareZone() {
        return fareZone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
