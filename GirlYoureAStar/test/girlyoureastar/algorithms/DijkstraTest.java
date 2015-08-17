package girlyoureastar.algorithms;

import girlyoureastar.datastructures.Graph;
import girlyoureastar.datastructures.Node;
import girlyoureastar.datastructures.Stack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DijkstraTest {

    private Dijkstra dijkstra;
    private Node start;
    private Node finish;

    public DijkstraTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        int[][] map = new int[6][6];
        Graph graph = new Graph(map);

        dijkstra = new Dijkstra(graph);

        Node[][] nodes = graph.getNodes();

        start = nodes[0][0];
        finish = nodes[5][0];
    }

    @After
    public void tearDown() {
    }

    @Test
    public void returnsTheShortestPathInAMapWithNoWalls() {
        Stack stack = dijkstra.findRoute(start, finish);
        assertEquals(6, stack.size());
    }

    @Test
    public void pathGoesAroundAWallViaTheShortestPath() {
        int[][] map = new int[6][6];
        map[2][0] = 9;

        Graph graph = new Graph(map);

        dijkstra = new Dijkstra(graph);

        Stack path = dijkstra.findRoute(start, finish);

        assertEquals(8, path.size());
    }

    @Test
    public void returnsNullIfThereIsNoPath() {
        int[][] map = new int[6][6];
        map[0][1] = 9;
        map[1][0] = 9;

        Graph graph = new Graph(map);
        dijkstra = new Dijkstra(graph);

        Stack path = dijkstra.findRoute(start, finish);

        assertNull(path);
    }
}
