package girlyoureastar;

import girlyoureastar.datastructures.Graph;
import girlyoureastar.datastructures.Node;
import girlyoureastar.algorithms.Astar;
import girlyoureastar.algorithms.Dijkstra;
import java.util.Stack;

public class GirlYoureAStar {

    public static void main(String[] args) {

        int[][] map = new int[6][6];

        map[1][0] = 9;
        map[1][1] = 9;
        map[1][2] = 9;
        map[1][4] = 9;
        map[3][4] = 9;
        map[3][3] = 9;
        map[3][2] = 9;
        map[3][0] = 9;
        map[4][0] = 9;
        map[4][1] = 9;
        map[4][2] = 9;
        map[2][0] = 9;

        Graph g = new Graph(map);

        System.out.println(g);

        Node[][] nodes = g.getNodes();

        Node start = nodes[0][0];
        Node finish = nodes[5][0];

        Astar a = new Astar(g);
        Dijkstra d = new Dijkstra(g);

        Stack path = a.findRoute(start, finish);

        while (!path.isEmpty()) {
            System.out.println(path.pop());
        }

        printMap(g, finish);

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
