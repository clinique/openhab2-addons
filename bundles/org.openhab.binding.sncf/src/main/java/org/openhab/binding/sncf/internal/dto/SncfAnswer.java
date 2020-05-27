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

/**
 * The {@link SncfAnswer} is the base class for all Sncf API requests
 *
 * @author Gaël L'hopital - Initial contribution
 */
@NonNullByDefault
public abstract class SncfAnswer {
    private Pagination pagination = new Pagination();
    private List<LinkSchema> links = new ArrayList<>();
    private List<Disruption> disruptions = new ArrayList<>();
    private List<Note> notes = new ArrayList<>();
    private List<FeedPublisher> feedPublishers = new ArrayList<>();
    private Context context = new Context();
    private Error error = new Error();

    public Pagination getPagination() {
        return pagination;
    }

    public List<LinkSchema> getLinks() {
        return links;
    }

    public List<Disruption> getDisruptions() {
        return disruptions;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public List<FeedPublisher> getFeedPublishers() {
        return feedPublishers;
    }

    public Context getContext() {
        return context;
    }

    public Error getError() {
        return error;
    }
}
