package edu.unibw.etti.racer.controller;

import edu.unibw.etti.racer.model.Obstacle;
import edu.unibw.etti.racer.model.Vehicle;
import edu.unibw.etti.racer.model.Bang;
import edu.unibw.etti.racer.model.Bang;
import edu.unibw.etti.racer.model.Point;
import edu.unibw.etti.racer.model.Life;
import edu.unibw.etti.racer.model.Life;
import edu.unibw.etti.racer.model.Obstacle;
import edu.unibw.etti.racer.model.Point;
import edu.unibw.etti.racer.model.Vehicle;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ObstacleArrayList extends Observable implements Observer {

    private final ArrayList<Obstacle> obstacleList = new ArrayList<>();
    private final ArrayList<Vehicle> vehicleCache = new ArrayList<>();
    private final ArrayList<Point> pointCache = new ArrayList<>();
    private final ArrayList<Life> lifeCache = new ArrayList<>();
    private final ArrayList<Bang> bangCache = new ArrayList<>();

    public synchronized void createVehicle(double xPos, double yPos) {
        Obstacle figure = (vehicleCache.isEmpty())
                ? new Vehicle(xPos, yPos)
                : vehicleCache.remove(0);
        figure.set(xPos, yPos);
        obstacleList.add(figure);
        figure.addObserver(this);

        setChanged();
    }

    public synchronized void createPoint(double xPos, double yPos) {
        Obstacle figure = (pointCache.isEmpty())
                ? new Point(xPos, yPos)
                : pointCache.remove(0);
        figure.set(xPos, yPos);
        obstacleList.add(figure);
        figure.addObserver(this);

        setChanged();
    }

    public synchronized void createLife(double xPos, double yPos) {
        Obstacle figure = (lifeCache.isEmpty())
                ? new Life(xPos, yPos)
                : lifeCache.remove(0);
        figure.set(xPos, yPos);
        obstacleList.add(figure);
        figure.addObserver(this);

        setChanged();
    }

    public synchronized void createBang(double xPos, double yPos) {
        Obstacle figure = (bangCache.isEmpty())
                ? new Bang(xPos, yPos)
                : bangCache.remove(0);
        figure.set(xPos, yPos);
        obstacleList.add(figure);
        figure.addObserver(this);

        setChanged();
    }

    public synchronized void delete(Obstacle o) {
        if (obstacleList.remove(o)) {
            o.deleteObservers();
            if (o instanceof Vehicle) {
                vehicleCache.add((Vehicle) o);
            } else if (o instanceof Point) {
                pointCache.add((Point) o);
            } else if (o instanceof Bang) {
                bangCache.add((Bang) o);
            } else if (o instanceof Point) {
                pointCache.add((Point) o);
            } else if (o instanceof Life) {
                lifeCache.add((Life) o);
            }

            setChanged();
        }
    }

    public synchronized boolean contains(Obstacle obstacle) {
        return obstacleList.contains(obstacle);
    }

    public synchronized void reset() {
        while (!obstacleList.isEmpty()) {
            delete(obstacleList.get(0));
        }
    }

    public synchronized Obstacle get(int i) {
        if (i >= 0 && i < obstacleList.size())
            return obstacleList.get(i);
        else
            return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
    }
    
}
