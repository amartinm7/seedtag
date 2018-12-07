package org.amm.seedtag.model.protocol;

import org.amm.seedtag.model.message.Coordinates;
import org.amm.seedtag.model.message.Scan;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// avoid-mech : No debe de atacarse ningún enemigo del tipo mech
public class AvoidMech implements Protocol {

    private static final String TYPE_MECH = "mech";

    public Scan[] process (Scan[] scans) {
        List<Scan> scanList = Arrays.asList(scans);
        return scanList.stream().filter(scan -> !scan.getEnemies().getType().equals(TYPE_MECH) && Protocol.getDistance(scan.getCoordinates()) < 100 ).toArray(Scan[]::new);
    }

    public List<Coordinates> execute(Scan[] scans){
        List<Scan> scanList = Arrays.asList(scans);
        return scanList.stream().filter(scan -> !scan.getEnemies().getType().equals(TYPE_MECH) && Protocol.getDistance(scan.getCoordinates()) < 100 ).map(scan -> scan.getCoordinates()).collect(Collectors.toList());
    }
}
