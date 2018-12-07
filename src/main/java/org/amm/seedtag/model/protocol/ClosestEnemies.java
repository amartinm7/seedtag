package org.amm.seedtag.model.protocol;

import org.amm.seedtag.model.message.Coordinates;
import org.amm.seedtag.model.message.Scan;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ClosestEnemies implements Protocol {

    public List<Coordinates> execute(Scan[] scans){
        List<Scan> scanList = Arrays.asList(scans);
        return scanList.stream().filter(scan -> Protocol.getDistance(scan.getCoordinates()) < 100).sorted(new ScanComparator()).map(scan -> scan.getCoordinates()).collect(Collectors.toList());
    }

    // closest-enemies : Se deberá priorizar el punto más cercano en el que haya enemigos.
    public static class ScanComparator implements Comparator<Scan> {

        @Override
        public int compare(Scan scan1, Scan scan2) {
            double ds1 = Protocol.getDistance(scan1.getCoordinates());
            double ds2 = Protocol.getDistance(scan2.getCoordinates());
            int compareDistances = Double.compare(ds1, ds2);
            if (compareDistances != 0) {
                return compareDistances;
            } else {
                int compareEnemies = Integer.valueOf(scan1.getEnemies().getNumber()).compareTo(Integer.valueOf(scan2.getEnemies().getNumber()));
                return -compareEnemies;
            }
        }
    }
}
