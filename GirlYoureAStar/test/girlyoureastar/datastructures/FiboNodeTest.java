package girlyoureastar.datastructures;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FiboNodeTest {

    FiboNode node;

    public FiboNodeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        node = new FiboNode(new Node(0, 1, 1, 1));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void newNodeIsntMarked() {
        assertFalse(node.isMarked());
    }

}
