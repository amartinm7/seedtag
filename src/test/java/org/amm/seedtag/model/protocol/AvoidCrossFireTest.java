package org.amm.seedtag.model.protocol;

import org.amm.seedtag.model.message.Coordinates;
import org.amm.seedtag.model.message.Enemies;
import org.amm.seedtag.model.message.Scan;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class AvoidCrossFireTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AvoidCrossFireTest.class);
    private String message = "Return only the enemies, filter the allies to skip them";

    @Before
    public void init(){
        LOGGER.info(message);
    }

    @Test
    public void oneAllieGetTheRest(){
        Coordinates c1 = new Coordinates(35,5);
        Coordinates c2 = new Coordinates(5,35);
        Enemies e1 = new Enemies(10,"soldier");
        Enemies e2 = new Enemies(20,"soldier");
        Scan scan1 = new Scan(c1,e1,3);
        Scan scan2 = new Scan(c2,e2,0);
        Scan[] scanList = new Scan[]{scan1, scan2};
        List<Coordinates> obtainedCoordinates = new AvoidCrossfire().execute(scanList);
        assertTrue(obtainedCoordinates.size() == 1);
        assertEquals(c2, obtainedCoordinates.get(0));
        LOGGER.info(scanList.toString());
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
        List<Coordinates> obtainedCoordinates = new AvoidCrossfire().execute(scanList);
        assertEquals(3, obtainedCoordinates.size());
        assertEquals(c3, obtainedCoordinates.get(0));
        LOGGER.info(scanList.toString());
    }

    @Test
    public void allAlliesReturnEmpty(){
        Coordinates c1 = new Coordinates(5,5);
        Coordinates c2 = new Coordinates(20,20);
        Coordinates c3 = new Coordinates(1,1);
        Enemies e1 = new Enemies(5,"soldier");
        Enemies e2 = new Enemies(20,"mech");
        Enemies e3 = new Enemies(1,"soldier");
        Scan scan1 = new Scan(c1,e1,1);
        Scan scan2 = new Scan(c2,e2,1);
        Scan scan3 = new Scan(c3,e3,1);
        Scan[] scanList = new Scan[]{scan1, scan2, scan3};
        List<Coordinates> obtainedCoordinates = new AvoidCrossfire().execute(scanList);
        assertEquals(0, obtainedCoordinates.size());
        LOGGER.info(scanList.toString());
    }
}
