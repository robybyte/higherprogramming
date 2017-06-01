package edu.unibw.etti.racer.model;

import edu.unibw.etti.racer.controller.Configuration;
import java.util.Observable;

public class Score extends Observable{ 

    private int lives = Configuration.INITIAL_LIVES;
    private int points = 0;
    private int level = 0;

    public synchronized void addPoints(int points) {
        if (points != 0) {
            this.points += points;
            level = this.points / Configuration.UPGRADE_LEVEL_AFTER_POINTS;
            
            //System.out.println("Level: " + this.level + " - Points: " + this.points
            //    + " - Lives: " + this.lives);
            setChanged();
            notifyObservers();
        }
    }

    public synchronized void addLives(int lives) {
        if (lives != 0) {
            this.lives += lives;

            //System.out.println("Level: " + this.level + " - Points: " + this.points
            //    + " - Lives: " + this.lives);
            setChanged();
            notifyObservers();
        }
    }

    public int getPoints() {
        return points;
    }

    public int getLives() {
        return lives;
    }

    public int getLevel() {
        return level;
    }

    public void reset() {
        points = 0;
        lives = Configuration.INITIAL_LIVES;
        level = 0;
        setChanged();
        notifyObservers();
    }

}
