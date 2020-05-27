/**
 * Copyright (c) 2010-2020 Contributors to the openHAB project
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
package org.openhab.binding.sncf.internal.handler;

import static org.openhab.binding.sncf.internal.SncfBindingConstants.JSON_CONTENT_TYPE;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.library.types.PointType;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.binding.BaseBridgeHandler;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.io.net.http.HttpUtil;
import org.openhab.binding.sncf.internal.SncfException;
import org.openhab.binding.sncf.internal.config.SncfBridgeConfiguration;
import org.openhab.binding.sncf.internal.dto.Arrivals;
import org.openhab.binding.sncf.internal.dto.Departures;
import org.openhab.binding.sncf.internal.dto.Passage;
import org.openhab.binding.sncf.internal.dto.PlaceNearby;
import org.openhab.binding.sncf.internal.dto.PlacesNearby;
import org.openhab.binding.sncf.internal.dto.SncfAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSyntaxException;

/**
 * The {@link SncfBridgeHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Gaël L'hopital - Initial contribution
 */
@NonNullByDefault
public class SncfBridgeHandler extends BaseBridgeHandler {
    private static final int REQUEST_TIMEOUT = (int) TimeUnit.SECONDS.toMillis(20);
    private static final DateTimeFormatter NAVITIA_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmssZ");
    private final Logger logger = LoggerFactory.getLogger(SncfBridgeHandler.class);
    private final Properties httpHeader = new Properties();
    private final Gson gson;
    private @NonNullByDefault({}) SncfBridgeConfiguration configuration;

    public SncfBridgeHandler(Bridge bridge) {
        super(bridge);
        gson = new GsonBuilder()
                .registerTypeAdapter(ZonedDateTime.class,
                        (JsonDeserializer<ZonedDateTime>) (json, type, jsonDeserializationContext) -> ZonedDateTime
                                .parse(json.getAsJsonPrimitive().getAsString().concat("+0200"), NAVITIA_DATE_FORMAT))
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    @Override
    public void initialize() {
        logger.debug("Initializing VolvoOnCall API bridge handler.");
        configuration = getConfigAs(SncfBridgeConfiguration.class);
        httpHeader.setProperty("Authorization", configuration.username);
        updateStatus(ThingStatus.ONLINE);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        logger.debug("SNCF Bridge is read-only and does not handle commands");
    }

    public synchronized <T> T getURL(String url, Class<T> objectClass) throws SncfException {
        try {
            String jsonResponse = HttpUtil.executeUrl("GET", url, httpHeader, null, JSON_CONTENT_TYPE, REQUEST_TIMEOUT);
            logger.debug("Request for : {}", url);
            logger.debug("Received : {}", jsonResponse);
            T response = gson.fromJson(jsonResponse, objectClass);
            return response;
        } catch (JsonSyntaxException | IOException e) {
            throw new SncfException(e);
        }
    }

    public List<PlaceNearby> discoverNearby(PointType location, int distance) {
        String URL = "https://api.sncf.com/v1/coverage/sncf/coord/%.5f;%.5f/places_nearby?distance=%d&type[]=stop_point&count=100";
        try {
            PlacesNearby places = getURL(String.format(Locale.US, URL, location.getLongitude().floatValue(),
                    location.getLatitude().floatValue(), distance), PlacesNearby.class);
            return places.getPlacesNearby();
        } catch (SncfException e) {
        }
        return new ArrayList<>();
    }

    public List<Passage> getDepartures(String stopPointId) {
        return getPassages(stopPointId, Departures.class);
    }

    public List<Passage> getArrivals(String stopPointId) {
        return getPassages(stopPointId, Arrivals.class);
    }

    public <T extends SncfAnswer> List<Passage> getPassages(String stopPointId, Class<T> passageClass) {
        String URL = "https://api.sncf.com/v1/coverage/sncf/stop_points/%s/%s?disable_geojson=true&count=2";
        try {
            T passages = getURL(String.format(Locale.US, URL, stopPointId,
                    passageClass == Departures.class ? "departures" : "arrivals"), passageClass);
            if (passages instanceof Departures) {
                return ((Departures) passages).getDepartures();
            } else if (passages instanceof Arrivals) {
                return ((Arrivals) passages).getArrivals();
            }
        } catch (SncfException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
