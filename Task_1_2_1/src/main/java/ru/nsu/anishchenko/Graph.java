package ru.nsu.anishchenko;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public interface Graph {
    void addNode(Node node);
    void deleteNode(Node node);
    ArrayList<Node> getNodes();

    void addEdge(Node from, Node to);
    void deleteEdge(Node from, Node to);
    ArrayList<Edge> getEdges();

    ArrayList<Node> getNeighbours(Node node);

    void clear();

    default void readFile(String file) throws IOException {
        clear();

        List<String> lines = Files.readAllLines(Paths.get(file));
        ArrayList<Node> nodes = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            nodes.add(new Node());
        }

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).isEmpty()) {
                continue;
            }
            String[] neighbours = lines.get(i).split(" ");

            for (String neighbour : neighbours) {
                addEdge(nodes.get(i), nodes.get(Integer.parseInt(neighbour)));
            }
        }
    }

    String toString();

    class Node {
        // Some data
    }

    class Edge {
        private final Node from;
        private final Node to;

        public Edge(Node source, Node destination) {
            this.from = source;
            this.to = destination;
        }

        public Node getFrom() {
            return from;
        }

        public Node getTo() {
            return to;
        }
    }
}