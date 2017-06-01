package edu.unibw.etti.racer.model;

import edu.unibw.etti.racer.controller.Configuration;
import java.util.Observable;

import javax.imageio.ImageIO;

public class Figure extends Observable {

    public final String file;
    public final int width;
    public final int height;
    public final int height_half;

    private double x = 0;
    private double y = 0;

    public Figure(String file, double xPos, double yPos) {
        this.file = file;
        x = xPos;
        y = yPos;

        int w = 1;
        int h = 1;
        try {
            w = ImageIO.read(getClass().getResource(file)).getWidth();
            h = ImageIO.read(getClass().getResource(file)).getHeight();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        width = w;
        height = h;
        height_half = height / 2;
    }

    public void set(double xPos, double yPos) {
        if (xPos != this.x || yPos != this.y) {
            this.x = xPos;
            this.y = yPos;
            setChanged();
            notifyObservers();
        }
    }

    public void shiftX(double xShift) {
        if (xShift != 0.0) {
            this.x += xShift;
            setChanged();
            notifyObservers();
        }
    }

    public void shiftY(double yShift) {
        if (yShift != 0.0) {
            y += yShift;
            if (y < height_half) {
                y = height_half;
            }
            if (y > Configuration.STREET_WIDTH - height_half) {
                y = Configuration.STREET_WIDTH - height_half;
            }
            setChanged();
            notifyObservers();
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean collides(Figure figure) {
        if (x + width + Configuration.SAFETY_DISTANCE < figure.x) {
            return false;
        } else if (figure.x + figure.width + Configuration.SAFETY_DISTANCE < x) {
            return false;
        } else if (y + height_half + Configuration.SAFETY_DISTANCE < figure.y - figure.height_half) {
            return false;
        } else {
            return figure.y + figure.height_half + Configuration.SAFETY_DISTANCE >= y - height_half;
        }
    }

}
