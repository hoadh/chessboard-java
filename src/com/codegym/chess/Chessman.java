package com.codegym.chess;

import com.codegym.common.Game;
import com.codegym.common.Coordinate;

public abstract class Chessman {
    private Coordinate coordinate;
    protected final int firstIndex = Game.FIRST_INDEX;
    protected final int lastIndex = Game.LAST_INDEX;

    private boolean _coordinateIsNotEqualWith(Coordinate coordinate) {

        return this.coordinate == null || !this.coordinate.equals(coordinate);
    }

    protected abstract void updateNextSteps() throws Exception;

    public void printAllSteps() {
        Iterable<Coordinate> steps = getNextSteps();
        int i = 1;
        for (Coordinate step: steps) {
            System.out.printf("%3d" + ". [" + step + "]\t", i++);
            Iterable<Coordinate> path = getPathTo(step);

            for (Coordinate square : path) {
                System.out.print(square + "\t");
            }
            System.out.println("");
        }
    }

    public final void setCoordinate(Coordinate coordinate) throws Exception {
        if (_coordinateIsNotEqualWith(coordinate)) {
            this.coordinate = coordinate;
            this.updateNextSteps();
        }
    }

    public final Coordinate getCoordinate() {
        return coordinate;
    }

    public final boolean isValidNextCoordinate(Coordinate nextCoordinate) {
        Iterable<Coordinate> coordinates = this.getNextSteps();
        for (Coordinate coordinate: coordinates) {
            if (coordinate.equals(nextCoordinate)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get all next available steps (coordinates)
     * @return Iterable<Coordinate>
     */
    public abstract Iterable<Coordinate> getNextSteps();

    /**
     * Get all coordinates of the path to destination.
     * @param destination Coordinate
     * @return Iterable<Coordinate>
     */
    public abstract Iterable<Coordinate> getPathTo(Coordinate destination);

    public abstract char getName();
}
