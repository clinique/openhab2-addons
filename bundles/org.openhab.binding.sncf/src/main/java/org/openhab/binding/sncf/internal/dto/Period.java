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
public class Period {
    @SerializedName("begin")
    private String begin = "";

    @SerializedName("end")
    private String end = "";

    /**
     * Get begin
     * 
     * @return begin
     **/
    public String getBegin() {
        return begin;
    }

    /**
     * Get end
     * 
     * @return end
     **/
    public String getEnd() {
        return end;
    }

}
