package girlyoureastar;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Luokka tarjoaa metodin lyhimmän reitin etsimiseen verkosta.
 *
 */
public class Astar {

    private final Graph graph;
    private PriorityQueue<Node> open;
    private final int[] costSoFar;

    public Astar(Graph graph) {
        this.graph = graph;
        this.costSoFar = new int[graph.length()];
        this.open = new PriorityQueue<>();
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
     * @return reitin loppupisteen Node, jonka parent-nodeja seuraamalla saa
     * lyhimmän polun
     */
    public Stack findRoute(Node start, Node finish) {
        this.initCostSoFar(start);
        open.add(start);

        while (!open.isEmpty()) {
            Node current = open.poll();

            if (current.equals(finish)) {
                return shortestPathInAStack(current);
            }

            ArrayList<Node> neighbors = graph.getEdges()[current.getNodeId()];

            for (Node next : neighbors) {
                int newCost = costSoFar[current.getNodeId()] + 1;
                if (costSoFar[next.getNodeId()] == -1 || newCost < costSoFar[next.getNodeId()]) {
                    costSoFar[next.getNodeId()] = newCost;
                    next.setCost(newCost + manhattanDist(next, finish));
                    open.add(next);
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

    private int manhattanDist(Node start, Node finish) {
        return Math.abs(finish.getX() - start.getX()) + Math.abs(finish.getY() - start.getY());
    }

    public int[] getCostSoFar() {
        return costSoFar;
    }

}
