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
package org.openhab.binding.netatmo.internal.api.dto.energy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.jdt.annotation.Nullable;

/**
 * @author Bernhard Kreuz - Initial contribution
 */
public class Home implements Serializable {

    private String id;
    private List<Module> modules = new ArrayList<>();
    private List<Room> rooms = new ArrayList<>();
    private Map<String, Object> additionalProperties = new HashMap<>();
    private final static long serialVersionUID = -330826367376419729L;

    /**
     * No args constructor for use in serialization
     * 
     */
    // public Home() {
    // }

    /**
     * 
     * @param rooms
     * @param id
     * @param modules
     */
    public Home(String id, List<Module> modules, List<Room> rooms) {
        super();
        this.id = id;
        this.modules = modules;
        this.rooms = rooms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Home withId(String id) {
        this.id = id;
        return this;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public Home withModules(List<Module> modules) {
        this.modules = modules;
        return this;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Home withRooms(List<Room> rooms) {
        this.rooms = rooms;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Home withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public @Nullable Module getNAPlug() {
        return modules.stream().filter(m -> m.getType().equals("NAPlug")).findFirst().orElse(null);
    }

    public List<Module> getNRVs() {
        return modules.stream().filter(m -> m.getType().equals("NRV")).collect(Collectors.toList());
    }
}
