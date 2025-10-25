package ru.nsu.anishchenko;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Graph interface.
 */
public interface Graph {

    /**
     * Adds node to graph.
     *
     * @param node node that need to add.
     */
    void addNode(Node node);

    /**
     * Delete node from graph.
     *
     * @param node node that need to delete.
     * @throws IllegalArgumentException if there is no node in graph.
     */
    void deleteNode(Node node) throws IllegalArgumentException;

    /**
     * Get all nodes in graph.
     *
     * @return list of graph nodes.
     */
    ArrayList<Node> getNodes();

    /**
     * Add directed edge from {@code from} to {@code to}.
     *
     * @param from node where edge begins.
     * @param to   node where edge ends.
     * @throws IllegalArgumentException if there is no {@code from} or {@code to} node in graph.
     */
    void addEdge(Node from, Node to) throws IllegalArgumentException;


    /**
     * Delete directed edge from {@code from} to {@code to}.
     * Edge needs to be in graph.
     *
     * @param from node where edge begins.
     * @param to   node where edge ends.
     * @throws IllegalArgumentException if there is no {@code from} or {@code to} node in graph.
     */
    void deleteEdge(Node from, Node to) throws IllegalArgumentException;

    /**
     * Get all edges in graph.
     *
     * @return list of graph edges.
     */
    ArrayList<Edge> getEdges();

    /**
     * Get all nodes.
     *
     * @param node node to get neighbours from.
     * @return list of node neighbours.
     * @throws IllegalArgumentException if there is no {@code from} or {@code to} node in graph.
     */
    ArrayList<Node> getNeighbours(Node node) throws IllegalArgumentException;

    /**
     * Delete all nodes and edges.
     */
    void clear();

    /**
     * Build graph from file.
     *
     * @param file path of file.
     * @throws NullPointerException if file does not exist.
     * @throws IOException if IO error occurs.
     */
    default void readFile(String file) throws NullPointerException, IOException {
        clear();

        List<String> lines = Files.readAllLines(Paths.get(file));
        ArrayList<Node> nodes = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            nodes.add(new Node());
            addNode(nodes.get(i));
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

    /**
     * Topological sort of graph.
     *
     * @return topological sorted list of graph nodes.
     * @throws RuntimeException if graph has cycles.
     */
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


    /**
     * Graph Node class.
     */
    class Node {
        // Some data
    }

    /**
     * Graph Edge class.
     */
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

        /**
         * Initializing from and to.
         *
         * @param from node where edge begins.
         * @param to node where edge ends.
         */
        public Edge(Node from, Node to) {
            this.from = from;
            this.to = to;
        }

        /**
         * Get end node.
         *
         * @return node where edge begins.
         */
        public Node getFrom() {
            return from;
        }

        /**
         * Get start node.
         *
         * @return node where edge starts.
         */
        public Node getTo() {
            return to;
        }
    }
}