/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
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
package org.openhab.binding.cardbook.internal.dto;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * The {@link PhoneNumber} is responsible for storing Phone number data.
 *
 * @author Gaël L'hopital - Initial contribution
 */
@NonNullByDefault
public class PhoneNumber {
    private String number = "";
    private String type = "";

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
