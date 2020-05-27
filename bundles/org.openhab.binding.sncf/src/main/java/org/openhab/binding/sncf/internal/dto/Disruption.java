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
public class Disruption {
    /**
     * Gets or Sets status
     */
    public enum StatusEnum {
        UNDEFINED("undefined"),
        @SerializedName("past")
        PAST("past"),

        @SerializedName("active")
        ACTIVE("active"),

        @SerializedName("future")
        FUTURE("future");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    @SerializedName("status")
    private StatusEnum status = StatusEnum.UNDEFINED;

    @SerializedName("category")
    private String category = "";

    @SerializedName("severity")
    private Severity severity = new Severity();

    @SerializedName("tags")
    private List<String> tags = new ArrayList<>();

    @SerializedName("messages")
    private List<Message> messages = new ArrayList<>();

    @SerializedName("application_periods")
    private List<Period> applicationPeriods = new ArrayList<>();

    @SerializedName("impact_id")
    private String impactId = "";

    @SerializedName("disruption_id")
    private String disruptionId = "";

    @SerializedName("updated_at")
    private String updatedAt = "";

    @SerializedName("uri")
    private String uri = "";

    @SerializedName("impacted_objects")
    private List<Impacted> impactedObjects = new ArrayList<>();

    @SerializedName("id")
    private String id = "";

    @SerializedName("disruption_uri")
    private String disruptionUri = "";

    @SerializedName("contributor")
    private String contributor = "";

    @SerializedName("cause")
    private String cause = "";

    @SerializedName("properties")
    private List<DisruptionProperty> properties = new ArrayList<>();

    /**
     * Get status
     *
     * @return status
     **/
    public StatusEnum getStatus() {
        return status;
    }

    /**
     * Get category
     *
     * @return category
     **/
    public String getCategory() {
        return category;
    }

    /**
     * Get severity
     *
     * @return severity
     **/
    public Severity getSeverity() {
        return severity;
    }

    /**
     * Get tags
     *
     * @return tags
     **/
    public List<String> getTags() {
        return tags;
    }

    /**
     * Get messages
     *
     * @return messages
     **/
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Get applicationPeriods
     *
     * @return applicationPeriods
     **/
    public List<Period> getApplicationPeriods() {
        return applicationPeriods;
    }

    /**
     * Get impactId
     *
     * @return impactId
     **/
    public String getImpactId() {
        return impactId;
    }

    /**
     * Get disruptionId
     *
     * @return disruptionId
     **/
    public String getDisruptionId() {
        return disruptionId;
    }

    /**
     * Get updatedAt
     *
     * @return updatedAt
     **/
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Get uri
     *
     * @return uri
     **/
    public String getUri() {
        return uri;
    }

    /**
     * Get impactedObjects
     *
     * @return impactedObjects
     **/
    public List<Impacted> getImpactedObjects() {
        return impactedObjects;
    }

    /**
     * Get id
     *
     * @return id
     **/
    public String getId() {
        return id;
    }

    /**
     * Get disruptionUri
     *
     * @return disruptionUri
     **/
    public String getDisruptionUri() {
        return disruptionUri;
    }

    /**
     * Get contributor
     *
     * @return contributor
     **/
    public String getContributor() {
        return contributor;
    }

    /**
     * Get cause
     *
     * @return cause
     **/
    public String getCause() {
        return cause;
    }

    /**
     * Get properties
     *
     * @return properties
     **/
    public List<DisruptionProperty> getProperties() {
        return properties;
    }

}
