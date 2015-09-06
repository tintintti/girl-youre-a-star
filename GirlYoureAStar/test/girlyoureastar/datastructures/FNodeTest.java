/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package girlyoureastar.datastructures;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tintti
 */
public class FNodeTest {

    private FNode node;

    public FNodeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        node = new FNode(new Node(0, 1, 1, 1));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testKeyAndNodeCostMatch() {
        assertEquals(node.getNode().getCost(), node.getKey());
    }

    @Test
    public void testKeyAndNodeCostMatchAfterChangingKey() {

        node.setKey(2);

        assertEquals(node.getNode().getCost(), node.getKey());
    }

    @Test
    public void testLinkingToAnotherNode() {
        FNode parent = new FNode(new Node(1, 0, 1, 1));

        node.link(parent);

        assertEquals(parent, node.getParent());
    }

    @Test
    public void testCut() {
        FNode parent = new FNode(new Node(1, 0, 1, 1));
        node.link(parent);

        parent.cut(node, parent);

        assertNull(node.getParent());
        assertEquals(parent, node.getRight());
    }

    @Test
    public void testCascadinCutWhenNotMarked() {
        FNode n1 = new FNode(new Node(1, 0, 1, 1));
        FNode n2 = new FNode(new Node(2, 0, 2, 1));

        node.link(n1);
        n2.link(node);

        node.cascadingCut(n1);

        assertTrue(node.isMarked());
    }

    @Test
    public void testCascadingCutWhenMarked() {
        FNode n1 = new FNode(new Node(1, 0, 1, 1));
        FNode n2 = new FNode(new Node(2, 0, 2, 1));

        node.link(n1);
        n2.link(node);

        node.setMarked(true);

        node.cascadingCut(n1);

        assertEquals(n1, node.getRight());
        assertNull(node.getParent());
        assertFalse(node.isMarked());
    }

    @Test
    public void testCutWithMultipleChildren() {
        FNode n1 = new FNode(new Node(1, 0, 1, 1));
        FNode n2 = new FNode(new Node(2, 0, 2, 1));
        
        node.link(n1);
        n2.link(n1);
        
        n1.cut(node, n1);
        
        assertEquals(n2, n1.getChild());
        
    }

}
