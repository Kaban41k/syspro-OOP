package ru.nsu.anishchenko;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdjacencyMatrixGraphTest extends GraphTest {
    @Override
    protected Graph createGraph() {
        return new AdjacencyMatrixGraph();
    }

    @Test
    void matrixSizeTest() {
        int N = 4;
        AdjacencyMatrixGraph graph = (AdjacencyMatrixGraph) createGraph();

        for (int i = 0; i < N; i++) {
            graph.addNode(new Graph.Node());
        }

        ArrayList<ArrayList<Boolean>> matrix = graph.getMatrix();

        assertEquals(N, matrix.size());

        for (int i = 0; i < N; i++) {
            assertEquals(N, matrix.get(i).size());
        }
    }
}
