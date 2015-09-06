package girlyoureastar.datastructures;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FibonacciHeapTest {

    private FibonacciHeap heap;
    private Node n1;
    private Node n2;
    private Node n3;
    private Node n4;
    private Node n5;
    private Node n6;
    private Node n7;
    private Node n8;
    private Node n9;
    private Node n10;

    public FibonacciHeapTest() {
    }

    @Before
    public void setUp() {
        heap = new FibonacciHeap();

        n1 = new Node(0, 0, 0, 1);
        n2 = new Node(1, 0, 1, 1);
        n3 = new Node(2, 0, 2, 1);
        n4 = new Node(3, 0, 3, 1);
        n5 = new Node(4, 0, 4, 1);
        n6 = new Node(5, 0, 5, 1);
        n7 = new Node(6, 0, 6, 1);
        n8 = new Node(7, 0, 7, 1);
        n9 = new Node(8, 0, 8, 1);
        n10 = new Node(9, 0, 9, 1);

        n1.setCost(10);
        n2.setCost(22);
        n3.setCost(67);
        n4.setCost(23);
        n5.setCost(63);
        n6.setCost(15);
        n7.setCost(24);
        n8.setCost(8);
        n9.setCost(27);
        n10.setCost(20);

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

        assertEquals(n2, heap.peek());
    }

    @Test
    public void delMinGivesAllNodesInCorrectOrderWhenCalledNumerously() {

        heap.insert(n1);
        heap.insert(n2);
        heap.insert(n3);
        heap.insert(n4);
        heap.insert(n5);
        heap.insert(n6);
        heap.insert(n7);
        heap.insert(n8);
        heap.insert(n9);
        heap.insert(n10);

        checkAllNodesAreDeletedInCorrectOrder();

    }

    private void checkAllNodesAreDeletedInCorrectOrder() {
        Node min = heap.delMin();
        while (!heap.isEmpty()) {
            Node next = heap.delMin();
            assertTrue(min.getCost() <= next.getCost());
            min = next;
        }
    }

    @Test
    public void delMinReturnsMinNodeAfterDecreaseKey() {
        heap.insert(n1);
        heap.insert(n2);

        heap.decreaseKey(n2, 1);

        assertEquals(n2, heap.delMin());
    }

    @Test
    public void delMinReturnsAllNodesInOrderAfterDecKey() {
        heap.insert(n1);
        heap.insert(n2);
        heap.insert(n3);
        heap.insert(n4);

        heap.decreaseKey(n2, 1);

        checkAllNodesAreDeletedInCorrectOrder();
    }

    @Test
    public void testDelMinAfterMultipleDecKeys() {
        heap.insert(n1);
        heap.insert(n2);
        heap.insert(n3);
        heap.insert(n4);
        heap.insert(n5);
        heap.insert(n6);
        heap.insert(n7);
        heap.insert(n8);
        heap.insert(n9);
        heap.insert(n10);

        heap.decreaseKey(n10, 10);
        heap.decreaseKey(n3, 4);
        heap.decreaseKey(n8, 1);
        heap.decreaseKey(n6, 3);
        heap.decreaseKey(n4, 5);

        checkAllNodesAreDeletedInCorrectOrder();
    }

    @Test
    public void testDecKey() {
        heap.insert(n1);
        
        heap.decreaseKey(n1, 5);
        
        assertEquals(5, n1.getCost());
    }
    
    @Test
    public void decKeyDoesntIncreaseKey() {
        heap.insert(n1);
        heap.decreaseKey(n1, 50);
        
        assertEquals(10, n1.getCost());
    }
    
    @Test
    public void decKeyAddsNodeToHeapIfItIsntThere() {
        heap.insert(n1);
        heap.insert(n2);
        heap.delMin();
        
        heap.decreaseKey(n1, 5);
        
        assertEquals(heap.delMin(), n1);
    }
    
}
