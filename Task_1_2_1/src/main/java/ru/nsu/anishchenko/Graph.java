package ru.nsu.anishchenko;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

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

    boolean equals(Object obj);

    String toString();

    default List<Node> topologicalSort() throws RuntimeException {
        ArrayList<Node> nodes = new ArrayList<>(getNodes());
        Stack<Node> stack = new Stack<>();

        HashMap<Node, Integer> flags = new HashMap<>();
        for (Node node : nodes) {
            flags.put(node, 0);
        }

        ArrayList<Node> res = new ArrayList<>();

        for (Node node : nodes) {
            if (flags.get(node) == 0) {
                stack.push(node);
            } else {
                continue;
            }

            while (!stack.empty()) {
                Node peeked_node = stack.peek();

                if (flags.get(peeked_node) == 0) {
                    flags.put(peeked_node, 1);
                    ArrayList<Node> neighbours = getNeighbours(peeked_node);

                    for (Node neighbour : neighbours) {
                        if (flags.get(neighbour) == 0) {
                            stack.push(neighbour);
                        } else if (flags.get(neighbour) == 1) {
                            throw new RuntimeException("Graph with cycle");
                        } else {
                            break;
                        }
                    }
                } else if (flags.get(peeked_node) == 1) {
                    stack.pop();
                    flags.put(peeked_node, 2);
                    res.add(peeked_node);
                }
            }
        }

        return res.reversed();
    }

    class Node {
        // Some data
    }

    class Edge {
        private final Node from;
        private final Node to;

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Edge another_edge)) {
                return false;
            }

            return from.equals(another_edge.getFrom()) && to.equals(another_edge.getTo());
        }

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