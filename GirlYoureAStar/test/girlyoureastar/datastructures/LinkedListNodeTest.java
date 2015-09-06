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
 * 
 */
public class LinkedListNodeTest {
    private LinkedListNode node;
    private Node key;
    
    public LinkedListNodeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        key = new Node(0, 0, 0, 1);
        node = new LinkedListNode(key);
    }
    
    @After
    public void tearDown() {
    }


     @Test
     public void testGetKey() {
         assertEquals(key, node.getKey());
     }
     
     @Test
     public void testSetKey() {
         Node key2 = new Node(1, 0, 1, 1);
         node.setKey(key2);
         
         assertEquals(key2, node.getKey());
     }
     
     @Test
     public void testSetNext() {
         LinkedListNode next = new LinkedListNode(new Node(1, 0, 1, 1));
         node.setNext(next);
         
         assertEquals(next, node.getNext());
     }
}
