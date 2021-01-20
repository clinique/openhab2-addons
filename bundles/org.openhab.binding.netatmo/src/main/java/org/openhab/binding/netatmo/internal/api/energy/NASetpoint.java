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
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.netatmo.internal.api.doc.NetatmoConstants.SetpointMode;

/**
 *
 * @author Gaël L'hopital - Initial contribution
 *
 */

@NonNullByDefault
public class NASetpoint {
    private double setpointTemp;
    private long setpointEndtime;
    private @Nullable SetpointMode setpointMode;

    public double getSetpointTemp() {
        return setpointTemp;
    }

    public long getSetpointEndtime() {
        return setpointEndtime;
    }

    public SetpointMode getMode() {
        SetpointMode mode = setpointMode;
        return mode != null ? mode : SetpointMode.UNKNOWN;
    }
}