package com.codegym.board;

import com.codegym.common.Coordinate;
import com.codegym.chess.Chessman;

public class Cell {
    private Coordinate coordinate;
    private Chessman chessman;

    public Cell(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setChessman(Chessman chessman) throws Exception {
        if (chessman != null) {
            this.chessman = chessman;
            this.chessman.setCoordinate(this.coordinate);
        }
    }

    public Chessman getChessman() {
        return this.chessman;
    }

    public void setEmpty() {
        this.chessman = null;
    }

    public boolean isEmpty() {
        return (this.chessman == null);
    }

    public boolean isNotEmpty() {
        return (!isEmpty());
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
