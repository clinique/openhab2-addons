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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.google.gson.annotations.SerializedName;

@NonNullByDefault
public class Address {
    @SerializedName("name")
    private String name = "";

    @SerializedName("house_number")
    private int houseNumber = UNDEFINED;

    @SerializedName("coord")
    private Coord coord = new Coord();

    @SerializedName("label")
    private String label = "";

    @SerializedName("administrative_regions")
    private List<Admin> administrativeRegions = new ArrayList<>();

    @SerializedName("id")
    private String id = "";

    /**
     * Name of the object
     *
     * @return name
     **/
    public String getName() {
        return name;
    }

    /**
     * Get houseNumber
     *
     * @return houseNumber
     **/
    public int getHouseNumber() {
        return houseNumber;
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
     * Get label
     *
     * @return label
     **/
    public String getLabel() {
        return label;
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
     * Identifier of the object
     *
     * @return id
     **/
    public String getId() {
        return id;
    }

}
