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
public class LinkedListTest {
    private LinkedList list;
    
    public LinkedListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        list = new LinkedList();
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void newListIsEmpty() {
         assertTrue(list.isEmpty());
     }
     
     @Test
     public void addingANodeWorks() {
         Node n = new Node(0, 0, 0, 1); 
         list.add(n);
         assertEquals(list.getHead().key, n);
     }
     
     public void addingTwoNodesWorks() {
         Node n1 = new Node(0, 0, 0, 1);
         Node n2 = new Node(1, 0, 1, 1);
         
         list.add(n1);
         list.add(n2);
         
         assertEquals(list.getHead().key, n2);
         assertEquals(list.getHead().getNext().key, n1);
     }
}
