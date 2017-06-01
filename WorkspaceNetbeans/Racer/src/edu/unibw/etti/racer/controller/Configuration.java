package edu.unibw.etti.racer.controller;

public class Configuration {

    public static final int STREET_LENGTH = 800;

    public static final int LANE_WIDTH = 100;
    public static final int MARKER_WIDTH = 20;

    public static final int NUMBER_OF_LANES = 4;

    public static final int STREET_WIDTH = LANE_WIDTH * NUMBER_OF_LANES
            + MARKER_WIDTH * (NUMBER_OF_LANES + 1);
    public static final int STREET_WIDTH_HALF = STREET_WIDTH / 2;

    public static final int UPGRADE_LEVEL_AFTER_POINTS = 20;
    public static final int INITIAL_LIVES = 3;

    public static final double VEHICLE_CREATION = 0.6;
    public static final double POINT_CREATION = 0.95;
    public static final double LIFE_CREATION = 1.0;
    public static final int STEP_SLEEP = 5;

    public static final int COLLISION_SLEEP = 100;
    public static final double SAFETY_DISTANCE = -25;

    public static final int STEP_SIZE = 1;
    public static final int OBSTACLE_CREATION_INTERVALL = 180 * STEP_SIZE;

    public static final double CAR_AMPLITUDE_STEP = 0.333;
    public static final int CAR_AMPLITUDE_AMOUNT = 10;
    public static final double CAR_XPOS = 5.0;

    public static final String PATH = "/edu/unibw/etti/racer/image/";
    public static final String CAR_FILE = PATH + "car.png";
    public static final String LIFE_FILE = PATH + "present.jpg";
    public static final String POINT_FILE = PATH + "coin.jpg";
    public static final String BANG_FILE = PATH + "bang.jpg";

    public static final String[] VEHICLE_FILES = {
        PATH + "bike.jpg", PATH + "bus.jpg", PATH + "cab.jpg",
        PATH + "cabriolet.jpg", PATH + "ferry.jpg", PATH + "gondola.jpg",
        PATH + "helicopter.jpg", PATH + "motorbike.jpg", PATH + "plane.jpg",
        PATH + "train.jpg", PATH + "van.jpg", PATH + "vespa.jpg",
        PATH + "zeppelin.jpg"};
    public static final int VEHICLE_FILES_LENGTH = VEHICLE_FILES.length;

    public static final String MARKER_FILE = PATH + "line.jpg";
    public static final double NEXT_MARKER_FACTOR = 2.0;
}
