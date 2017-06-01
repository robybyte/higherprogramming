package edu.unibw.etti.racer.controller;

import edu.unibw.etti.racer.model.Obstacle;

public class ObstaclesThread extends Thread {

    private final GameControl gameControl;

    public ObstaclesThread(GameControl gameControl) {
        super("ObstaclesThread");

        this.gameControl = gameControl;
    }

    @Override
    public void run() {
        int count = 0;
        while (!isInterrupted()) {
            try {
                sleep(Configuration.STEP_SLEEP);
                if (gameControl.isPaused()) {
                    synchronized (gameControl) {
                        gameControl.wait();
                    }
                }
            } catch (InterruptedException e) {
                return;
            }

            if (++count > Configuration.OBSTACLE_CREATION_INTERVALL / (Configuration.STEP_SIZE + gameControl.score.getLevel())) {
                double random = Math.random();
                int lane = ((int) (Math.random() * Configuration.NUMBER_OF_LANES))
                        * (Configuration.LANE_WIDTH + Configuration.MARKER_WIDTH)
                        + Configuration.MARKER_WIDTH + Configuration.LANE_WIDTH / 2;

                if (random < Configuration.VEHICLE_CREATION) {
                    gameControl.obstacles.createVehicle(Configuration.STREET_LENGTH, lane);
                } else if (random < Configuration.POINT_CREATION) {
                    gameControl.obstacles.createPoint(Configuration.STREET_LENGTH, lane);
                } else if (random < Configuration.LIFE_CREATION) {
                    gameControl.obstacles.createLife(Configuration.STREET_LENGTH, lane);
                }

                count = 0;
            }

            // Hindenisse bewegen und Kollision ueberpruefen
            int i = 0;
            Obstacle obstacle = gameControl.obstacles.get(i++);
            while (obstacle != null) {
                obstacle.shiftX(- (Configuration.STEP_SIZE + gameControl.score.getLevel()));

                if (gameControl.car.collides(obstacle)) {
                    gameControl.obstacles.delete(obstacle);
                    if (obstacle.livesOnHit < 0) {
                        gameControl.obstacles.createBang(
                                (gameControl.car.getX() + obstacle.getX()) / 2.0,
                                (gameControl.car.getY() + obstacle.getY()) / 2.0);
                        gameControl.obstacles.notifyObservers();
                        try {
                            sleep(Configuration.COLLISION_SLEEP);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    gameControl.addLives(obstacle.livesOnHit);
                    gameControl.addPoints(obstacle.pointsOnHit);
                } else if (obstacle.getX() + obstacle.width < 0) {
                    gameControl.obstacles.delete(obstacle);
                    gameControl.addLives(obstacle.livesOnPass);
                    gameControl.addPoints(obstacle.pointsOnPass);
                }
                
                obstacle = gameControl.obstacles.get(i++);
            }
            gameControl.obstacles.notifyObservers();
        }
    }
}
