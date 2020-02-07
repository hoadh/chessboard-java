package com.codegym.board.tree;

import java.util.ArrayList;
import java.util.List;

public class Node {
    List<Node> nextList = new ArrayList<>();
    Node back;
    int stepCount = 0;
    String coordinate = "";

    Node(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getCoordinate() {
        return coordinate;
    }

    void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    int getStepCount() {
        return this.stepCount;
    }

    void setBack(Node back) {
        this.back = back;
    }

    void add(Node next) {
        int incStep = this.stepCount + 1;
        next.setStepCount(incStep);
        next.setBack(this);
        nextList.add(next);
    }

    List<Node> getNextList() {
        return nextList;
    }
}
