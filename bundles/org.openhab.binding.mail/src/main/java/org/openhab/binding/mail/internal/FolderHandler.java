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
package org.openhab.binding.mail.internal;

import static org.openhab.binding.mail.internal.MailBindingConstants.PUBLISH_TRIGGER;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.event.MessageCountEvent;
import javax.mail.event.MessageCountListener;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.types.Command;
import org.openhab.binding.mail.internal.config.FolderConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link FolderHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Gaël L'hopital - Initial contribution
 */
@NonNullByDefault
public class FolderHandler extends BaseThingHandler implements MessageCountListener {
    private final Logger logger = LoggerFactory.getLogger(FolderHandler.class);

    private @NonNullByDefault({}) FolderConfig config;

    public FolderHandler(Thing thing) {
        super(thing);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
    }

    @Override
    public void initialize() {
        config = getConfigAs(FolderConfig.class);
        Bridge bridge = getBridge();
        if (bridge instanceof IMAPHandler) {
            IMAPHandler imapHandler = (IMAPHandler) bridge;
            if (bridge.getStatus() == ThingStatus.ONLINE) {
                String folderName = config.folder;
                try {
                    imapHandler.setFolderObserver(this, folderName);
                    updateStatus(ThingStatus.ONLINE);
                } catch (MessagingException e) {
                    logger.debug("setting device offline : {}", e.getMessage());
                    updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR, e.getMessage());
                }
            } else {
                logger.debug("setting device offline (bridge is offline)");
                updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.BRIDGE_OFFLINE);
            }
        } else {
            logger.debug("setting device offline (bridge is not an IMAP server)");
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR);
        }
    }

    @Override
    public void dispose() {
        Bridge bridge = getBridge();
        if (bridge instanceof IMAPHandler) {
            IMAPHandler imapHandler = (IMAPHandler) bridge;
            try {
                imapHandler.unsetFolderObserver(this);
            } catch (MessagingException e) {
                logger.warn("Error disposing folder observer : {}", e.getMessage());
            }
        }
    }

    @Override
    public void messagesAdded(@Nullable MessageCountEvent event) {
        for (Message message : event.getMessages()) {
            try {
                triggerChannel(PUBLISH_TRIGGER, message.getSubject());
            } catch (MessagingException e) {
                logger.warn("Error reading mail : {}", e.getMessage());
            }
        }
    }

    @Override
    public void messagesRemoved(@Nullable MessageCountEvent event) {

    }

}
