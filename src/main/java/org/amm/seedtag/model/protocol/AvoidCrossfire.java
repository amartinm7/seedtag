package org.amm.seedtag.model.protocol;

import org.amm.seedtag.model.message.Scan;
import java.util.*;

// avoid-crossfire : No debe de atacarse ningún punto en el que haya algún aliado.
public class AvoidCrossfire implements Protocol {

    public Scan[] process (Scan[] scans){
        List<Scan> scanList = Arrays.asList(scans);
        return scanList.stream().filter(scan -> scan.getAllies() == 0 && Protocol.getDistance(scan.getCoordinates()) < 100 ).sorted(new ScanComparator()).toArray(Scan[]::new);
    }

    public static class ScanComparator implements Comparator<Scan> {

        @Override
        public int compare(Scan scan1, Scan scan2) {
            double ds1 = Protocol.getDistance(scan1.getCoordinates());
            double ds2 = Protocol.getDistance(scan2.getCoordinates());
            int compareEnemies = Integer.valueOf(scan1.getEnemies().getNumber()).compareTo(Integer.valueOf(scan2.getEnemies().getNumber()));
            if ( compareEnemies != 0) {
                return -(compareEnemies);
            } else {
                return Double.compare(ds1, ds2);
            }
        }

    }

}
