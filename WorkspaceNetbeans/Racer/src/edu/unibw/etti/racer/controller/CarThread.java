package edu.unibw.etti.racer.controller;

public class CarThread extends Thread {
    private final GameControl gameControl;
    
    private int downUpIndicator = 0;
    private double shiftAmount = 0.0;

    public CarThread(GameControl gameControl) {
        super("CarThread");

        this.gameControl = gameControl;
    }

    public synchronized void setShiftAmount(double value) {
        shiftAmount = value;
    }

    public double getShiftAmount() {
        return shiftAmount;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                sleep(Configuration.STEP_SLEEP);
                if (gameControl.isPaused())
                    synchronized (gameControl) {
                        gameControl.wait();
                    }
            } catch (InterruptedException e) {
                return;
            }

            if (downUpIndicator < Configuration.CAR_AMPLITUDE_AMOUNT) {
                gameControl.car.shiftY(shiftAmount - Configuration.CAR_AMPLITUDE_STEP);
            } else if (downUpIndicator < 2 * Configuration.CAR_AMPLITUDE_AMOUNT) {
                gameControl.car.shiftY(shiftAmount + Configuration.CAR_AMPLITUDE_STEP);
            } 
            downUpIndicator++;
            if (downUpIndicator  >= 2 * Configuration.CAR_AMPLITUDE_AMOUNT)
                downUpIndicator = 0;
        }
    }
}
