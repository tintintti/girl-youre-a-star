package girlyoureastar.datastructures;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {

    Graph g;
    int[][] map;

    public GraphTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        map = new int[6][6];
        g = new Graph(map);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void nodesIsSameSizeAsTheMap() {
        Node[][] nodes = g.getNodes();

        assertEquals(map.length, nodes.length);
        assertEquals(map[0].length, nodes[0].length);
    }

    @Test
    public void edgesIsSameLengthAsTheAmountOfNodesInTheMap() {
        LinkedList[] edges = g.getEdges();

        assertEquals(map.length * map[0].length, edges.length);
    }
}
