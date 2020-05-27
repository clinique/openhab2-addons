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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.google.gson.annotations.SerializedName;

@NonNullByDefault
public class Poi {
    @SerializedName("poi_type")
    private PoiType poiType = new PoiType();

    @SerializedName("name")
    private String name = "";

    @SerializedName("car_park")
    private CarPark carPark = new CarPark();

    @SerializedName("coord")
    private Coord coord = new Coord();

    @SerializedName("label")
    private String label = "";

    @SerializedName("administrative_regions")
    private List<Admin> administrativeRegions = new ArrayList<>();

    @SerializedName("address")
    private Address address = new Address();

    @SerializedName("id")
    private String id = "";

    @SerializedName("properties")
    private Map<String, String> properties = new HashMap<>();

    @SerializedName("stands")
    private Stands stands = new Stands();

    /**
     * Get poiType
     * 
     * @return poiType
     **/
    public PoiType getPoiType() {
        return poiType;
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
     * Get carPark
     * 
     * @return carPark
     **/
    public CarPark getCarPark() {
        return carPark;
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
     * Get address
     * 
     * @return address
     **/
    public Address getAddress() {
        return address;
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
     * Get properties
     * 
     * @return properties
     **/
    public Map<String, String> getProperties() {
        return properties;
    }

    /**
     * Get stands
     * 
     * @return stands
     **/
    public Stands getStands() {
        return stands;
    }

}
