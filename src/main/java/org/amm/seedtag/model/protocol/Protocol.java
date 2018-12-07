package org.amm.seedtag.model.protocol;

import org.amm.seedtag.model.message.Coordinates;
import org.amm.seedtag.model.message.Scan;

import java.awt.geom.Point2D;
import java.util.List;

public interface Protocol {

    public List<Coordinates> execute (Scan[] scans);

    public static double getDistance (Coordinates coordinates) {
        final Point2D finalP = new Point2D.Double(0, 0);
        final Point2D point1 = new Point2D.Double(coordinates.getX(), coordinates.getY());
        double distance = point1.distance(finalP);
        return distance;
    }
}

