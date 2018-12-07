package org.amm.seedtag.model.protocol;

import org.amm.seedtag.model.message.Coordinates;
import org.amm.seedtag.model.message.Enemies;
import org.amm.seedtag.model.message.Scan;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;


public class AvoidMechTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AvoidMechTest.class);
    private String message = "Not attack to the Mech enemies, so attack to the soldiers. Skip Mechs";

    @Before
    public void init(){
        LOGGER.info(message);
    }

    @Test
    public void noMechs(){
        Coordinates c1 = new Coordinates(35,5);
        Coordinates c2 = new Coordinates(5,35);
        Enemies e1 = new Enemies(10,"soldier");
        Enemies e2 = new Enemies(20,"soldier");
        Scan scan1 = new Scan(c1,e1,3);
        Scan scan2 = new Scan(c2,e2,0);
        Scan[] scanList = new Scan[]{scan1, scan2};
        Scan[] obtainedScanList = new AvoidMech().process(scanList);
        assertTrue("two scans has to be obtained", obtainedScanList.length == 2);
        LOGGER.info(obtainedScanList.toString());
    }

    @Test
    public void filterOneMech(){
        Coordinates c1 = new Coordinates(5,5);
        Coordinates c2 = new Coordinates(20,20);
        Coordinates c3 = new Coordinates(1,1);
        Enemies e1 = new Enemies(0,"soldier");
        Enemies e2 = new Enemies(0,"mech");
        Enemies e3 = new Enemies(0,"soldier");
        Scan scan1 = new Scan(c1,e1,0);
        Scan scan2 = new Scan(c2,e2,0);
        Scan scan3 = new Scan(c3,e3,0);
        Scan[] scanList = new Scan[]{scan1, scan2, scan3};
        Scan[] obtainedScanList = new AvoidMech().process(scanList);
        assertTrue("two scans has to be obtained", obtainedScanList.length == 2);
        LOGGER.info(obtainedScanList.toString());
    }

    @Test
    public void filterAllMechs(){
        Coordinates c1 = new Coordinates(5,5);
        Coordinates c2 = new Coordinates(20,20);
        Coordinates c3 = new Coordinates(1,1);
        Enemies e1 = new Enemies(5,"mech");
        Enemies e2 = new Enemies(20,"mech");
        Enemies e3 = new Enemies(1,"mech");
        Scan scan1 = new Scan(c1,e1,1);
        Scan scan2 = new Scan(c2,e2,1);
        Scan scan3 = new Scan(c3,e3,1);
        Scan[] scanList = new Scan[]{scan1, scan2, scan3};
        Scan[] obtainedScanList = new AvoidMech().process(scanList);
        assertTrue(obtainedScanList.length == 0);
        LOGGER.info(obtainedScanList.toString());
    }
}
