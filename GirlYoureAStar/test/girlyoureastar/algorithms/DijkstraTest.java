package girlyoureastar.algorithms;

import girlyoureastar.datastructures.Graph;
import girlyoureastar.datastructures.Node;
import girlyoureastar.datastructures.Stack;
import java.util.Random;
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
    private int[][] weightedMap;

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

        dijkstra = new Dijkstra(graph, false);

        Node[][] nodes = graph.getNodes();

        start = nodes[0][0];
        finish = nodes[5][0];
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testShortestPathInAnUnWeightedMapWithNoWalls() {
        Stack stack = dijkstra.findRoute(start, finish);
        assertEquals(6, stack.size());
    }

    @Test
    public void testShortestPathInAnUnWeightedMap() {
        int[][] map = new int[6][6];
        map[2][0] = 9;

        Graph graph = new Graph(map);

        dijkstra = new Dijkstra(graph, false);

        Stack path = dijkstra.findRoute(start, finish);

        assertEquals(8, path.size());
    }

    @Test
    public void returnsNullIfThereIsNoPath() {
        int[][] map = new int[6][6];
        map[0][1] = 9;
        map[1][0] = 9;

        Graph graph = new Graph(map);
        dijkstra = new Dijkstra(graph, false);

        Stack path = dijkstra.findRoute(start, finish);

        assertNull(path);
    }
    
    @Test
    public void findsShortestRouteWithFibonacciInAMapWithWeightedNodes() {
        setWeightedMap();

        Graph g = new Graph(weightedMap);
        
        dijkstra = new Dijkstra(g, true);
        
        Node[][] nodes1 = g.getNodes();

        start = nodes1[0][0];
        finish = nodes1[10][0];
        
        Stack s = dijkstra.findRoute(start, finish);
        
        assertEquals(20, finish.getCost());
    }
    
    @Test
    public void findsShortestRouteWithBinaryHeapInAMapWithWeightedNodes() {
        setWeightedMap();

        Graph g = new Graph(weightedMap);
        
        dijkstra = new Dijkstra(g, false);
        
        Node[][] nodes1 = g.getNodes();

        start = nodes1[0][0];
        finish = nodes1[10][0];
        
        Stack s = dijkstra.findRoute(start, finish);
        
        assertEquals(20, finish.getCost());
    }
    
 
    private void setWeightedMap() {
        weightedMap = new int[11][21];

        weightedMap[1][0] = 9;
        weightedMap[1][1] = 9;
        weightedMap[1][2] = 9;
        weightedMap[1][4] = 9;
        weightedMap[3][4] = 9;
        weightedMap[3][3] = 9;
        weightedMap[3][2] = 9;
        weightedMap[3][0] = 9;
        weightedMap[4][0] = 9;
        weightedMap[4][1] = 3;
        weightedMap[3][1] = 3;
        weightedMap[4][2] = 9;
        weightedMap[5][5] = 9;
        weightedMap[6][5] = 9;
    }
}
