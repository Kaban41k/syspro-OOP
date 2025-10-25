package ru.nsu.anishchenko;

import java.util.ArrayList;

public class AdjacencyListGraph implements Graph {
    private final ArrayList<Node> nodes = new ArrayList<>();
    private final ArrayList<ArrayList<Node>> lists = new ArrayList<>();

    @Override
    public void addNode(Node node) {
        if (nodes.contains(node)) {
            return;
        }

        nodes.add(node);
        lists.add(new ArrayList<>());
    }

    @Override
    public void deleteNode(Node node) throws IllegalArgumentException {
        if (!nodes.contains(node)) {
            throw new IllegalArgumentException("Node is not in graph");
        }

        for (ArrayList<Node> list : lists) {
            list.remove(node);
        }

        int node_index = nodes.indexOf(node);

        nodes.remove(node_index);
        lists.remove(node_index);
    }

    @Override
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    @Override
    public void addEdge(Node from, Node to) throws IllegalArgumentException {
        if (!nodes.contains(from) || !nodes.contains(to)) {
            throw new IllegalArgumentException("Node is not in graph");
        }

        Edge edge_current = new Edge(from, to);

        for (Edge edge : getEdges()) {
            if (edge.equals(edge_current)) {
                return;
            }
        }

        int from_index = nodes.indexOf(from);

        lists.get(from_index).add(to);
    }

    @Override
    public void deleteEdge(Node from, Node to) {
        if (!nodes.contains(from) || !nodes.contains(to)) {
            throw new IllegalArgumentException("Node is not in graph");
        }

        int from_index = nodes.indexOf(from);

        lists.get(from_index).remove(to);
    }

    @Override
    public ArrayList<Edge> getEdges() {
        ArrayList<Edge> res = new ArrayList<>();

        for (int from = 0; from < lists.size(); from++) {
            for (int to = 0; to < lists.get(from).size(); to++) {
                res.add(new Edge(nodes.get(from), lists.get(from) .get(to)));
            }
        }

        return res;
    }

    @Override
    public ArrayList<Node> getNeighbours(Node node) {
        return (ArrayList<Node>) lists.get(nodes.indexOf(node)).clone();
    }

    @Override
    public void clear() {
        nodes.clear();
        lists.clear();
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

        for (int from = 0; from < lists.size(); from++) {
            if (from != 0) res.append("\n");

            for (int to = 0; to < lists.get(from).size(); to++) {
                if (to != 0) res.append(" ");

                res.append(nodes.indexOf(lists.get(from).get(to)));
            }
        }

        return res.toString();
    }
}
