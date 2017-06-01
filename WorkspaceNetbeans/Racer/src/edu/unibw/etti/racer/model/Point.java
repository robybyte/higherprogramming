package edu.unibw.etti.racer.model;

import edu.unibw.etti.racer.controller.Configuration;

public class Point extends Obstacle {

    public Point(double xPos, double yPos) {
        super(Configuration.POINT_FILE, xPos, yPos, 5, 0, 0, 0);
    }
}
