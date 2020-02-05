package com.codegym;

import com.codegym.chess.Bishop;
import com.codegym.common.Coordinate;

public class BishopTest {
    public static void main(String[] args) throws Exception {
        Bishop bishop = new Bishop();
        Coordinate coordinate = new Coordinate(4, 3);
        bishop.setCoordinate(coordinate);
        bishop.printAllSteps();
    }
}
