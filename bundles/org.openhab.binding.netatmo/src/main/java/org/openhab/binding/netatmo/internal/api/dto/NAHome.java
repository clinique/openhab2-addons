/**
 * Copyright (c) 2010-2021 Contributors to the openHAB project
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
package org.openhab.binding.netatmo.internal.api.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.netatmo.internal.api.NetatmoConstants.SetpointMode;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.PointType;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Gaël L'hopital - Initial contribution
 *
 */

@NonNullByDefault
public class NAHome extends NADevice {
    private @Nullable NAObjectMap<NAPerson> persons;
    private List<NAHomeEvent> events = List.of();
    private List<NAThermProgram> schedules = List.of();
    private List<NAWelcome> cameras = List.of();
    private List<NARoom> rooms = List.of();
    private @Nullable SetpointMode thermMode;
    private long thermModeEndtime = 0;
    private int thermSetpointDefaultDuration;
    @SerializedName("coordinates")
    private double[] location = {};
    private double altitude;

    public List<NAThermProgram> getThermSchedules() {
        return schedules;
    }

    public @Nullable NAThermProgram getActiveProgram() {
        return schedules.stream().filter(NAThermProgram::isSelected).findFirst().orElse(null);
    }

    public long getThermModeEndTime() {
        return thermModeEndtime;
    }

    public int getThermSetpointDefaultDuration() {
        return thermSetpointDefaultDuration;
    }

    public @Nullable PointType getLocation() {
        if (location.length == 2) {
            return new PointType(new DecimalType(location[1]), new DecimalType(location[0]), new DecimalType(altitude));
        }
        return null;
    }

    public @Nullable SetpointMode getThermMode() {
        return thermMode;
    }

    public @Nullable NAObjectMap<NAPerson> getPersons() {
        return persons;
    }

    public @Nullable List<NAPerson> getKnownPersons() {
        NAObjectMap<NAPerson> personList = persons;
        if (personList != null) {
            return personList.values().stream().filter(person -> person.getName() != null).collect(Collectors.toList());
        }
        return null;
    }

    public List<NAHomeEvent> getEvents() {
        return events;
    }

    public List<NAWelcome> getCameras() {
        return cameras;
    }

    public List<NARoom> getRooms() {
        return rooms;
    }

    public @Nullable NARoom getRoom(String id) {
        return rooms.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
    }

    public Optional<NAPerson> getPerson(String id) {
        NAObjectMap<NAPerson> personList = persons;
        return personList == null ? Optional.empty() : Optional.ofNullable(personList.get(id));
    }

    public void setEvents(List<NAHomeEvent> events) {
        this.events = events;
    }
}
