package edu.unibw.etti.racer.model;

import edu.unibw.etti.racer.controller.Configuration;

public class Vehicle extends Obstacle {

    private static int next = (int)(Math.random()*Configuration.VEHICLE_FILES_LENGTH);
    
    public Vehicle(double xPos, double yPos) {
        super(Configuration.VEHICLE_FILES[next++ % Configuration.VEHICLE_FILES_LENGTH], xPos, yPos, 0, -1, 1, 0);
    }

}
