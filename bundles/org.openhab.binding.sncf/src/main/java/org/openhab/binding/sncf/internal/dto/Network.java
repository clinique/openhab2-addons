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
public class Network {
    @SerializedName("codes")
    private List<Code> codes = new ArrayList<>();

    @SerializedName("id")
    private String id = "";

    @SerializedName("links")
    private List<LinkSchema> links = new ArrayList<>();

    @SerializedName("name")
    private String name = "";

    /**
     * Get codes
     * 
     * @return codes
     **/
    public List<Code> getCodes() {
        return codes;
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
     * Get links
     * 
     * @return links
     **/
    public List<LinkSchema> getLinks() {
        return links;
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
