package ru.nsu.anishchenko;


class AdjacencyListGraphTest extends GraphTest {
    @Override
    protected Graph createGraph() {
        return new AdjacencyListGraph();
    }
}
