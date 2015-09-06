package girlyoureastar;

import girlyoureastar.algorithms.*;
import girlyoureastar.datastructures.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Luokka tarjoaa yksinkertaisen tekstikäyttöliittymän reitinhakualgoritmien ja
 * prioriteettijonototeutuksien kokeilemiseen
 *
 */
public class TextUI {

    private int startX;
    private int endX;
    private int startY;
    private int endY;
    private Scanner scanner;
    private boolean printmaps;

    public TextUI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Metodi käynnistää tekstikäyttöliittymän.
     *
     * @throws FileNotFoundException
     */
    public void start() throws FileNotFoundException {

        System.out.println("Tulostetaanko kartat reitinhaun yhteydessä? y/n");
        String s = scanner.nextLine();
        printmaps = s.equals("y");

        System.out.println("");
        System.out.println("Karttatiedoston nimi:");
        String filename = scanner.nextLine();

        MapFileReader fileReader = new MapFileReader(filename);

        int[][] map = fileReader.readMap();

        while (true) {
            try {

                System.out.println("Alkupisteen rivi:");
                startX = Integer.parseInt(scanner.nextLine());

                System.out.println("Alkupisteen sarake:");
                startY = Integer.parseInt(scanner.nextLine());

                System.out.println("Loppupisteen rivi:");
                endX = Integer.parseInt(scanner.nextLine());

                System.out.println("Loppupisteen sarake:");
                endY = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Virheellinen syöte.\n");
                continue;
            }
            break;
        }

        while (true) {

            Graph g = new Graph(map);

            Node[][] nodes = g.getNodes();

            Node start = nodes[startX][startY];
            Node end = nodes[endX][endY];

            printAlgorithmChoises();

            String algorithm = scanner.nextLine();

            if (algorithm.equals("q")) {
                break;
            }

            printHeapChoises();

            String priorityqueue = scanner.nextLine();

            MinHeap heap;
            RouteFinder finder;

            switch (priorityqueue) {
                case "f":
                    heap = new FibonacciHeap();
                    break;
                case "b":
                    heap = new BinaryHeap(g.length());
                    break;
                default:
                    System.out.println("Virheellinen prioriteettikeon valinta.\n");
                    continue;
            }

            switch (algorithm) {
                case "d":
                    if (printmaps) {
                        printMapWithoutRoute(g, start, end);
                    }
                    finder = new Dijkstra(g, priorityqueue.equals("f"));
                    break;
                case "a":
                    if (printmaps) {
                        printMapWithoutRoute(g, start, end);
                    }
                    System.out.println("");
                    finder = new Astar(g, priorityqueue.equals("f"));
                    break;
                default:
                    System.out.println("Virheellinen algoritmin valinta.\n");
                    continue;
            }

            long timeStart = System.currentTimeMillis();
            finder.findRoute(start, end);
            long timeEnd = System.currentTimeMillis();

            printRouteAndInfo(g, start, end, timeEnd, timeStart);

        }

    }

    private void printRouteAndInfo(Graph g, Node start, Node end, long timeEnd, long timeStart) {
        System.out.println("");
        if (printmaps) {
            System.out.println("Reitti");
            System.out.println("----------------------------------------");
            printMap(g, start, end);
            System.out.println("----------------------------------------\n");
        }
        System.out.println("Reitin pituus siirtymisen hinta huomioonotettuna: " + end.getCost());
        System.out.println("Reitin löytämiseen kului aikaa " + (timeEnd - timeStart) + " ms.");
    }

    private void printMapWithoutRoute(Graph g, Node start, Node end) {
        System.out.println("");
        System.out.println("Kartta");
        System.out.println("----------------------------------------");
        printMap(g, start, end);
        System.out.println("");
        System.out.println("----------------------------------------\n");
    }

    private void printHeapChoises() {
        System.out.println("f = Fibonacci-keko");
        System.out.println("b = binäärikeko");
        System.out.print("> ");
    }

    private void printAlgorithmChoises() {
        System.out.println("d = Dijkstra");
        System.out.println("a = A*");
        System.out.println("q = lopettaa ohjelman");
        System.out.print("> ");
    }

    private void printMap(Graph g, Node start, Node finish) {
        int[][] map = g.getMap();
        int[][] mapCopy = new int[map.length][map[0].length];

        for (int i = 0; i < mapCopy.length; i++) {
            for (int j = 0; j < mapCopy[0].length; j++) {
                mapCopy[i][j] = map[i][j];
            }
        }

        if (finish != null) {
            Node fParent = finish.getParent();

            while (fParent != null && fParent != start) {
                mapCopy[fParent.getX()][fParent.getY()] = 10;
                fParent = fParent.getParent();
            }

            mapCopy[finish.getX()][finish.getY()] = 12;
        }

        if (start != null) {
            mapCopy[start.getX()][start.getY()] = 11;
        }

        for (int i = 0; i < mapCopy.length; i++) {
            for (int j = 0; j < mapCopy[0].length; j++) {
                if (mapCopy[i][j] == 9) {
                    System.out.print("#");
                } else if (mapCopy[i][j] == 0) {
                    System.out.print(" ");
                } else if (mapCopy[i][j] == 10) {
                    System.out.print(".");
                } else if (mapCopy[i][j] == 11) {
                    System.out.print("a");
                } else if (mapCopy[i][j] == 12) {
                    System.out.print("b");
                } else {
                    System.out.print(mapCopy[i][j] + 1);
                }
            }
            System.out.println("");
        }

    }

}
