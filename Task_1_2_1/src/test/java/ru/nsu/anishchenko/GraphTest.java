package ru.nsu.anishchenko;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class GraphTest {
    protected Graph graph;

    protected abstract Graph createGraph();

    @Test
    void addNodeTest() {
        graph = createGraph();
        Graph.Node node = new Graph.Node();

        graph.addNode(node);

        assertEquals(graph.getNodes().getFirst(), node);

        graph.addNode(node);

        assertEquals(1, graph.getNodes().size());
    }

    @Test
    void deleteNodeTest() {
        graph = createGraph();
        Graph.Node node = new Graph.Node();

        graph.addNode(node);
        graph.deleteNode(node);

        assertEquals(0, graph.getNodes().size());

        assertThrows(IllegalArgumentException.class, () -> graph.deleteNode(node));
    }

    @Test
    void addEdgeTest() {
        graph = createGraph();
        Graph.Node node_from = new Graph.Node();
        Graph.Node node_to = new Graph.Node();

        graph.addNode(node_from);
        graph.addNode(node_to);

        graph.addEdge(node_from, node_to);

        assertEquals(graph.getEdges().getFirst().getFrom(), node_from);
        assertEquals(graph.getEdges().getFirst().getTo(), node_to);

        graph.addEdge(node_from, node_to);

        assertEquals(1, graph.getEdges().size());

        Graph.Node another_node = new Graph.Node();

        assertThrows(IllegalArgumentException.class, () -> graph.deleteEdge(node_from, another_node));
    }

    @Test
    void deleteEdgeTest() {
        graph = createGraph();
        Graph.Node node_from = new Graph.Node();
        Graph.Node node_to = new Graph.Node();

        graph.addNode(node_from);
        graph.addNode(node_to);

        graph.addEdge(node_from, node_to);

        graph.deleteEdge(node_from, node_to);

        assertEquals(0, graph.getEdges().size());

        graph.addEdge(node_from, node_to);
        graph.deleteNode(node_from);

        assertEquals(0, graph.getEdges().size());

        assertThrows(IllegalArgumentException.class, () -> graph.deleteEdge(node_from, node_to));
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
        assertEquals(node_from, graph.getNeighbours(node_to).getFirst());
    }

    @Test
    void twoGraphsEqualsTest() {
        Graph.Node node1 = new Graph.Node();
        Graph.Node node2 = new Graph.Node();

        Graph graph1 = createGraph();

        graph1.addNode(node1);
        graph1.addNode(node2);

        Graph graph2 = createGraph();

        graph2.addNode(node1);
        graph2.addNode(node2);

        graph2.addEdge(node1, node2);

        assertNotEquals(graph1, graph2);

        graph2.deleteEdge(node1, node2);

        assertEquals(graph1, graph2);
    }

    @Test
    void differentTypesGraphsEqualsTest() {
        Graph.Node node1 = new Graph.Node();
        Graph.Node node2 = new Graph.Node();

        AdjacencyListGraph alg = new AdjacencyListGraph();
        AdjacencyMatrixGraph amg = new AdjacencyMatrixGraph();
        IncidenceMatrixGraph img = new IncidenceMatrixGraph();

        alg.addNode(node1);
        alg.addNode(node2);

        amg.addNode(node1);
        amg.addNode(node2);

        img.addNode(node1);
        img.addNode(node2);

        assertEquals(alg, amg);
        assertEquals(alg, img);
        assertEquals(amg, img);
    }

    @Test
    void topologicalSortTest() {
        graph = createGraph();

        for (int i = 0; i < 6; i++) {
            graph.addNode(new Graph.Node());
        }

        ArrayList<Graph.Node> nodes = graph.getNodes();

        graph.addEdge(nodes.get(0), nodes.get(1));
        graph.addEdge(nodes.get(1), nodes.get(2));
        graph.addEdge(nodes.get(1), nodes.get(3));
        graph.addEdge(nodes.get(2), nodes.get(3));
        graph.addEdge(nodes.get(2), nodes.get(4));
        graph.addEdge(nodes.get(3), nodes.get(4));
        graph.addEdge(nodes.get(3), nodes.get(5));
        graph.addEdge(nodes.get(4), nodes.get(5));

        List<Graph.Node> nodes_t = graph.topologicalSort();

        for (int i = 0; i < 5; i++) {
            assertTrue(nodes_t.indexOf(nodes.get(i)) < nodes_t.indexOf(nodes.get(i + 1)));
        }
    }

    @Test
    void topologicalSortCycleTest() {
        graph = createGraph();

        for (int i = 0; i < 4; i++) {
            graph.addNode(new Graph.Node());
        }

        ArrayList<Graph.Node> nodes = graph.getNodes();

        graph.addEdge(nodes.get(0), nodes.get(1));
        graph.addEdge(nodes.get(1), nodes.get(2));
        graph.addEdge(nodes.get(2), nodes.get(3));
        graph.addEdge(nodes.get(3), nodes.get(1));

        assertThrows(RuntimeException.class, () -> graph.topologicalSort());
    }
}
