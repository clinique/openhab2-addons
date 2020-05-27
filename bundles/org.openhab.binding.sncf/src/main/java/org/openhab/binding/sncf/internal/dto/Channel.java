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
public class Channel {
    @SerializedName("content_type")
    private String contentType = "";

    @SerializedName("id")
    private String id = "";

    /**
     * Gets or Sets types
     */
    public enum TypesEnum {
        UNDEFINED("undefined"),
        @SerializedName("web")
        WEB("web"),

        @SerializedName("sms")
        SMS("sms"),

        @SerializedName("email")
        EMAIL("email"),

        @SerializedName("mobile")
        MOBILE("mobile"),

        @SerializedName("notification")
        NOTIFICATION("notification"),

        @SerializedName("twitter")
        TWITTER("twitter"),

        @SerializedName("facebook")
        FACEBOOK("facebook"),

        @SerializedName("unknown_type")
        UNKNOWN_TYPE("unknown_type"),

        @SerializedName("title")
        TITLE("title"),

        @SerializedName("beacon")
        BEACON("beacon");

        private String value;

        TypesEnum(String value) {
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

    @SerializedName("types")
    private List<TypesEnum> types = new ArrayList<>();

    @SerializedName("name")
    private String name = "";

    /**
     * Get contentType
     * 
     * @return contentType
     **/
    public String getContentType() {
        return contentType;
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
     * Get types
     * 
     * @return types
     **/
    public List<TypesEnum> getTypes() {
        return types;
    }

    /**
     * Get name
     * 
     * @return name
     **/
    public String getName() {
        return name;
    }

}
