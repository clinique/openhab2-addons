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
package org.openhab.binding.netatmo.internal.api.energy;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.netatmo.internal.api.doc.NetatmoConstants.ThermostatZoneType;
import org.openhab.binding.netatmo.internal.api.dto.NAObject;

/**
 *
 * @author Gaël L'hopital - Initial contribution
 *
 */

@NonNullByDefault
public class NATimeTableItem extends NAObject {
    private int mOffset;

    public int getMOffset() {
        return mOffset;
    }

    public ThermostatZoneType getZoneType() {
        return ThermostatZoneType.fromId(getId());
    }
}