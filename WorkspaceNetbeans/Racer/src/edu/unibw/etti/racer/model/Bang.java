package edu.unibw.etti.racer.model;

import edu.unibw.etti.racer.controller.Configuration;

public class Bang extends Obstacle {

    public Bang(double xPos, double yPos) {
        super(Configuration.BANG_FILE, xPos, yPos, 0, 0, 0, 0);
    }

}