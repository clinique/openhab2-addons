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

import java.time.ZonedDateTime;
import java.util.Optional;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.netatmo.internal.api.EventType;

/**
 *
 * @author Gaël L'hopital - Initial contribution
 *
 */

@NonNullByDefault
public class NAOutdoorEvent {
    private EventType type = EventType.OUTDOOR;
    private @NonNullByDefault({}) ZonedDateTime time;
    private @NonNullByDefault({}) String id;
    private @Nullable Integer offset;
    private @Nullable String message;
    private @Nullable NASnapshot snapshot;

    public Optional<NASnapshot> getSnapshot() {
        return Optional.ofNullable(snapshot);
    }

    public String getMessage() {
        return (message != null ? message.replace("<b>", "").replace("</b>", "") : "");
    }

    public ZonedDateTime getTime() {
        return time;
    }
}
