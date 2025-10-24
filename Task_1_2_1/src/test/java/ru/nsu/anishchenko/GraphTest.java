package ru.nsu.anishchenko;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

abstract class GraphTest {
    protected Graph graph;

    protected abstract Graph createGraph();

    @Test
    void singleElementTest() {
        graph = createGraph();
        Graph.Node node = new Graph.Node();

        graph.addNode(node);

        assertEquals(graph.getNodes().getFirst(), node);

        graph.deleteNode(node);

        assertEquals(0, graph.getNodes().size());
    }

    @Test
    void singleEdgeTest() {
        graph = createGraph();
        Graph.Node node_from = new Graph.Node();
        Graph.Node node_to = new Graph.Node();

        graph.addNode(node_from);
        graph.addNode(node_to);

        graph.addEdge(node_from, node_to);

        assertEquals(graph.getEdges().getFirst().getFrom(), node_from);
        assertEquals(graph.getEdges().getFirst().getTo(), node_to);

        graph.deleteNode(node_from);

        assertEquals(1, graph.getNodes().size());
        assertEquals(0, graph.getEdges().size());
    }

    @Test
    void twoElementStringTest() {
        graph = createGraph();
        Graph.Node node_from = new Graph.Node();
        Graph.Node node_to = new Graph.Node();

        graph.addNode(node_from);
        graph.addNode(node_to);

        graph.addEdge(node_from, node_to);
        graph.addEdge(node_to, node_from);


        assertEquals("1\n0", graph.toString());
    }

    @Test
    void twoElementNeighboursTest() {
        graph = createGraph();
        Graph.Node node_from = new Graph.Node();
        Graph.Node node_to = new Graph.Node();

        graph.addNode(node_from);
        graph.addNode(node_to);

        graph.addEdge(node_from, node_to);
        graph.addEdge(node_to, node_from);


        assertEquals(node_to, graph.getNeighbours(node_from).getFirst());
    }
}
