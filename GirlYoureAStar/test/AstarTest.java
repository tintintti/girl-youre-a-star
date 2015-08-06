
import girlyoureastar.Astar;
import girlyoureastar.Graph;
import girlyoureastar.Node;
import java.util.Stack;
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
        char[][] map = new char[6][6];
        Graph graph = new Graph(map);

        astar = new Astar(graph);

        start = new Node(0, 0, 0);
        finish = new Node(1, 5, 0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void costSoFarIsntNull() {
        assertNotNull(astar.getCostSoFar());
    }

    @Test
    public void returnsTheShortestPathInAMapWithNoWalls() {
        Stack stack = astar.findRoute(start, finish);
        assertEquals(6, stack.size());
    }

    @Test
    public void pathGoesAroundAWallViaTheShortestPath() {
        char[][] map = new char[6][6];
        map[2][0] = '#';

        Graph graph = new Graph(map);

        astar = new Astar(graph);

        Stack path = astar.findRoute(start, finish);

        assertEquals(8, path.size());
    }

}
