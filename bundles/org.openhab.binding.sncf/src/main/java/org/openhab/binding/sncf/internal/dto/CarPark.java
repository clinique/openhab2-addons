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
public class CarPark {
    @SerializedName("available")
    private int available = UNDEFINED;

    @SerializedName("total_places")
    private int totalPlaces = UNDEFINED;

    @SerializedName("occupied_PRM")
    private int occupiedPRM = UNDEFINED;

    @SerializedName("occupied")
    private int occupied = UNDEFINED;

    @SerializedName("available_PRM")
    private int availablePRM = UNDEFINED;

    /**
     * Get available
     * 
     * @return available
     **/
    public int getAvailable() {
        return available;
    }

    /**
     * Get totalPlaces
     * 
     * @return totalPlaces
     **/
    public int getTotalPlaces() {
        return totalPlaces;
    }

    /**
     * Get occupiedPRM
     * 
     * @return occupiedPRM
     **/
    public int getOccupiedPRM() {
        return occupiedPRM;
    }

    /**
     * Get occupied
     * 
     * @return occupied
     **/
    public int getOccupied() {
        return occupied;
    }

    /**
     * Get availablePRM
     * 
     * @return availablePRM
     **/
    public int getAvailablePRM() {
        return availablePRM;
    }

}
