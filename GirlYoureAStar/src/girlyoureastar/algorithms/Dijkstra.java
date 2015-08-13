package girlyoureastar.algorithms;

import girlyoureastar.datastructures.Graph;
import girlyoureastar.datastructures.Node;
import girlyoureastar.datastructures.MinHeap;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Luokka tarjoaa metodin lyhimmän reitin etsimiseen verkosta Dijkstran
 * algoritmilla.
 *
 */
public class Dijkstra {

    private Graph graph;
    private MinHeap open;
    private int[] costSoFar;

    public Dijkstra(Graph graph) {
        this.graph = graph;
        this.costSoFar = new int[graph.length()];
        this.open = new MinHeap(graph.length());
    }

    /**
     * Metodi etsii lyhimmän reitin alku- ja loppupisteen välillä.
     *
     * @param start reitin alkupiste
     * @param finish reitin loppupiste
     * @return lyhimmän reitin solmut sisältävä pino
     */
    public Stack findRoute(Node start, Node finish) {
        open.heapInsert(start);
        start.setCost(0);

        while (!open.isEmpty()) {
            Node current = open.heapDelMin();

            if (current.equals(finish)) {
                return shortestPathInAStack(current);
            }

            ArrayList<Node> neighbors = graph.getEdges()[current.getNodeId()];

            for (Node next : neighbors) {
                int newCost = current.getCost() + next.getCostOfMovement();
                if (next.getCost() == -1 || next.getCost() > newCost) {
                    open.heapDecKey(next, newCost);
                    open.heapInsert(next);
                    next.setParent(current);
                }
            }
        }

        return null;
    }

    private Stack shortestPathInAStack(Node end) {
        Stack path = new Stack();

        while (end != null) {
            path.push(end);
            end = end.getParent();
        }

        return path;
    }

}
