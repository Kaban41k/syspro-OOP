package ru.nsu.anishchenko;

import java.util.ArrayList;

/**
 * Adjacency matrix graph interface implementation.
 */
public class AdjacencyMatrixGraph implements Graph {
    private final ArrayList<Node> nodes = new ArrayList<>();
    private final ArrayList<ArrayList<Boolean>> matrix = new ArrayList<>();

    @Override
    public void addNode(Node node) {
        if (nodes.contains(node)) {
            return;
        }

        nodes.add(node);
        matrix.add(new ArrayList<>());

        for (int i = 0; i < matrix.size() - 1; i++) {
            matrix.get(i).add(false);
        }

        for (int i = 0; i < matrix.size(); i++) {
            matrix.getLast().add(false);
        }
    }

    @Override
    public void deleteNode(Node node) throws IllegalArgumentException {
        if (!nodes.contains(node)) {
            throw new IllegalArgumentException("Node is not in graph");
        }

        int node_index = nodes.indexOf(node);

        nodes.remove(node_index);
        matrix.remove(node_index);

        for (ArrayList<Boolean> arr : matrix) {
            arr.remove(node_index);
        }
    }

    @Override
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    @Override
    public void addEdge(Node from, Node to) {
        if (!nodes.contains(from) || !nodes.contains(to)) {
            throw new IllegalArgumentException("Node is not in graph");
        }

        matrix.get(nodes.indexOf(from)).set(nodes.indexOf(to), true);
    }

    @Override
    public void deleteEdge(Node from, Node to) {
        if (!nodes.contains(from) || !nodes.contains(to)) {
            throw new IllegalArgumentException("Node is not in graph");
        }

        matrix.get(nodes.indexOf(from)).set(nodes.indexOf(to), false);
    }

    @Override
    public ArrayList<Edge> getEdges() {
        ArrayList<Edge> res = new ArrayList<>();

        for (int from = 0; from < matrix.size(); from++) {
            for (int to = 0; to < matrix.size(); to++) {
                if (matrix.get(from).get(to)) {
                    res.add(new Edge(nodes.get(from), nodes.get(to)));
                }
            }
        }

        return res;
    }

    @Override
    public ArrayList<Node> getNeighbours(Node node) throws IllegalArgumentException {
        if (!nodes.contains(node)) {
            throw new IllegalArgumentException("Node is not in graph");
        }

        ArrayList<Node> res = new ArrayList<>();
        int node_index = nodes.indexOf(node);

        for (int i = 0; i < matrix.size(); i++) {
            if (matrix.get(node_index).get(i)) {
                res.add(nodes.get(i));
            }
        }

        return res;
    }

    /**
     * Get matrix of graph.
     *
     * @return adjacency matrix.
     */
    public ArrayList<ArrayList<Boolean>> getMatrix() {
        return matrix;
    }

    @Override
    public void clear() {
        nodes.clear();
        matrix.clear();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Graph another_graph)) {
            return false;
        }

        ArrayList<Node> nodes_this = getNodes();
        ArrayList<Node> nodes_other = another_graph.getNodes();
        if (!nodes_this.equals(nodes_other)) {
            return false;
        }

        ArrayList<Edge> edges_this = getEdges();
        ArrayList<Edge> edges_other = another_graph.getEdges();
        if (!edges_this.equals(edges_other)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        for (int from = 0; from < matrix.size(); from++) {
            if (from != 0) res.append("\n");
            boolean firstNeighbour = true;

            for (int to = 0; to < matrix.size(); to++) {
                if (matrix.get(from).get(to)) {
                    if (!firstNeighbour) res.append(" ");

                    res.append(to);
                    firstNeighbour = false;
                }
            }
        }

        return res.toString();
    }
}
