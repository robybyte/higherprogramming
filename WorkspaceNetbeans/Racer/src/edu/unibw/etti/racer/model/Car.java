package edu.unibw.etti.racer.model;

import edu.unibw.etti.racer.controller.Configuration;

public class Car extends Figure {

    public Car() {
        super(Configuration.CAR_FILE, Configuration.CAR_XPOS, Configuration.STREET_WIDTH_HALF);
    }

    public void reset() {
        set(Configuration.CAR_XPOS, Configuration.STREET_WIDTH_HALF);
    }
}
