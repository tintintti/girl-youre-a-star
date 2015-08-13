package girlyoureastar.datastructures;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeTest {

    Node node;

    public NodeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        node = new Node(0, 0, 0, 1);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void newNodesParentIsNull() {
        assertEquals(null, node.getParent());
    }

    @Test
    public void newNodeIsPassable() {
        assertTrue(node.isPassable());
    }

    @Test
    public void constructorSetsTheXCoordinateRight() {
        assertEquals(0, node.getX());
    }

    @Test
    public void constructorSetsTheYCoordinateRight() {
        assertEquals(0, node.getY());
    }

    @Test
    public void constructorSetsTheNodeIdRight() {
        assertEquals(0, node.getNodeId());
    }

    @Test
    public void constructorSetsTheCostRight() {
        assertEquals(-1, node.getCost());
    }
}
