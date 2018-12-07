package org.amm.seedtag.model.protocol;

import org.amm.seedtag.model.message.Coordinates;
import org.amm.seedtag.model.message.Scan;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ClosestEnemies implements Protocol {

    public List<Coordinates> execute(Scan[] scans){
        List<Scan> scanList = Arrays.asList(scans);
        return scanList.stream().sorted(new ScanComparator()).map(scan -> scan.getCoordinates()).collect(Collectors.toList());
    }

    // closest-enemies : Se deberá priorizar el punto más cercano en el que haya enemigos.
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
