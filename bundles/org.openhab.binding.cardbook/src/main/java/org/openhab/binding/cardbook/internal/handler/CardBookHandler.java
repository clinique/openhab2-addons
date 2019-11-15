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
package org.openhab.binding.cardbook.internal.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.library.types.RawType;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.types.Command;
import org.openhab.binding.cardbook.internal.DirectoryConfiguration;
import org.openhab.binding.cardbook.internal.dto.Contact;
import org.openhab.binding.cardbook.internal.dto.Email;
import org.openhab.binding.cardbook.internal.dto.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sourceforge.cardme.engine.VCardEngine;
import net.sourceforge.cardme.vcard.VCard;
import net.sourceforge.cardme.vcard.exceptions.VCardParseException;
import net.sourceforge.cardme.vcard.types.BDayType;
import net.sourceforge.cardme.vcard.types.EmailType;
import net.sourceforge.cardme.vcard.types.PhotoType;
import net.sourceforge.cardme.vcard.types.TelType;
import net.sourceforge.cardme.vcard.types.params.EmailParamType;
import net.sourceforge.cardme.vcard.types.params.TelParamType;

/**
 * The {@link CardBookHandler} is for getting informations from a vCard file
 * and making them available to OH2
 *
 * @author Gaël L'hopital - Initial contribution
 */
@NonNullByDefault
public abstract class CardBookHandler extends BaseThingHandler {
    private final Logger logger = LoggerFactory.getLogger(CardBookHandler.class);
    private final VCardEngine vCardEngine = new VCardEngine();
    private final List<Contact> contactList = new ArrayList<Contact>();
    private @NonNullByDefault({}) DirectoryConfiguration config;

    public CardBookHandler(Thing thing) {
        super(thing);
    }

    protected abstract List<String> getRawCards();

    private final Runnable updateCards = new Runnable() {
        @Override
        public void run() {
            logger.info("Loading contacts");
            List<String> rawContacts = getRawCards();

            final List<Contact> contacts = new ArrayList<Contact>();
            rawContacts.forEach(rawData -> {
                try {
                    VCard vcard = vCardEngine.parse(rawData);
                    Contact contact = new Contact(Integer.toString(rawData.hashCode()), vcard.getN().getGivenName(),
                            vcard.getN().getFamilyName());
                    if (vcard.hasBDay()) {
                        BDayType birthday = vcard.getBDay();
                        contact.setBirthday(birthday.getBirthday());
                    }
                    if (vcard.hasTels()) {
                        for (TelType telType : vcard.getTels()) {
                            PhoneNumber telephone = new PhoneNumber();
                            StringBuilder sb = new StringBuilder();
                            if (telType.getParams() != null) {
                                for (TelParamType telParamType : telType.getParams()) {
                                    if (sb.length() > 0) {
                                        sb.append(", ");
                                    }
                                    sb.append(telParamType.getDescription());
                                }
                            }
                            telephone.setType(sb.toString());
                            telephone.setNumber(telType.getTelephone());
                            contact.getPhoneNumbers().add(telephone);
                        }
                    }
                    if (vcard.hasEmails()) {
                        for (EmailType emailType : vcard.getEmails()) {
                            Email email = new Email();
                            StringBuilder sb = new StringBuilder();
                            if (emailType.getParams() != null) {
                                for (EmailParamType emailParamType : emailType.getParams()) {
                                    if (sb.length() > 0) {
                                        sb.append(", ");
                                    }
                                    sb.append(emailParamType.getDescription());
                                }
                            }
                            email.setType(sb.toString());
                            email.setEmail(emailType.getEmail());
                            contact.getEmails().add(email);
                            if (vcard.hasCategories()) {
                                contact.getCategories().addAll(vcard.getCategories().getCategories());
                            }
                        }
                    }
                    if (vcard.hasPhotos()) {
                        PhotoType photo = vcard.getPhotos().get(0);
                        contact.setPhoto(new RawType(photo.getPhoto(), "application/octet-stream"));
                    }
                    logger.debug("found contact: {}", contact.getFullName());
                    contacts.add(contact);
                } catch (IOException | VCardParseException e) {
                    logger.warn("Error decoding vCard : {}", e.getMessage());
                }

            });

            logger.info("Number of contacts loaded : {}, now filtering", contacts.size());
            String match = config.matchCategory;
            Predicate<Contact> isQualified = c -> c.hasFullName()
                    && (c.hasBirthday() || c.hasEmails() || c.hasPhoneNumbers())
                    && (match.isEmpty() || c.ofCategory(match));

            contactList.clear();
            contactList.addAll(contacts.stream().filter(isQualified).collect(Collectors.toList()));

            logger.info("Number of contacts kept : {}.", contactList.size());

        }
    };

    @Override
    public void initialize() {
        logger.debug("Initializing thing {}", getThing().getUID());
        config = getConfigAs(DirectoryConfiguration.class);
        // scheduler.scheduleWithFixedDelay(updateCards, 0, config.refresh, TimeUnit.HOURS);
        updateStatus(ThingStatus.ONLINE);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        // TODO Auto-generated method stub

    }

}
