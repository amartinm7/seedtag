package org.amm.seedtag.model.protocol;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.amm.seedtag.model.message.Coordinates;
import org.amm.seedtag.model.message.Enemies;
import org.amm.seedtag.model.message.RequestMessage;
import org.amm.seedtag.model.message.Scan;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// closest-enemies : Se deberá priorizar el punto más cercano en el que haya enemigos.
public class ClosestEnemiesTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClosestEnemiesTest.class);

    @Test
    public void realTest1() throws Exception {
        final String provided = "{\"protocols\":[\"closest-enemies\"],\"scans\":[{\"coordinates\":{\"x\":89,\"y\":13},\"enemies\":{\"type\":\"mech\",\"number\":1}},{\"coordinates\":{\"x\":11,\"y\":35},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":19,\"y\":49},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":38,\"y\":21},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":10,\"y\":39},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":13,\"y\":38},\"enemies\":{\"type\":\"soldier\",\"number\":15}},{\"coordinates\":{\"x\":13,\"y\":15},\"enemies\":{\"type\":\"soldier\",\"number\":60}},{\"coordinates\":{\"x\":30,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":40}},{\"coordinates\":{\"x\":30,\"y\":11},\"enemies\":{\"type\":\"soldier\",\"number\":20}},{\"coordinates\":{\"x\":15,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":80}},{\"coordinates\":{\"x\":22,\"y\":15},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":10,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":94,\"y\":11},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":10,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":90,\"y\":18},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":80,\"y\":51},\"enemies\":{\"type\":\"soldier\",\"number\":15}},{\"coordinates\":{\"x\":70,\"y\":91},\"enemies\":{\"type\":\"soldier\",\"number\":60}},{\"coordinates\":{\"x\":30,\"y\":11},\"enemies\":{\"type\":\"soldier\",\"number\":40}},{\"coordinates\":{\"x\":30,\"y\":95},\"enemies\":{\"type\":\"mech\",\"number\":20}},{\"coordinates\":{\"x\":1,\"y\":89},\"enemies\":{\"type\":\"soldier\",\"number\":80}},{\"coordinates\":{\"x\":3,\"y\":11},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":54,\"y\":19},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":22,\"y\":38},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":3,\"y\":10},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":43,\"y\":13},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":51,\"y\":13},\"enemies\":{\"type\":\"soldier\",\"number\":15}},{\"coordinates\":{\"x\":91,\"y\":30},\"enemies\":{\"type\":\"soldier\",\"number\":60}},{\"coordinates\":{\"x\":11,\"y\":30},\"enemies\":{\"type\":\"soldier\",\"number\":40}},{\"coordinates\":{\"x\":91,\"y\":15},\"enemies\":{\"type\":\"soldier\",\"number\":20}},{\"coordinates\":{\"x\":51,\"y\":22},\"enemies\":{\"type\":\"soldier\",\"number\":80}},{\"coordinates\":{\"x\":91,\"y\":10},\"enemies\":{\"type\":\"mech\",\"number\":10}},{\"coordinates\":{\"x\":11,\"y\":84},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":91,\"y\":65},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":81,\"y\":53},\"enemies\":{\"type\":\"mech\",\"number\":30}},{\"coordinates\":{\"x\":15,\"y\":70},\"enemies\":{\"type\":\"soldier\",\"number\":30}},{\"coordinates\":{\"x\":19,\"y\":83},\"enemies\":{\"type\":\"soldier\",\"number\":15}},{\"coordinates\":{\"x\":11,\"y\":46},\"enemies\":{\"type\":\"soldier\",\"number\":60}},{\"coordinates\":{\"x\":59,\"y\":26},\"enemies\":{\"type\":\"soldier\",\"number\":40}},{\"coordinates\":{\"x\":98,\"y\":57},\"enemies\":{\"type\":\"soldier\",\"number\":20}},{\"coordinates\":{\"x\":11,\"y\":58},\"enemies\":{\"type\":\"mech\",\"number\":80}},{\"coordinates\":{\"x\":91,\"y\":39},\"enemies\":{\"type\":\"mech\",\"number\":10}},{\"coordinates\":{\"x\":83,\"y\":37},\"enemies\":{\"type\":\"soldier\",\"number\":10}},{\"coordinates\":{\"x\":0,\"y\":11},\"enemies\":{\"type\":\"mech\",\"number\":1}}]}";
        final Coordinates expected = new Coordinates(3,10);
        final ObjectMapper objectMapper = new ObjectMapper();
        final RequestMessage request =  objectMapper.readValue(provided, RequestMessage.class);
        Scan[] obtainedScanList = new ClosestEnemies().process(request.getScans());
        assertTrue(obtainedScanList.length > 0);
        assertEquals(expected, obtainedScanList[0].getCoordinates());
        LOGGER.info(obtainedScanList.toString());
    }

    @Test
    public void getTheNearest(){
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
        Scan[] obtainedScanList = new ClosestEnemies().process(scanList);
        assertTrue(obtainedScanList.length > 0);
        assertEquals(c3, obtainedScanList[0].getCoordinates());
        LOGGER.info(obtainedScanList.toString());
    }

    @Test
    public void sameDistanceGetMoreEnemies(){
        Coordinates c1 = new Coordinates(5,5);
        Coordinates c2 = new Coordinates(1,1);
        Coordinates c3 = new Coordinates(1,1);
        Enemies e1 = new Enemies(5,"soldier");
        Enemies e2 = new Enemies(20,"mech");
        Enemies e3 = new Enemies(1,"soldier");
        Scan scan1 = new Scan(c1,e1,0);
        Scan scan2 = new Scan(c2,e2,0);
        Scan scan3 = new Scan(c3,e3,0);
        Scan[] scanList = new Scan[]{scan1, scan2, scan3};
        Scan[] obtainedScanList = new ClosestEnemies().process(scanList);
        assertTrue(obtainedScanList.length > 0);
        assertEquals(c2, obtainedScanList[0].getCoordinates());
        LOGGER.info(obtainedScanList.toString());
    }

    @Test
    public void sameDistanceSameEnemies(){
        Coordinates c1 = new Coordinates(5,5);
        Coordinates c2 = new Coordinates(1,1);
        Coordinates c3 = new Coordinates(1,1);
        Enemies e1 = new Enemies(5,"soldier");
        Enemies e2 = new Enemies(1,"mech");
        Enemies e3 = new Enemies(1,"soldier");
        Scan scan1 = new Scan(c1,e1,0);
        Scan scan2 = new Scan(c2,e2,0);
        Scan scan3 = new Scan(c3,e3,0);
        Scan[] scanList = new Scan[]{scan1, scan2, scan3};
        Scan[] obtainedScanList = new ClosestEnemies().process(scanList);
        assertTrue(obtainedScanList.length > 0);
        assertEquals(c2, obtainedScanList[0].getCoordinates());
        LOGGER.info(obtainedScanList.toString());
    }

}
