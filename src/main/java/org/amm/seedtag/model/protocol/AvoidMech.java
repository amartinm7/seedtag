package org.amm.seedtag.model.protocol;

import org.amm.seedtag.model.message.Coordinates;
import org.amm.seedtag.model.message.Scan;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// avoid-mech : No debe de atacarse ningún enemigo del tipo mech
public class AvoidMech implements Protocol {

    private static final String TYPE_MECH = "mech";

    public List<Coordinates> execute(Scan[] scans){
        List<Scan> scanList = Arrays.asList(scans);
        return scanList.stream().filter(scan -> !scan.getEnemies().getType().equals(TYPE_MECH) && Protocol.getDistance(scan.getCoordinates()) < 100 ).sorted(new ScanComparator()).map(scan -> scan.getCoordinates()).collect(Collectors.toList());
    }

    // avoid-crossfire : No debe de atacarse ningún punto en el que haya algún aliado.
    public static class ScanComparator implements Comparator<Scan> {

        @Override
        public int compare(Scan scan1, Scan scan2) {
            double ds1 = Protocol.getDistance(scan1.getCoordinates());
            double ds2 = Protocol.getDistance(scan2.getCoordinates());
            int compareAllies = Integer.valueOf(scan1.getAllies()).compareTo(Integer.valueOf(scan2.getAllies()));
            if ( compareAllies != 0) {
                return compareAllies;
            } else {
                return Double.compare(ds1, ds2);
            }
        }

    }

}
