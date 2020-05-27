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
public class ImpactedSection {
    @SerializedName("routes")
    private List<Route> routes = new ArrayList<>();

    @SerializedName("to")
    private PtObject to = new PtObject();

    @SerializedName("from")
    private PtObject from = new PtObject();

    /**
     * Get routes
     * 
     * @return routes
     **/
    public List<Route> getRoutes() {
        return routes;
    }

    /**
     * Get to
     * 
     * @return to
     **/
    public PtObject getTo() {
        return to;
    }

    /**
     * Get from
     * 
     * @return from
     **/
    public PtObject getFrom() {
        return from;
    }

}
