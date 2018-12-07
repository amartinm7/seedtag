package org.amm.seedtag.model.message;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CoordinatesTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoordinatesTest.class);

    @Test
    public void test(){
        Coordinates c1 = new Coordinates(5,5);
        Coordinates c2 = new Coordinates(20,20);
        Coordinates c3 = new Coordinates(1,1);
        List<Coordinates> points = new ArrayList<>();
        points.add(c1);
        points.add(c2);
        points.add(c3);
        Collections.sort(points, new Coordinates.CoordinatesComparator());
        LOGGER.info(points.toString());
    }
}
