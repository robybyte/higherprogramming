package edu.unibw.etti.racer.controller;

import edu.unibw.etti.racer.model.Score;
import edu.unibw.etti.racer.model.Car;
import java.util.Observable;

public class GameControl extends Observable {

    public final Score score;
    public final Car car;
    public final ObstacleArrayList obstacles;

    private final CarThread carThread;
    private final ObstaclesThread obstaclesThread;

    private boolean isPaused = true;

    public GameControl(Score score, Car car, ObstacleArrayList obstacles) {
        this.score = score;
        this.car = car;
        this.obstacles = obstacles;

        carThread = new CarThread(this);
        carThread.setDaemon(true);
        obstaclesThread = new ObstaclesThread(this);
        obstaclesThread.setDaemon(true);
    }

    public void startThreads() {
        carThread.start();
        obstaclesThread.start();
    }

    public synchronized void setPaused(boolean isPaused) {
        if (this.isPaused == isPaused) {
            return;
        }

        if (!isPaused && score.getLives() == 0) {
            restart();
        }

        if (!isPaused) {
            notifyAll();
        }

        this.isPaused = isPaused;
        setChanged();
        notifyObservers();
    }

    public boolean isPaused() {
        return isPaused;
    }

    public synchronized void addPoints(int points) {
        score.addPoints(points);
    }

    public synchronized void addLives(int lives) {
        score.addLives(lives);

        if (score.getLives() <= 0) {
            setPaused(true);
        }
    }

    public synchronized void setShiftAmount(double x) {
        if (carThread != null) {
            carThread.setShiftAmount(x);
        }
    }

    public double getShiftAmount() {
        if (carThread != null) {
            return carThread.getShiftAmount();
        } else {
            return 0.0;
        }
    }

    private void restart() {
        car.reset();
        score.reset();
        obstacles.reset();

        carThread.setShiftAmount(0.0);
    }
}
