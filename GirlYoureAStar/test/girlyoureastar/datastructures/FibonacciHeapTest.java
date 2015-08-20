
package girlyoureastar.datastructures;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class FibonacciHeapTest {
    private FibonacciHeap heap;
    
    public FibonacciHeapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        heap = new FibonacciHeap();
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
     public void emptyHeapHasNullMinNode() {
         assertNull(heap.peek());
     }
     
     @Test
     public void emptyHeapSizeIs0() {
         assertEquals(0, heap.size());
     }
     
     @Test
     public void sizeIncreasesWhenNodeIsAddedToHeap() {
         Node n = new Node(0, 0, 0, 1);
         heap.insert(n);
         
         assertEquals(1, heap.size());
     }
     
     @Test
     public void minNodeIsTheSmallestNodeInTheHeapWhenTwoNodesAreInserted() {
         Node n1 = new Node(0, 0, 0, 1);
         n1.setCost(10);
         Node n2 = new Node(1, 0, 1, 1);
         n2.setCost(5);
         
         heap.insert(n1);
         heap.insert(n2);
         
         assertEquals(n2, heap.peek().getNode());
     }
}
