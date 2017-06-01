package edu.unibw.etti.racer.model;

import edu.unibw.etti.racer.model.Figure;

public abstract class Obstacle extends Figure {

    public final int pointsOnHit;
    public final int livesOnHit;
    public final int pointsOnPass;
    public final int livesOnPass;

    public Obstacle(String file, double xPos, double yPos, 
        int pointsOnHit, int livesOnHit, int pointsOnPass, int livesOnPass) {
        super(file, xPos, yPos);
        this.pointsOnHit = pointsOnHit;
        this.livesOnHit = livesOnHit;
        this.pointsOnPass = pointsOnPass;
        this.livesOnPass = livesOnPass;
    }
}
