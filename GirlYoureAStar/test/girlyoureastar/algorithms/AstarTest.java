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

public class AstarTest {

    private Astar astar;
    private Node start;
    private Node finish;

    public AstarTest() {
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

        astar = new Astar(graph);

        start = new Node(0, 0, 0, 1);
        finish = new Node(1, 5, 0, 1);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void costSoFarIsntNull() {
        assertNotNull(astar.getCostSoFar());
    }

    @Test
    public void returnsTheShortestPathInAnUnWeightedMapWithNoWalls() {
        Stack stack = astar.findRoute(start, finish);
        assertEquals(6, stack.size());
    }

    @Test
    public void pathGoesAroundAWallViaTheShortestPathInAnUnWeightedMap() {
        int[][] map = new int[6][6];
        map[2][0] = 9;

        Graph graph = new Graph(map);

        astar = new Astar(graph);

        Stack path = astar.findRoute(start, finish);

        assertEquals(8, path.size());
    }

    @Test
    public void returnsNullIfThereIsNoPath() {
        int[][] map = new int[6][6];
        map[0][1] = 9;
        map[1][0] = 9;

        Graph graph = new Graph(map);
        astar = new Astar(graph);

        Stack path = astar.findRoute(start, finish);

        assertNull(path);
    }

}
