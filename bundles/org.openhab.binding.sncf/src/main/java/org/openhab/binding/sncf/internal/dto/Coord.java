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

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.google.gson.annotations.SerializedName;

@NonNullByDefault
public class Coord {
    @SerializedName("lat")
    private String lat = "";

    @SerializedName("lon")
    private String lon = "";

    /**
     * Get lat
     * 
     * @return lat
     **/
    public String getLat() {
        return lat;
    }

    /**
     * Get lon
     * 
     * @return lon
     **/
    public String getLon() {
        return lon;
    }
}
