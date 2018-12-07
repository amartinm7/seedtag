package org.amm.seedtag.model.protocol;

import org.amm.seedtag.model.message.Scan;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// assist-allies : Deberan de priorizarse los puntos en los que exista algún aliado.
public class AssistAllies implements Protocol {

    public Scan[] process (Scan[] scans){
        List<Scan> scanList = Arrays.asList(scans);
        return scanList.stream().filter(scan -> Protocol.getDistance(scan.getCoordinates()) < 100).sorted(new ScanComparator()).toArray(Scan[]::new);
    }

    public static class ScanComparator implements Comparator<Scan> {

        @Override
        public int compare(Scan scan1, Scan scan2) {
            double ds1 = Protocol.getDistance(scan1.getCoordinates());
            double ds2 = Protocol.getDistance(scan2.getCoordinates());
            int compareAllies = Integer.valueOf(scan1.getAllies()).compareTo(Integer.valueOf(scan2.getAllies()));
            if ( compareAllies != 0) {
                return -(compareAllies);
            } else {
                return Double.compare(ds1, ds2);
            }
        }

    }

}
