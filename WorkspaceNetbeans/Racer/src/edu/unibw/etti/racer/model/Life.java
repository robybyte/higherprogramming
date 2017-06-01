package edu.unibw.etti.racer.model;

import edu.unibw.etti.racer.controller.Configuration;

public class Life extends Obstacle {
    public Life(double xPos, double yPos) {
        super(Configuration.LIFE_FILE, xPos, yPos, 0, 1, 0, 0);
    }
}
