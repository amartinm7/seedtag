package org.amm.seedtag.model.protocol;

import org.amm.seedtag.model.message.Coordinates;
import org.amm.seedtag.model.message.Enemies;
import org.amm.seedtag.model.message.Scan;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class AssistAlliesTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssistAlliesTest.class);
    private String message = "prioritize the allies over the others";

    @Before
    public void init(){
        LOGGER.info(message);
    }

    @Test
    public void oneAllieGetTheRest(){
        final String message = "Return all ordered by allies to support them";
        LOGGER.info(message);
        Coordinates c1 = new Coordinates(35,5);
        Coordinates c2 = new Coordinates(5,35);
        Enemies e1 = new Enemies(10,"soldier");
        Enemies e2 = new Enemies(20,"soldier");
        Scan scan1 = new Scan(c1,e1,3);
        Scan scan2 = new Scan(c2,e2,0);
        Scan[] scanList = new Scan[]{scan1, scan2};
        Scan[] obtainedScanList = new AssistAllies().process(scanList);
        assertTrue(obtainedScanList.length == 2);
        assertEquals(c1, obtainedScanList[0].getCoordinates());
        LOGGER.info(obtainedScanList.toString());
    }

    @Test
    public void zeroAlliesForAllGetTheNearest(){
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
        Scan[] obtainedScanList = new AssistAllies().process(scanList);
        assertTrue(obtainedScanList.length == 3);
        assertEquals(c3, obtainedScanList[0].getCoordinates());
        LOGGER.info(obtainedScanList.toString());
    }

    @Test
    public void allAlliesGetTheNearest(){
        Coordinates c1 = new Coordinates(5,5);
        Coordinates c2 = new Coordinates(20,20);
        Coordinates c3 = new Coordinates(1,1);
        Enemies e1 = new Enemies(5,"soldier");
        Enemies e2 = new Enemies(20,"mech");
        Enemies e3 = new Enemies(1,"soldier");
        Scan scan1 = new Scan(c1,e1,1);
        Scan scan2 = new Scan(c2,e2,1);
        Scan scan3 = new Scan(c3,e3,1);
        Scan[] scanList = new Scan[]{scan1, scan3, scan2};
        Scan[] obtainedScanList = new AssistAllies().process(scanList);
        assertTrue(obtainedScanList.length == 3);
        assertEquals(c3, obtainedScanList[0].getCoordinates());
        LOGGER.info(obtainedScanList.toString());
    }

}
