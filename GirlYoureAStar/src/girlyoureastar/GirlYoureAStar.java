package girlyoureastar;

import girlyoureastar.algorithms.*;
import girlyoureastar.datastructures.*;
import java.util.LinkedList;

public class GirlYoureAStar {

    public static void main(String[] args) {

        searchTest();
//        fiboTest();

    }

    public static void searchTest() {
        int[][] map = new int[11][21];

        map[1][0] = 9;
        map[1][1] = 9;
        map[1][2] = 9;
        map[1][4] = 9;
        map[3][4] = 9;
        map[3][3] = 9;
        map[3][2] = 9;
        map[3][0] = 9;
        map[4][0] = 9;
        map[4][1] = 3;
        map[3][1] = 3;
        map[4][2] = 9;
        map[5][5] = 9;
        map[6][5] = 9;
        map[5][6] = 3;
        map[6][6] = 3;
        map[5][6] = 3;
        map[6][6] = 3;
        map[5][4] = 3;
        map[6][4] = 3;
        map[5][3] = 3;
        map[6][3] = 3;
        map[5][2] = 3;
        map[6][2] = 3;
        map[5][1] = 3;
        map[6][1] = 3;
        map[5][0] = 3;
        map[6][0] = 3;

        Graph g = new Graph(map);

        System.out.println(g);

        Node[][] nodes1 = g.getNodes();

        Node start = nodes1[0][0];
        Node finish = nodes1[7][0];

        Astar a = new Astar(g, true);
        Dijkstra d = new Dijkstra(g, true);

        Stack path = d.findRoute(start, finish);

        System.out.println("");
        if (path != null) {
            while (!path.isEmpty()) {
                Node n = path.pop();
                System.out.println(n);
            }
        }

        System.out.println("");

        printMap(g, finish);
        System.out.println("Matkan pituus: " + finish.getCost());

    }

    public static void fiboTest() {

        FibonacciHeap heap = new FibonacciHeap();

        Node n1 = new Node(0, 0, 0, 1);
        Node n2 = new Node(1, 0, 1, 1);
        Node n3 = new Node(2, 0, 2, 1);
        Node n4 = new Node(3, 0, 3, 1);
        Node n5 = new Node(4, 0, 4, 1);
        Node n6 = new Node(5, 0, 5, 1);
        Node n7 = new Node(6, 0, 6, 1);
        Node n8 = new Node(7, 0, 7, 1);
        Node n9 = new Node(8, 0, 8, 1);
        Node n10 = new Node(9, 0, 9, 1);

        n1.setCost(10);
        n2.setCost(22);
        n3.setCost(67);
        n4.setCost(23);
        n5.setCost(63);
        n6.setCost(15);
        n7.setCost(24);
        n8.setCost(8);
        n9.setCost(27);
        n10.setCost(20);

        heap.insert(n1);
        heap.insert(n2);
        heap.insert(n3);
        heap.insert(n4);

        heap.decreaseKey(n2, 3);

        heap.insert(n5);
        heap.insert(n6);
        heap.insert(n7);

        heap.decreaseKey(n7, 2);

        heap.insert(n8);
        heap.insert(n9);
        heap.insert(n10);

        heap.decreaseKey(n10, 10);
        heap.decreaseKey(n3, 4);
        heap.decreaseKey(n8, 1);
        heap.decreaseKey(n6, 3);
        heap.decreaseKey(n4, 5);
        
        printTrees(heap);

        for (int i = 0; i < 10; i++) {
            System.out.println(heap.size());
            System.out.println(heap.delMin());
            System.out.println("");
            printTrees(heap);
        }

    }

    public static void printIdAndCost(Node n) {
        if (n == null) {
            System.out.println(n);
        } else {
            System.out.println("id: " + n.getNodeId() + " cost: " + n.getCost());
            System.out.println(n.getFNode().getLeft().getNode());
            System.out.println(n.getFNode().getRight().getNode());
        }
    }

    public static void printTrees(FibonacciHeap heap) {
        System.out.println("tulostetaan puu:");
        
        if (heap.peek() == null) {
            System.out.println("Puu on tyhjä");
            System.out.println("----------------");
            return;
        }
        
        FNode start = heap.peek().getFNode();
        
        do {
            LinkedList<FNode> q = new LinkedList<>();
            q.add(start);

            while (!q.isEmpty()) {
                FNode n = q.poll();
                System.out.print("[id: " + n.getNode().getNodeId() + " d: " + n.getDegree()
                        + " prev: " + n.getLeft().getNode()
                        + " next: " + n.getRight().getNode() + "] ");
                if (n.getChild() != null) {
                    FNode child = n.getChild();
                    do {
                        q.add(child);
                        child = child.getRight();
                    } while (child != n.getChild());
                }
            }
            System.out.println("");
            start = start.getRight();
        } while (start != heap.peek().getFNode());
        System.out.println("----------------");
    }

    public static void printMap(Graph g, Node n) {
        int[][] map = g.getMap();

        while (n != null) {
            map[n.getX()][n.getY()] = 10;
            n = n.getParent();
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 9) {
                    System.out.print("#");
                } else if (map[i][j] == 10) {
                    System.out.print(".");
                } else {
                    System.out.print(map[i][j] + 1);
                }
            }
            System.out.println("");
        }

    }

}
