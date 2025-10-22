package ru.nsu.anishchenko;

import java.util.ArrayList;

public interface Graph {
    void addNode(Node node);
    void deleteNode(Node node);
    void addEdge(Node from, Node to);
    void deleteEdge(Integer from, Node to);
    ArrayList<Node> getNeighbours(Node node);
    <RET extends Graph> RET readFile(String filename, Class<RET> graphType);

    class Node {
    }
}