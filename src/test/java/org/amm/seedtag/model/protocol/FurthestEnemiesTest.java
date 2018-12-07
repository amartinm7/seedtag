package org.amm.seedtag.model.protocol;

import org.amm.seedtag.model.message.Coordinates;
import org.amm.seedtag.model.message.Enemies;
import org.amm.seedtag.model.message.Scan;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;

// furthest-enemies : Se deberá priorizar el punto más lejano en el que haya enemigos.
public class FurthestEnemiesTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FurthestEnemiesTest.class);

    @Test
    public void zeroEnemiesForAllGetTheFurthest(){
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
        List<Coordinates> obtainedCoordinates = new FurthestEnemies().execute(scanList);
        assertEquals(c2, obtainedCoordinates);
        LOGGER.info(scanList.toString());
    }

    @Test
    public void moreEnemiesForTheFarestGetTheFarest(){
        Coordinates c1 = new Coordinates(5,5);
        Coordinates c2 = new Coordinates(20,20);
        Coordinates c3 = new Coordinates(1,1);
        Enemies e1 = new Enemies(5,"soldier");
        Enemies e2 = new Enemies(20,"mech");
        Enemies e3 = new Enemies(1,"soldier");
        Scan scan1 = new Scan(c1,e1,0);
        Scan scan2 = new Scan(c2,e2,0);
        Scan scan3 = new Scan(c3,e3,0);
        Scan[] scanList = new Scan[]{scan1, scan2, scan3};
        List<Coordinates> obtainedCoordinates = new FurthestEnemies().execute(scanList);
        assertEquals(c2, obtainedCoordinates);
        LOGGER.info(scanList.toString());
    }

    @Test
    public void zeroEnemiesForTheFarestGetTheSecondFarest(){
        Coordinates c1 = new Coordinates(5,5);
        Coordinates c2 = new Coordinates(20,20);
        Coordinates c3 = new Coordinates(1,1);
        Enemies e1 = new Enemies(5,"soldier");
        Enemies e2 = new Enemies(0,"mech");
        Enemies e3 = new Enemies(1,"soldier");
        Scan scan1 = new Scan(c1,e1,0);
        Scan scan2 = new Scan(c2,e2,0);
        Scan scan3 = new Scan(c3,e3,0);
        Scan[] scanList = new Scan[]{scan1, scan2, scan3};
        List<Coordinates> obtainedCoordinates = new FurthestEnemies().execute(scanList);
        assertEquals(c1, obtainedCoordinates);
        LOGGER.info(scanList.toString());
    }
    @Test
    public void zeroEnemiesForTheNearest(){
        Coordinates c1 = new Coordinates(5,5);
        Coordinates c2 = new Coordinates(20,20);
        Coordinates c3 = new Coordinates(1,1);
        Enemies e1 = new Enemies(5,"soldier");
        Enemies e2 = new Enemies(20,"mech");
        Enemies e3 = new Enemies(0,"soldier");
        Scan scan1 = new Scan(c1,e1,0);
        Scan scan2 = new Scan(c2,e2,0);
        Scan scan3 = new Scan(c3,e3,0);
        Scan[] scanList = new Scan[]{scan1, scan2, scan3};
        List<Coordinates> obtainedCoordinates = new FurthestEnemies().execute(scanList);
        assertEquals(c2, obtainedCoordinates);
        LOGGER.info(scanList.toString());
    }
}
