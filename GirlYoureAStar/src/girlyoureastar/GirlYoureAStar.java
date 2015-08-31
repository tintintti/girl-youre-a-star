package girlyoureastar;

import girlyoureastar.algorithms.*;
import girlyoureastar.datastructures.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GirlYoureAStar {

    public static void main(String[] args) {
        try {
            mazeSolving();
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löydy.");
        }

    }

    private static void mazeSolving() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Karttatiedoston nimi:");
        String filename = scanner.nextLine();

        MapFileReader fileReader = new MapFileReader(filename);

        int[][] map = fileReader.readMap();

        int startX;
        int endX;
        int startY;
        int endY;

        while (true) {
            try {
                System.out.println("Alkupisteen x-koordinaatti:");
                startX = Integer.parseInt(scanner.nextLine());

                System.out.println("Alkupisteen y-koordinaatti:");
                startY = Integer.parseInt(scanner.nextLine());

                System.out.println("Loppupisteen x-koordinaatti:");
                endX = Integer.parseInt(scanner.nextLine());

                System.out.println("Loppupisteen y-koordinaatti:");
                endY = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Virheellinen koordinaatti.\n"
                        + "Anna koordinaatit uudelleen.\n");
                continue;
            }
            break;
        }

        while (true) {

            Graph g = new Graph(map);

            Node[][] nodes = g.getNodes();

            Node start = nodes[startX][startY];
            Node end = nodes[endX][endY];

            System.out.println("d = Dijkstra");
            System.out.println("a = A*");
            System.out.println("q = lopettaa ohjelman");
            System.out.print("> ");

            String algorithm = scanner.nextLine();

            if (algorithm.equals("q")) {
                break;
            }

            System.out.println("f = Fibonacci-keko");
            System.out.println("b = binäärikeko");
            System.out.print("> ");

            String priorityqueue = scanner.nextLine();

            MinHeap heap;
            RouteFinding finder;

            switch (priorityqueue) {
                case "f":
                    heap = new FibonacciHeap();
                    break;
                case "b":
                    heap = new BinaryHeap(g.length());
                    break;
                default:
                    System.out.println("Virheellinen prioriteettikeon valinta. Valitse 'f' tai 'b'\n");
                    continue;
            }

            switch (algorithm) {
                case "d":
                    System.out.println(g);
                    System.out.println("");
                    finder = new Dijkstra(g, priorityqueue.equals("f"));
                    break;
                case "a":
                    System.out.println(g);
                    System.out.println("");
                    finder = new Astar(g, priorityqueue.equals("f"));
                    break;
                default:
                    System.out.println("Virheellinen algoritmin valinta. Valitse 'd' tai 'a'\n");
                    continue;
            }

            long timeStart = System.currentTimeMillis();
            finder.findRoute(start, end);
            long timeEnd = System.currentTimeMillis();
            printMap(g, end);
            System.out.println("Reitin pituus siirtymisen hinta huomioonotettuna: " + end.getCost());
            System.out.println("Reitin löytämiseen kului aikaa " + (timeEnd - timeStart) + " ms.");

        }
    }

    public static void printMap(Graph g, Node finish) {
        int[][] map = g.getMap();
        int[][] mapCopy = new int[map.length][map[0].length];

        for (int i = 0; i < mapCopy.length; i++) {
            for (int j = 0; j < mapCopy[0].length; j++) {
                mapCopy[i][j] = map[i][j];
            }
        }

        while (finish != null) {
            mapCopy[finish.getX()][finish.getY()] = 10;
            finish = finish.getParent();
        }

        for (int i = 0; i < mapCopy.length; i++) {
            for (int j = 0; j < mapCopy[0].length; j++) {
                if (mapCopy[i][j] == 9) {
                    System.out.print("#");
                } else if (mapCopy[i][j] == 10) {
                    System.out.print(".");
                } else {
                    System.out.print(mapCopy[i][j] + 1);
                }
            }
            System.out.println("");
        }

    }

}
