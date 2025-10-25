package ru.nsu.anishchenko;


class IndenceMatrixGraphTest extends GraphTest {
    @Override
    protected Graph createGraph() {
        return new IncidenceMatrixGraph();
    }
}
