package com.codegym.board;

import com.codegym.common.Coordinate;
import com.codegym.common.Game;
import com.codegym.chess.Chessman;

public class Board {
    private final Cell[][] cells = new Cell[Game.BOARD_SIZE][Game.BOARD_SIZE];

    public Board() {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() throws Exception {
        for (int i = 0 ; i < Game.BOARD_SIZE; i++) {
            for (int j = 0 ; j < Game.BOARD_SIZE; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                this.cells[i][j] = new Cell(coordinate);
            }
        }
    }

    private void checkPosition(int col, int row) throws Exception {
        if (col > Game.LAST_INDEX || col < Game.FIRST_INDEX) throw new Exception("Invalid column");
        if (row > Game.LAST_INDEX || row < Game.FIRST_INDEX) throw new Exception("Invalid row");
    }

    public Cell getCellAt(Coordinate coordinate) throws Exception {
        checkPosition(coordinate.getX(), coordinate.getY());
        return cells[coordinate.getX()][coordinate.getY()];
    }

    public boolean canMoveChessman(Coordinate currentCoordinate, Coordinate nextCoordinate) throws Exception {
        Cell currentCell = getCellAt(currentCoordinate);

        if (currentCell.isNotEmpty()) {
            Chessman chessman = currentCell.getChessman();
            Cell nextCell = getCellAt(nextCoordinate);
            Iterable<Coordinate> coordinates = chessman.getNextSteps();

            for (Coordinate coordinate: coordinates) {
                if (hasChessmanAt(coordinate)) {
                    return false;
                }
            }
            return true;
        } else {
            throw new Exception("Không có quân cờ ở vị trí này");
        }
    }

    public void setChessmanAt(Chessman chessman, Coordinate coordinate) throws Exception {
        Cell cell = getCellAt(coordinate);
        cell.setChessman(chessman);
    }

    public boolean moveChessman(Cell currentCell, Coordinate nextCoordinate) throws Exception {
        if (currentCell.isNotEmpty()) {
            Chessman chessman = currentCell.getChessman();
            Cell nextCell = getCellAt(nextCoordinate);
            Iterable<Coordinate> coordinates = chessman.getPathTo(nextCoordinate);

            for (Coordinate coordinate: coordinates) {
                if (hasChessmanAt(coordinate)) {
                    return false;
                }
            }
            nextCell.setChessman(chessman);
            currentCell.setEmpty();
            return true;
        } else {
            throw new Exception("Không có quân cờ ở vị trí này");
        }
    }

    private boolean hasChessmanAt(Coordinate coordinate) {
        return (this.cells[coordinate.getX()][coordinate.getY()] == null);
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();

        text.append("   ");
        for (char i = 'a'; i <= 'h'; i++) {
            text.append(" ").append(i).append(" ");
        }
        text.append('\n');

        for (int i = 0; i < cells.length; i++) {
            text.append(" ").append(8 - i).append(" ");
            for (int j = 0; j < cells[i].length; j++) {
                text.append((cells[j][i].isEmpty()) ? " _ " : " " + cells[j][i].getChessman().getName() + " ");
            }
            text.append(" ").append(8 - i).append(" ");
            text.append('\n');
        }

        text.append("   ");
        for (char i = 'a'; i <= 'h'; i++) {
            text.append(" ").append(i).append(" ");
        }
        text.append('\n');

        return text.toString();
    }

    public static Coordinate convertStringToCoordinate(String posString) throws Exception {
        if (posString == null || posString.isEmpty() || posString.length() > 2) {
            throw new Exception("Sai vị trí");
        }

        char col = posString.charAt(0);
        char row = posString.charAt(1);

        int x, y;

        switch (col) {
            case 'a': x = 0; break;
            case 'b': x = 1; break;
            case 'c': x = 2; break;
            case 'd': x = 3; break;
            case 'e': x = 4; break;
            case 'f': x = 5; break;
            case 'g': x = 6; break;
            case 'h': x = 7; break;
            default: throw new Exception("Invalid column character");
        }

        switch (row) {
            case '1': y = 7; break;
            case '2': y = 6; break;
            case '3': y = 5; break;
            case '4': y = 4; break;
            case '5': y = 3; break;
            case '6': y = 2; break;
            case '7': y = 1; break;
            case '8': y = 0; break;
            default: throw new Exception("Invalid row character");
        }
        return new Coordinate(x, y);
    }
}
