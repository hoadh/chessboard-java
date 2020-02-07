package com.codegym.chess;

import com.codegym.common.Coordinate;

import java.util.*;

public class Bishop extends Chessman {
    private Map<Coordinate, List<Coordinate>> steps = new HashMap<>();

    private List<Coordinate> cloneList(List<Coordinate> list) {
        return new ArrayList<>(list);
    }

    @Override
    protected void updateNextSteps() throws Exception {
        int x = this.getCoordinate().getX();
        int y = this.getCoordinate().getY();

        int i = x, j = y;
        List<Coordinate> coordinates = new ArrayList<>();
        while (i < lastIndex && j > firstIndex) {
            i++;
            j--;

            Coordinate nextStep = new Coordinate(i, j);
            steps.put(nextStep, cloneList(coordinates));
            coordinates.add(nextStep);
        }

        i = x; j = y;
        coordinates.clear();
        while (i < lastIndex && j < lastIndex) {
            i++;
            j++;

            Coordinate nextStep = new Coordinate(i, j);
            steps.put(nextStep, cloneList(coordinates));
            coordinates.add(nextStep);
        }

        i = x; j = y;
        coordinates.clear();
        while (i > firstIndex && j > firstIndex) {
            i--;
            j--;

            Coordinate nextStep = new Coordinate(i, j);
            steps.put(nextStep, cloneList(coordinates));
            coordinates.add(nextStep);
        }

        i = x; j = y;
        coordinates.clear();
        while (i > firstIndex && j < lastIndex) {
            i--;
            j++;

            Coordinate nextStep = new Coordinate(i, j);
            steps.put(nextStep, cloneList(coordinates));
            coordinates.add(nextStep);
        }
    }

    @Override
    public Iterable<Coordinate> getNextSteps() {
        return this.steps.keySet();
    }

    @Override
    public Iterable<Coordinate> getPathTo(Coordinate destination) {
        return this.steps.get(destination);
    }

    @Override
    public char getName() {
        return 'B';
    }

    @Override
    public boolean canMove(Coordinate destination, Iterable<Coordinate> coordinatesContainChessman) {
        if (this.isValidNextCoordinate(destination)) {
            Iterable<Coordinate> path = this.getPathTo(destination);

            for (Coordinate coordinate: path) {
                for (Coordinate coordinateHasChessman: coordinatesContainChessman) {
                    if (coordinate.equals(coordinateHasChessman)) {
                        return false;
                    }
                }
            }

            return true;
        }
        return false;
    }
}
