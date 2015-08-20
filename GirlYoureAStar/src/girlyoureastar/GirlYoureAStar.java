package girlyoureastar;

import girlyoureastar.datastructures.Graph;
import girlyoureastar.datastructures.Node;
import girlyoureastar.datastructures.Stack;
import girlyoureastar.algorithms.Astar;
import girlyoureastar.algorithms.Dijkstra;
import girlyoureastar.datastructures.FibonacciHeap;

public class GirlYoureAStar {

    public static void main(String[] args) {

//        int[][] map = new int[6][6];
//
//        map[1][0] = 9;
//        map[1][1] = 9;
//        map[1][2] = 9;
//        map[1][4] = 9;
//        map[3][4] = 9;
//        map[3][3] = 9;
//        map[3][2] = 9;
//        map[3][0] = 9;
//        map[4][0] = 9;
//        map[4][1] = 3;
//        map[3][1] = 3;
//        map[4][2] = 9;
//
//        Graph g = new Graph(map);
//
//        System.out.println(g);
//
//        Node[][] nodes = g.getNodes();
//
//        Node start = nodes[0][0];
//        Node finish = nodes[5][0];
//
//        Astar a = new Astar(g);
//        Dijkstra d = new Dijkstra(g);
//
//        Stack path = a.findRoute(start, finish);
//
//        while (!path.isEmpty()) {
//            System.out.println(path.pop());
//        }
//
//        printMap(g, finish);
        
        FibonacciHeap heap = new FibonacciHeap();

        Node n1 = new Node(0, 0, 0, 1);
        n1.setCost(15);
        Node n2 = new Node(1, 0, 1, 1);
        n2.setCost(11);
        Node n3 = new Node(2, 0, 2, 1);
        n3.setCost(1);

        heap.insert(n1);
        heap.insert(n2);
        heap.insert(n3);

        System.out.println("heap size: " + heap.size());
        System.out.println("smallest Node value: " + heap.peek().getNode().getCost());

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
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }

    }

}
