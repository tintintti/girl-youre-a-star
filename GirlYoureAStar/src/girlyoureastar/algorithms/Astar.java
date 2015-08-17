package girlyoureastar.algorithms;

import girlyoureastar.datastructures.Graph;
import girlyoureastar.datastructures.Node;
import girlyoureastar.datastructures.MinHeap;
import girlyoureastar.datastructures.Stack;
import java.util.ArrayList;

/**
 * Luokka tarjoaa metodin lyhimmän reitin etsimiseen verkosta A*-algoritmilla.
 *
 */
public class Astar {

    private final Graph graph;
    private MinHeap open;
    private final int[] costSoFar;

    public Astar(Graph graph) {
        this.graph = graph;
        this.costSoFar = new int[graph.length()];
        this.open = new MinHeap(graph.length());
    }

    private void initCostSoFar(Node start) {
        for (int i = 0; i < costSoFar.length; i++) {
            costSoFar[i] = -1;
        }
        costSoFar[start.getNodeId()] = 0;
    }

    /**
     * Metodi etsii lyhyimmän reitin verkosta annetun alku- ja loppupisteen
     * välillä.
     *
     * @param start reitin alkupiste
     * @param finish reitin loppupiste
     * @return lyhimmän reitin solmut sisältävä pino
     */
    public Stack findRoute(Node start, Node finish) {
        this.initCostSoFar(start);
        open.heapInsert(start);

        while (!open.isEmpty()) {
            Node current = open.heapDelMin();

            if (current.equals(finish)) {
                return shortestPathInAStack(current);
            }

            ArrayList<Node> neighbors = graph.getEdges()[current.getNodeId()];

            updateNeighbors(neighbors, current, finish);
        }

        return null;
    }

    private void updateNeighbors(ArrayList<Node> neighbors, Node current, Node finish) {
        for (Node next : neighbors) {
            int newCost = costSoFar[current.getNodeId()] + next.getCostOfMovement();
            
            if (costSoFar[next.getNodeId()] == -1) {
                costSoFar[next.getNodeId()] = newCost;
                next.setCost(newCost + manhattanDist(next, finish));
                open.heapInsert(next);
                next.setParent(current);
            } else if (newCost < costSoFar[next.getNodeId()]) {
                costSoFar[next.getNodeId()] = newCost;
                open.heapDecKey(next, newCost + manhattanDist(next, finish));
                next.setParent(current);
            }
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

    private int manhattanDist(Node start, Node finish) {
        return Math.abs(finish.getX() - start.getX()) + Math.abs(finish.getY() - start.getY());
    }

    public int[] getCostSoFar() {
        return costSoFar;
    }

}