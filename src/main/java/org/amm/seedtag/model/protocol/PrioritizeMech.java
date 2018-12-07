package org.amm.seedtag.model.protocol;

import org.amm.seedtag.model.message.Coordinates;
import org.amm.seedtag.model.message.Scan;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PrioritizeMech implements Protocol{

    private static final String TYPE_MECH = "mech";

    public List<Coordinates> execute(Scan[] scans){
        List<Scan> scanList = Arrays.asList(scans);
        return scanList.stream().sorted(new ScanComparator()).map(scan -> scan.getCoordinates()).collect(Collectors.toList());
    }

    // prioritize-mech : Debe de atacarse un mech si se encuentra. En caso negativo, cualquier otro tipo de objetivo será válido.
    public static class ScanComparator implements Comparator<Scan> {

        @Override
        public int compare(Scan scan1, Scan scan2) {
            double ds1 = Protocol.getDistance(scan1.getCoordinates());
            double ds2 = Protocol.getDistance(scan2.getCoordinates());
            if (scan1.getEnemies().getType().equals(TYPE_MECH) && !scan2.getEnemies().getType().equals(TYPE_MECH)){
                return -1;
            }
            if (scan2.getEnemies().getType().equals(TYPE_MECH) && !scan1.getEnemies().getType().equals(TYPE_MECH)){
                return 1;
            }
            return Double.compare(ds1, ds2);
        }

    }

}
