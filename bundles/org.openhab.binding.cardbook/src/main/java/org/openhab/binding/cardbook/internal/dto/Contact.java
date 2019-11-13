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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.library.types.RawType;

/**
 * The {@link Contact} is responsible for storing Contact data.
 *
 * @author Gaël L'hopital - Initial contribution
 */
@NonNullByDefault
public class Contact {
    private final List<PhoneNumber> phoneNumbers = new ArrayList<>();
    private final List<Email> emails = new ArrayList<>();
    private final List<String> categories = new ArrayList<>();
    private final List<Address> addressList = new ArrayList<>();
    private final String id;
    private final @Nullable String firstName;
    private final @Nullable String lastName;
    private LocalDate birthday = LocalDate.MAX;
    private @Nullable RawType photo;

    public Contact(String id, String fName, String lName) {
        this.id = id;
        this.firstName = fName;
        this.lastName = lName;
    }

    public String getId() {
        return id;
    }

    public @Nullable RawType getPhoto() {
        return photo;
    }

    public void setPhoto(RawType photo) {
        this.photo = photo;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public List<Address> getAddresses() {
        return addressList;
    }

    public String getFullName() {
        return (firstName + " " + lastName).trim();
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = LocalDateTime.ofInstant(birthday.toInstant(), birthday.getTimeZone().toZoneId()).toLocalDate();
    }

    public boolean hasBirthday() {
        return birthday != LocalDate.MAX;
    }

    public boolean hasFullName() {
        return getFullName().length() > 0;
    }

    public boolean hasPhoneNumbers() {
        return !phoneNumbers.isEmpty();
    }

    public boolean hasEmails() {
        return !emails.isEmpty();
    }

    public boolean ofCategory(String category) {
        return categories.indexOf(category) != -1;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public List<String> getCategories() {
        return categories;
    }

}
