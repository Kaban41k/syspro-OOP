package ru.nsu.anishchenko;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;


import org.junit.jupiter.api.Test;


class AdjacencyMatrixGraphTest extends GraphTest {
    @Override
    protected Graph createGraph() {
        return new AdjacencyMatrixGraph();
    }

    @Test
    void matrixSizeTest() {
        int n = 4;
        AdjacencyMatrixGraph graph = (AdjacencyMatrixGraph) createGraph();

        for (int i = 0; i < n; i++) {
            graph.addNode(new Graph.Node());
        }

        ArrayList<ArrayList<Boolean>> matrix = graph.getMatrix();

        assertEquals(n, matrix.size());

        for (int i = 0; i < n; i++) {
            assertEquals(n, matrix.get(i).size());
        }
    }
}
