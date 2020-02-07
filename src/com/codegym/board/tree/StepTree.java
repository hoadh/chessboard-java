package com.codegym.board.tree;

import java.util.ArrayList;
import java.util.List;

public class StepTree {
    Node first;
    StepTree(String coordinate) {
        first = new Node(coordinate);
    }

    List<Node> destinations = new ArrayList<>();

    public Node getFirst() {
        return first;
    }

    public Node getMinStep() {
        Node minStep = destinations.get(0);
        for (Node node : destinations) {
            if (node.getStepCount() < minStep.getStepCount()) {
                minStep = node;
            }
        }

        return minStep;
    }
}
