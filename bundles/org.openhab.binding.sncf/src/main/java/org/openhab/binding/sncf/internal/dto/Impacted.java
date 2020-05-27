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
public class Impacted {
    @SerializedName("impacted_stops")
    private List<ImpactedStop> impactedStops = new ArrayList<>();

    @SerializedName("pt_object")
    private PtObject ptObject = new PtObject();

    @SerializedName("impacted_section")
    private ImpactedSection impactedSection = new ImpactedSection();

    /**
     * Get impactedStops
     * 
     * @return impactedStops
     **/
    public List<ImpactedStop> getImpactedStops() {
        return impactedStops;
    }

    /**
     * Get ptObject
     * 
     * @return ptObject
     **/
    public PtObject getPtObject() {
        return ptObject;
    }

    /**
     * Get impactedSection
     * 
     * @return impactedSection
     **/
    public ImpactedSection getImpactedSection() {
        return impactedSection;
    }

}
