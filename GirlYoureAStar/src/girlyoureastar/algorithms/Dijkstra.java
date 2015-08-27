package girlyoureastar.algorithms;

import girlyoureastar.datastructures.*;

/**
 * Luokka tarjoaa metodin lyhimmän reitin etsimiseen verkosta Dijkstran
 * algoritmilla.
 *
 */
public class Dijkstra {

    private Graph graph;
    private MinHeap open;
    private int[] costSoFar;

    public Dijkstra(Graph graph, boolean fibonacci) {
        this.graph = graph;
        this.costSoFar = new int[graph.length()];
        if (fibonacci) {
            open = new FibonacciHeap();
        } else {
            open = new BinaryHeap(graph.length());
        }
    }

    /**
     * Metodi etsii lyhimmän reitin alku- ja loppupisteen välillä.
     *
     * @param start reitin alkupiste
     * @param finish reitin loppupiste
     * @return lyhimmän reitin solmut sisältävä pino
     */
    public Stack findRoute(Node start, Node finish) {
        start.setCost(0);
        open.insert(start);

        while (!open.isEmpty()) {
            Node current = open.delMin();

            if (current.equals(finish)) {
                return shortestPathInAStack(current);
            }

            LinkedList neighbors = graph.getEdges()[current.getNodeId()];

            updateNeighbors(neighbors, current);
        }

        return null;
    }
    

    private void updateNeighbors(LinkedList neighbors, Node current) {

        LinkedListNode nextListNode = neighbors.getHead();

        while (nextListNode != null) {
            Node next = nextListNode.getKey();
            int newCost = current.getCost() + next.getCostOfMovement();

            if (next.getCost() == -1) {
                next.setCost(newCost);
                open.insert(next);
                next.setParent(current);
            } else if (next.getCost() > newCost) {
                next.setCost(newCost);
                open.decreaseKey(next, newCost);
                next.setParent(current);
            }

            nextListNode = nextListNode.getNext();
        }
    }

    private Stack shortestPathInAStack(Node end) {
        Stack path = new Stack(graph.length());

        while (end != null) {
            path.push(end);
            end = end.getParent();
        }

        return path;
    }

}