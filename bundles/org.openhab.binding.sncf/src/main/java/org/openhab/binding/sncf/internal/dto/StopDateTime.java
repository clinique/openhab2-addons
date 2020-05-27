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

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.google.gson.annotations.SerializedName;

@NonNullByDefault
public class StopDateTime {
    public enum AdditionalInformationsEnum {
        UNDEFINED,
        @SerializedName("pick_up_only")
        PICK_UP_ONLY,
        @SerializedName("drop_off_only")
        DROP_OFF_ONLY,
        @SerializedName("on_demand_transport")
        ON_DEMAND_TRANSPORT,
        @SerializedName("date_time_estimated")
        DATE_TIME_ESTIMATED;
    }

    public enum DataFreshnessEnum {
        UNDEFINED,
        @SerializedName("base_schedule")
        BASE_SCHEDULE,
        @SerializedName("adapted_schedule")
        ADAPTED_SCHEDULE,
        @SerializedName("realtime")
        REALTIME;
    }

    private StopPoint stopPoint = new StopPoint();
    private List<LinkSchema> links = new ArrayList<>();
    private List<AdditionalInformationsEnum> additionalInformations = new ArrayList<>();
    private @NonNullByDefault({}) ZonedDateTime arrivalDateTime;
    private @NonNullByDefault({}) ZonedDateTime departureDateTime;
    private @NonNullByDefault({}) ZonedDateTime baseArrivalDateTime;
    private @NonNullByDefault({}) ZonedDateTime baseDepartureDateTime;
    private DataFreshnessEnum dataFreshness = DataFreshnessEnum.UNDEFINED;

    public StopPoint getStopPoint() {
        return stopPoint;
    }

    public List<LinkSchema> getLinks() {
        return links;
    }

    public ZonedDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public List<AdditionalInformationsEnum> getAdditionalInformations() {
        return additionalInformations;
    }

    public ZonedDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public ZonedDateTime getBaseArrivalDateTime() {
        return baseArrivalDateTime;
    }

    public ZonedDateTime getBaseDepartureDateTime() {
        return baseDepartureDateTime;
    }

    public DataFreshnessEnum getDataFreshness() {
        return dataFreshness;
    }

}
