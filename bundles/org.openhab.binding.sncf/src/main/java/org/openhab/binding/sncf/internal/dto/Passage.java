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

@NonNullByDefault
public class Passage {
    private VJDisplayInformation displayInformations = new VJDisplayInformation();
    private StopPoint stopPoint = new StopPoint();
    private Route route = new Route();
    private List<LinkSchema> links = new ArrayList<>();
    private StopDateTime stopDateTime = new StopDateTime();

    public VJDisplayInformation getDisplayInformations() {
        return displayInformations;
    }

    public StopPoint getStopPoint() {
        return stopPoint;
    }

    public Route getRoute() {
        return route;
    }

    public List<LinkSchema> getLinks() {
        return links;
    }

    public StopDateTime getStopDateTime() {
        return stopDateTime;
    }

}
