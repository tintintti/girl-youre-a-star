package girlyoureastar.algorithms;

import girlyoureastar.datastructures.Graph;
import girlyoureastar.datastructures.Node;
import girlyoureastar.datastructures.BinaryHeap;
import girlyoureastar.datastructures.FibonacciHeap;
import girlyoureastar.datastructures.LinkedList;
import girlyoureastar.datastructures.LinkedListNode;
import girlyoureastar.datastructures.MinHeap;
import girlyoureastar.datastructures.Stack;

/**
 * Luokka tarjoaa metodin lyhimmän reitin etsimiseen verkosta A*-algoritmilla.
 *
 */
public class Astar {

    private final Graph graph;
    private MinHeap open;
    private final int[] costSoFar;

    public Astar(Graph graph, boolean fibonacci) {
        this.graph = graph;
        this.costSoFar = new int[graph.length()];
        if (fibonacci) {
            this.open = new FibonacciHeap();
        } else {
            this.open = new BinaryHeap(graph.length());
        }
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
        open.insert(start);

        while (!open.isEmpty()) {
            Node current = open.delMin();

            if (current.equals(finish)) {
                return shortestPathInAStack(current);
            }

            LinkedList neighbors = graph.getEdges()[current.getNodeId()];

            updateNeighbors(neighbors, current, finish);
        }

        return null;
    }

    private void updateNeighbors(LinkedList neighbors, Node current, Node finish) {
        
        LinkedListNode nextListNode = neighbors.getHead();
        
        while (nextListNode != null) {
            Node next = nextListNode.getKey();
            int newCost = costSoFar[current.getNodeId()] + next.getCostOfMovement();

            if (costSoFar[next.getNodeId()] == -1) {
                costSoFar[next.getNodeId()] = newCost;
                next.setCost(newCost + manhattanDist(next, finish));
                open.insert(next);
                next.setParent(current);
            } else if (newCost < costSoFar[next.getNodeId()]) {
                costSoFar[next.getNodeId()] = newCost;
                open.decreaseKey(next, newCost + manhattanDist(next, finish));
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

    private int manhattanDist(Node start, Node finish) {
        return Math.abs(finish.getX() - start.getX()) + Math.abs(finish.getY() - start.getY());
    }

    public int[] getCostSoFar() {
        return costSoFar;
    }

}
