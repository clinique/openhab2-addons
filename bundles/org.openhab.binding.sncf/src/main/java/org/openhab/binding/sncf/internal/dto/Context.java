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
public class Context {
    @SerializedName("timezone")
    private String timezone = "";

    @SerializedName("current_datetime")
    private String currentDatetime = "";

    @SerializedName("car_direct_path")
    private CO2 carDirectPath = new CO2();

    /**
     * Timezone of any datetime in the response, default value Africa/Abidjan (UTC)
     *
     * @return timezone
     **/
    public String getTimezone() {
        return timezone;
    }

    /**
     * The datetime of the request (considered as \"now\")
     *
     * @return currentDatetime
     **/
    public String getCurrentDatetime() {
        return currentDatetime;
    }

    /**
     * Get carDirectPath
     *
     * @return carDirectPath
     **/
    public CO2 getCarDirectPath() {
        return carDirectPath;
    }

}
