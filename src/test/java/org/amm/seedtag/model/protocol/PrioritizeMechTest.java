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

// assist-allies : Deberan de priorizarse los puntos en los que exista algún aliado.
public class PrioritizeMechTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrioritizeMechTest.class);
    private String message = "prioritize the mech over the others";

    @Before
    public void init(){
        LOGGER.info(message);
    }

    @Test
    public void oneMechGetTheNearest(){
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
        Scan[] obtainedCoordinates = new PrioritizeMech().process(scanList);
        assertTrue(obtainedCoordinates.length == 3);
        assertEquals(c2, obtainedCoordinates[0].getCoordinates());
        assertEquals(c3, obtainedCoordinates[1].getCoordinates());
        assertEquals(c1, obtainedCoordinates[2].getCoordinates());
        LOGGER.info(obtainedCoordinates.toString());
    }

    @Test
    public void twoMechGetTheNearest(){
        Coordinates c1 = new Coordinates(5,5);
        Coordinates c2 = new Coordinates(20,20);
        Coordinates c3 = new Coordinates(1,1);
        Enemies e1 = new Enemies(5,"mech");
        Enemies e2 = new Enemies(20,"mech");
        Enemies e3 = new Enemies(1,"soldier");
        Scan scan1 = new Scan(c1,e1,1);
        Scan scan2 = new Scan(c2,e2,1);
        Scan scan3 = new Scan(c3,e3,1);
        Scan[] scanList = new Scan[]{scan1, scan2, scan3};
        Scan[] obtainedCoordinates = new PrioritizeMech().process(scanList);
        assertTrue(obtainedCoordinates.length == 3);
        assertEquals(c1, obtainedCoordinates[0].getCoordinates());
        assertEquals(c2, obtainedCoordinates[1].getCoordinates());
        assertEquals(c3, obtainedCoordinates[2].getCoordinates());
        LOGGER.info(obtainedCoordinates.toString());
    }

    @Test
    public void zeroMechGetTheNearest(){
        Coordinates c1 = new Coordinates(5,5);
        Coordinates c2 = new Coordinates(20,20);
        Coordinates c3 = new Coordinates(1,1);
        Enemies e1 = new Enemies(5,"soldier");
        Enemies e2 = new Enemies(20,"soldier");
        Enemies e3 = new Enemies(1,"soldier");
        Scan scan1 = new Scan(c1,e1,1);
        Scan scan2 = new Scan(c2,e2,1);
        Scan scan3 = new Scan(c3,e3,1);
        Scan[] scanList = new Scan[]{scan1, scan2, scan3};
        Scan[] obtainedCoordinates = new PrioritizeMech().process(scanList);
        assertTrue(obtainedCoordinates.length == 3);
        assertEquals(c3, obtainedCoordinates[0].getCoordinates());
        assertEquals(c1, obtainedCoordinates[1].getCoordinates());
        assertEquals(c2, obtainedCoordinates[2].getCoordinates());
        LOGGER.info(obtainedCoordinates.toString());
    }
    @Test
    public void allMechGetTheNearest(){
        Coordinates c1 = new Coordinates(5,5);
        Coordinates c2 = new Coordinates(20,20);
        Coordinates c3 = new Coordinates(1,1);
        Enemies e1 = new Enemies(5,"mech");
        Enemies e2 = new Enemies(20,"mech");
        Enemies e3 = new Enemies(1,"mech");
        Scan scan1 = new Scan(c1,e1,0);
        Scan scan2 = new Scan(c2,e2,0);
        Scan scan3 = new Scan(c3,e3,0);
        Scan[] scanList = new Scan[]{scan1, scan2, scan3};
        Scan[] obtainedCoordinates = new PrioritizeMech().process(scanList);
        assertTrue(obtainedCoordinates.length == 3);
        assertEquals(c3, obtainedCoordinates[0].getCoordinates());
        assertEquals(c1, obtainedCoordinates[1].getCoordinates());
        assertEquals(c2, obtainedCoordinates[2].getCoordinates());
        LOGGER.info(obtainedCoordinates.toString());
    }
}
