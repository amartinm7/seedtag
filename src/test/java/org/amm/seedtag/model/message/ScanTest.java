package org.amm.seedtag.model.message;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;


public class ScanTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScanTest.class);

    @Test
    public void test(){
        Coordinates c1 = new Coordinates(5,5);
        Coordinates c2 = new Coordinates(20,20);
        Coordinates c3 = new Coordinates(1,1);
        Enemies e1 = new Enemies(5,"soldier");
        Enemies e2 = new Enemies(20,"mech");
        Enemies e3 = new Enemies(1,"soldier");
        Scan scan1 = new Scan(c1,e1,0);
        Scan scan2 = new Scan(c2,e2,0);
        Scan scan3 = new Scan(c3,e3,0);
        List<Scan> scanList = new ArrayList<>();
        scanList.add(scan1);
        scanList.add(scan2);
        scanList.add(scan3);
        Collections.sort(scanList, new Scan.ScanComparator());
        assertEquals(c3,scanList.get(0).getCoordinates());
        LOGGER.info(scanList.toString());
    }
}
