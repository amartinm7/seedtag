package org.amm.seedtag.model.protocol;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.amm.seedtag.model.message.Coordinates;
import org.amm.seedtag.model.message.Enemies;
import org.amm.seedtag.model.message.RequestMessage;
import org.amm.seedtag.model.message.Scan;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// furthest-enemies : Se deberá priorizar el punto más lejano en el que haya enemigos.
public class FurthestEnemiesTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FurthestEnemiesTest.class);

    @Test
    public void realTest4() throws Exception{
        final String provided = "{\"protocols\":[\"furthest-enemies\"],\"scans\":[{\"enemies\":{\"number\":10,\"type\":\"soldier\"},\"coordinates\":{\"y\":35,\"x\":5}},{\"enemies\":{\"number\":20,\"type\":\"soldier\"},\"coordinates\":{\"y\":30,\"x\":10}}]}";
        final Coordinates expected = new Coordinates(5,35);
        final ObjectMapper objectMapper = new ObjectMapper();
        final RequestMessage request =  objectMapper.readValue(provided, RequestMessage.class);
        List<Coordinates> obtainedCoordinates = new FurthestEnemies().execute(request.getScans());
        assertTrue("no returned coordinates", obtainedCoordinates.size() > 0 );
        assertEquals(expected, obtainedCoordinates.get(0));
        LOGGER.info(obtainedCoordinates.toString());
    }

    @Test
    public void test7(){
        final String provided = "{\"protocols\":[\"furthest-enemies\"]," +
                "\"scans\":[{\"enemies\":{\"number\":10,\"type\":\"soldier\"},\"coordinates\":{\"y\":35,\"x\":5}}," +
                "{\"enemies\":{\"number\":20,\"type\":\"soldier\"},\"coordinates\":{\"y\":30,\"x\":10}}]}";
        final String expected = "{\"x\":5,\"y\":35}";
        Coordinates c1 = new Coordinates(5,35);
        Coordinates c2 = new Coordinates(10,30);
        Enemies e1 = new Enemies(10,"soldier");
        Enemies e2 = new Enemies(20,"soldier");
        Scan scan1 = new Scan(c1,e1,0);
        Scan scan2 = new Scan(c2,e2,0);
        Scan[] scanList = new Scan[]{scan1, scan2};
        List<Coordinates> obtainedCoordinates = new FurthestEnemies().execute(scanList);
        assertTrue("no returned coordinates", obtainedCoordinates.size() > 0 );
        assertEquals(c2, obtainedCoordinates.get(0));
        LOGGER.info(scanList.toString());
    }



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
