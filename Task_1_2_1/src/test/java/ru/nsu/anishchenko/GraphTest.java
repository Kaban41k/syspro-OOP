package ru.nsu.anishchenko;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


abstract class GraphTest {
    protected Graph graph;

    @TempDir
    Path tempDir;

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
        Graph.Node nodeFrom = new Graph.Node();
        Graph.Node nodeTo = new Graph.Node();

        graph.addNode(nodeFrom);
        graph.addNode(nodeTo);

        graph.addEdge(nodeFrom, nodeTo);

        assertEquals(graph.getEdges().getFirst().getFrom(), nodeFrom);
        assertEquals(graph.getEdges().getFirst().getTo(), nodeTo);

        graph.addEdge(nodeFrom, nodeTo);

        assertEquals(1, graph.getEdges().size());

        Graph.Node anotherNode = new Graph.Node();

        assertThrows(IllegalArgumentException.class, () -> graph.deleteEdge(nodeFrom, anotherNode));
    }

    @Test
    void deleteEdgeTest() {
        graph = createGraph();
        Graph.Node nodeFrom = new Graph.Node();
        Graph.Node nodeTo = new Graph.Node();

        graph.addNode(nodeFrom);
        graph.addNode(nodeTo);

        graph.addEdge(nodeFrom, nodeTo);

        graph.deleteEdge(nodeFrom, nodeTo);

        assertEquals(0, graph.getEdges().size());

        graph.addEdge(nodeFrom, nodeTo);
        graph.deleteNode(nodeFrom);

        assertEquals(0, graph.getEdges().size());

        assertThrows(IllegalArgumentException.class, () -> graph.deleteEdge(nodeFrom, nodeTo));
    }

    @Test
    void twoElementStringTest() {
        graph = createGraph();
        Graph.Node nodeFrom = new Graph.Node();
        Graph.Node nodeTo = new Graph.Node();

        graph.addNode(nodeFrom);
        graph.addNode(nodeTo);

        graph.addEdge(nodeFrom, nodeTo);
        graph.addEdge(nodeTo, nodeFrom);


        assertEquals("1\n0", graph.toString());
    }

    @Test
    void twoElementNeighboursTest() {
        graph = createGraph();
        Graph.Node nodeFrom = new Graph.Node();
        Graph.Node nodeTo = new Graph.Node();

        graph.addNode(nodeFrom);
        graph.addNode(nodeTo);

        graph.addEdge(nodeFrom, nodeTo);
        graph.addEdge(nodeTo, nodeFrom);


        assertEquals(nodeTo, graph.getNeighbours(nodeFrom).getFirst());
        assertEquals(nodeFrom, graph.getNeighbours(nodeTo).getFirst());
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
        alg.addNode(node1);
        alg.addNode(node2);
        alg.addEdge(node1, node2);

        AdjacencyMatrixGraph amg = new AdjacencyMatrixGraph();
        amg.addNode(node1);
        amg.addNode(node2);
        amg.addEdge(node1, node2);

        IncidenceMatrixGraph img = new IncidenceMatrixGraph();
        img.addNode(node1);
        img.addNode(node2);
        img.addEdge(node1, node2);

        assertEquals(alg, amg);
        assertEquals(alg, img);
        assertEquals(amg, img);
    }

    @Test
    void readFileTest() throws IOException {
        assertThrows(NullPointerException.class, () -> graph.readFile("/1*(&@ (* #98q273 0_-98"));

        Path file = tempDir.resolve("test.txt");
        String content = "1\n2\n0\n";
        Files.writeString(file, content);

        graph = createGraph();
        graph.readFile(file.toString());

        Graph graph2 = createGraph();

        ArrayList<Graph.Node> nodes = graph.getNodes();

        graph2.addNode(nodes.get(0));
        graph2.addNode(nodes.get(1));
        graph2.addNode(nodes.get(2));

        graph2.addEdge(nodes.get(0), nodes.get(1));
        graph2.addEdge(nodes.get(1), nodes.get(2));
        graph2.addEdge(nodes.get(2), nodes.get(0));

        assertEquals(graph, graph2);
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

        List<Graph.Node> nodesT = graph.topologicalSort();

        for (int i = 0; i < 5; i++) {
            assertTrue(nodesT.indexOf(nodes.get(i)) < nodesT.indexOf(nodes.get(i + 1)));
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
