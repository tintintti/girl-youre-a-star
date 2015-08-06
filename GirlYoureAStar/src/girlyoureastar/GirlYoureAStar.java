package girlyoureastar;

import java.util.Stack;

public class GirlYoureAStar {

    public static void main(String[] args) {

        
        
        char[][] map = new char[6][6];

        map[1][0] = '#';
        map[1][1] = '#';
        map[1][2] = '#';
        map[1][2] = '#';
        map[1][4] = '#';
        map[3][4] = '#';
        map[3][3] = '#';
        map[3][2] = '#';
        map[3][0] = '#';
        map[4][0] = '#';
        map[4][1] = '#';
        map[4][2] = '#';
        map[2][0] = '#';

        Graph g = new Graph(map);

        System.out.println(g);

        Node[][] nodes = g.getNodes();

        Node start = nodes[0][0];
        Node finish = nodes[5][0];

        Astar a = new Astar(g);

        Stack path = a.findRoute(start, finish);

        while (!path.isEmpty()) {
            System.out.println(path.pop());
        }

        printMap(g, finish);

    }

    public static void printMap(Graph g, Node n) {
        char[][] map = g.getMap();

        while (n != null) {
            map[n.getX()][n.getY()] = '.';
            n = n.getParent();
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println("");
        }

    }

}
