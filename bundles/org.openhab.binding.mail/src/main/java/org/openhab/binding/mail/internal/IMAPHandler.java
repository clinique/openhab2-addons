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

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.eclipse.smarthome.core.thing.binding.BaseBridgeHandler;
import org.eclipse.smarthome.core.types.Command;
import org.openhab.binding.mail.internal.config.BaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link IMAPHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Jan N. Klug - Initial contribution
 * @author Gaël L'hopital - Changed to Bridge
 */
@NonNullByDefault
public class IMAPHandler extends BaseBridgeHandler {
    private static final String BASE_PROTOCOL = "pop3";
    private final Logger logger = LoggerFactory.getLogger(IMAPHandler.class);
    private final Map<FolderHandler, String> observers = new HashMap<>();
    private @NonNullByDefault({}) Store store;
    private String protocol = BASE_PROTOCOL;

    public IMAPHandler(Bridge bridge) {
        super(bridge);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
    }

    @Override
    public void initialize() {
        BaseConfig config = getConfigAs(BaseConfig.class);

        if (config.security == ServerSecurity.SSL) {
            protocol.concat("s");
        }

        if (config.port == 0) {
            switch (protocol) {
                case "imap":
                    config.port = 143;
                    break;
                case "imaps":
                    config.port = 993;
                    break;
            }
        }

        Properties props = new Properties();
        props.setProperty("mail." + BASE_PROTOCOL + ".starttls.enable", "true");
        props.setProperty("mail.store.protocol", protocol);
        Session session = Session.getInstance(props);

        try {
            store = session.getStore();
            store.connect(config.hostname, config.port, config.username, config.password);

        } catch (MessagingException e) {
            logger.info("error when trying to connect IMAP: {}", e.getMessage());
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR, e.getMessage());
        }
        updateStatus(ThingStatus.ONLINE);
    }

    public void setFolderObserver(FolderHandler folderHandler, String folderName) throws MessagingException {
        Folder folder = store.getFolder(folderName);
        folder.addMessageCountListener(folderHandler);
        observers.put(folderHandler, folderName);
    }

    public void unsetFolderObserver(FolderHandler folderHandler) throws MessagingException {
        String folderName = observers.get(folderHandler);
        Folder folder = store.getFolder(folderName);
        folder.removeMessageCountListener(folderHandler);
        observers.remove(folderHandler);
    }

}
