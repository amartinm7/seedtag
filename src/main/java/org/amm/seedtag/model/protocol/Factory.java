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
        map.put(Key.CLOSEST_ENEMIES, org.amm.seedtag.model.protocol.ClosestEnemies.class);
        map.put(Key.FURTHEST_ENEMIES, FurthestEnemies.class);
        map.put(Key.ASSIST_ALLIES, org.amm.seedtag.model.protocol.AssistAllies.class);
        map.put(Key.AVOID_CROSSFIRE, org.amm.seedtag.model.protocol.AvoidCrossfire.class);
        map.put(Key.PRIORITIZE_MECH, org.amm.seedtag.model.protocol.PrioritizeMech.class);
        map.put(Key.AVOID_MECH, org.amm.seedtag.model.protocol.AvoidMech.class);
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

    public static class Key{
        public static final String CLOSEST_ENEMIES = "closest-enemies";
        public static final String FURTHEST_ENEMIES ="furthest-enemies";
        public static final String ASSIST_ALLIES = "assist-allies";
        public static final String AVOID_CROSSFIRE = "avoid-crossfire";
        public static final String PRIORITIZE_MECH = "prioritize-mech";
        public static final String AVOID_MECH = "avoid-mech";
    }
}
