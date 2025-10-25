package ru.nsu.anishchenko;

import java.util.ArrayList;

/**
 * Adjacency list graph interface implementation.
 */
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

        int nodeIndex = nodes.indexOf(node);

        nodes.remove(nodeIndex);
        lists.remove(nodeIndex);
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

        Edge edgeCurrent = new Edge(from, to);

        for (Edge edge : getEdges()) {
            if (edge.equals(edgeCurrent)) {
                return;
            }
        }

        int fromIndex = nodes.indexOf(from);

        lists.get(fromIndex).add(to);
    }

    @Override
    public void deleteEdge(Node from, Node to) throws IllegalArgumentException{
        if (!nodes.contains(from) || !nodes.contains(to)) {
            throw new IllegalArgumentException("Node is not in graph");
        }

        int fromIndex = nodes.indexOf(from);

        lists.get(fromIndex).remove(to);
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
    public ArrayList<Node> getNeighbours(Node node) throws IllegalArgumentException {
        if (!nodes.contains(node)) {
            throw new IllegalArgumentException("Node is not in graph");
        }

        return (ArrayList<Node>) lists.get(nodes.indexOf(node)).clone();
    }

    @Override
    public void clear() {
        nodes.clear();
        lists.clear();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Graph anotherGraph)) {
            return false;
        }

        ArrayList<Node> nodesThis = getNodes();
        ArrayList<Node> nodesOther = anotherGraph.getNodes();
        if (!nodesThis.equals(nodesOther)) {
            return false;
        }

        ArrayList<Edge> edgesThis = getEdges();
        ArrayList<Edge> edgesOther = anotherGraph.getEdges();
        if (!edgesThis.equals(edgesOther)) {
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
                if (to != 0) {
                    res.append(" ");
                }

                res.append(nodes.indexOf(lists.get(from).get(to)));
            }
        }

        return res.toString();
    }
}
