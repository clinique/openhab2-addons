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

import static org.openhab.binding.sncf.internal.SncfBindingConstants.UNDEFINED;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.google.gson.annotations.SerializedName;

@NonNullByDefault
public class Pagination {
    @SerializedName("start_page")
    private int startPage = UNDEFINED;

    @SerializedName("items_on_page")
    private int itemsOnPage = UNDEFINED;

    @SerializedName("items_per_page")
    private int itemsPerPage = UNDEFINED;

    @SerializedName("total_result")
    private int totalResult = UNDEFINED;

    /**
     * Get startPage
     * 
     * @return startPage
     **/
    public int getStartPage() {
        return startPage;
    }

    /**
     * Get itemsOnPage
     * 
     * @return itemsOnPage
     **/
    public int getItemsOnPage() {
        return itemsOnPage;
    }

    /**
     * Get itemsPerPage
     * 
     * @return itemsPerPage
     **/
    public int getItemsPerPage() {
        return itemsPerPage;
    }

    /**
     * Get totalResult
     * 
     * @return totalResult
     **/
    public int getTotalResult() {
        return totalResult;
    }

}
