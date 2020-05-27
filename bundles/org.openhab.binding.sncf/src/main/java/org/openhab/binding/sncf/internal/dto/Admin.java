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
public class Admin {
    @SerializedName("insee")
    private String insee = "";

    @SerializedName("name")
    private String name = "";

    @SerializedName("level")
    private int level = UNDEFINED;

    @SerializedName("coord")
    private Coord coord = new Coord();

    @SerializedName("label")
    private String label = "";

    @SerializedName("id")
    private String id = "";

    @SerializedName("zip_code")
    private String zipCode = "";

    /**
     * Get insee
     * 
     * @return insee
     **/
    public String getInsee() {
        return insee;
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
     * Get level
     * 
     * @return level
     **/
    public Integer getLevel() {
        return level;
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
     * Identifier of the object
     * 
     * @return id
     **/
    public String getId() {
        return id;
    }

    /**
     * Get zipCode
     * 
     * @return zipCode
     **/
    public String getZipCode() {
        return zipCode;
    }

}
