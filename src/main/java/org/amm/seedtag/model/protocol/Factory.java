package org.amm.seedtag.model.protocol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Factory {

    private static final Logger LOGGER = LoggerFactory.getLogger(Factory.class);

    private Map<String,Class<? extends Protocol>> map = new HashMap<>();

    private static Factory instance = new Factory();

    private Factory(){
        map.put("closest-enemies", org.amm.seedtag.model.protocol.ClosestEnemies.class);
        map.put("furthest-enemies", FurthestEnemies.class);
        map.put("assist-allies", org.amm.seedtag.model.protocol.AssistAllies.class);
        map.put("avoid-crossfire", org.amm.seedtag.model.protocol.AvoidCrossfire.class);
        map.put("prioritize-mech", org.amm.seedtag.model.protocol.PrioritizeMech.class);
        map.put("avoid-mech", org.amm.seedtag.model.protocol.AvoidMech.class);
    }

    public static Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public Protocol getProtocol(String protocol) {
        if ( !map.containsKey(protocol)){
            LOGGER.error(String.format("Invalid protocol %s",protocol));
            throw new RuntimeException(String.format("Invalid protocol %s",protocol));
        }
        try {
            return map.get(protocol).newInstance();
        } catch (Exception e) {
            LOGGER.error(String.format("Internal Error, invalid mapped classes %s", e.getMessage()));
            throw new RuntimeException(String.format("Internal Error, invalid mapped classes %s", e.getMessage()));
        }


    }
}
