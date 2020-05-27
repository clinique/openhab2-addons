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

@NonNullByDefault
public class LinkSchema {
    private String category = "";
    private String commentType = "";
    private String title = "";
    private boolean internal;
    private String value = "";
    private String href = "";
    private String rel = "";
    private boolean templated;
    private String type = "";
    private String id = "";

    public String getCategory() {
        return category;
    }

    public String getCommentType() {
        return commentType;
    }

    public String getTitle() {
        return title;
    }

    public boolean getInternal() {
        return internal;
    }

    public String getValue() {
        return value;
    }

    public String getHref() {
        return href;
    }

    public String getRel() {
        return rel;
    }

    public boolean getTemplated() {
        return templated;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

}
