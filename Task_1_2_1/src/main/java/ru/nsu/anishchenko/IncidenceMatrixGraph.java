package ru.nsu.anishchenko;

import java.util.ArrayList;

/**
 * Incidence matrix graph interface implementation.
 */
public class IncidenceMatrixGraph implements Graph {
    private final ArrayList<Node> nodes = new ArrayList<>();
    private final ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

    @Override
    public void addNode(Node node) {
        if (nodes.contains(node)) {
            return;
        }

        nodes.add(node);

        for (ArrayList<Integer> list : matrix) {
            list.add(0);
        }
    }

    @Override
    public void deleteNode(Node node) throws IllegalArgumentException {
        if (!nodes.contains(node)) {
            throw new IllegalArgumentException("Node is not in graph");
        }

        int nodeIndex = nodes.indexOf(node);

        nodes.remove(nodeIndex);

        int i = 0;

        while (i < matrix.size()) {
            if (matrix.get(i).get(nodeIndex) != 0) {
                matrix.remove(i);
                continue;
            }

            i++;
        }

        for (ArrayList<Integer> list : matrix) {
            list.remove(nodeIndex);
        }
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

        matrix.add(new ArrayList<>());

        for (int i = 0; i < nodes.size(); i++) {
            matrix.getLast().add(0);
        }

        matrix.getLast().set(nodes.indexOf(from), 1);
        matrix.getLast().set(nodes.indexOf(to), -1);
    }

    @Override
    public void deleteEdge(Node from, Node to) throws IllegalArgumentException {
        if (!nodes.contains(from) || !nodes.contains(to)) {
            throw new IllegalArgumentException("Node is not in graph");
        }

        for (int i = 0; i < matrix.size(); i++) {
            if (matrix.get(i).get(nodes.indexOf(from)) == 1 &&
                    matrix.get(i).get(nodes.indexOf(to)) == -1) {
                matrix.remove(i);
                return;
            }
        }
    }

    @Override
    public ArrayList<Edge> getEdges() {
        ArrayList<Edge> res = new ArrayList<>();

        for (ArrayList<Integer> integers : matrix) {
            Node from = null;
            Node to = null;

            for (int j = 0; j < integers.size(); j++) {
                if (integers.get(j) == 1) {
                    from = nodes.get(j);
                    if (to != null) {
                        break;
                    }
                }

                if (integers.get(j) == -1) {
                    to = nodes.get(j);
                    if (from != null) {
                        break;
                    }
                }
            }

            res.add(new Edge(from, to));
        }

        return res;
    }

    @Override
    public ArrayList<Node> getNeighbours(Node node) throws IllegalArgumentException {
        if (!nodes.contains(node)) {
            throw new IllegalArgumentException("Node is not in graph");
        }

        ArrayList<Node> res = new ArrayList<>();

        int nodeIndex = nodes.indexOf(node);

        for (ArrayList<Integer> integers : matrix) {
            if (integers.get(nodeIndex) != 1) {
                continue;
            }

            for (int j = 0; j < integers.size(); j++) {
                if (integers.get(j) == -1) {
                    res.add(nodes.get(j));
                }
            }
        }

        return res;
    }

    @Override
    public void clear() {
        nodes.clear();
        matrix.clear();
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
        return edgesThis.equals(edgesOther);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        for (int from = 0; from < nodes.size(); from++) {
            if (from != 0) {
                res.append("\n");
            }
            boolean firstNeighbour = true;

            for (ArrayList<Integer> integers : matrix) {
                if (integers.get(from) != 1) {
                    continue;
                }

                for (int j = 0; j < integers.size(); j++) {
                    if (integers.get(j) == -1) {
                        if (!firstNeighbour) {
                            res.append(" ");
                        }

                        res.append(j);
                        firstNeighbour = false;
                    }
                }
            }

        }

        return res.toString();
    }
}
