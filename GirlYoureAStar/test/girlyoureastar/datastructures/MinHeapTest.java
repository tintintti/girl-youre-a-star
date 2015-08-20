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
public class MinHeapTest {

    private MinHeap heap;

    public MinHeapTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        heap = new MinHeap(6);
        Node n1 = new Node(0, 0, 0, 1);
        Node n2 = new Node(1, 1, 0, 1);
        n1.setCost(5);
        n2.setCost(2);
        heap.heapInsert(n1);
        heap.heapInsert(n2);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void newHeapIsEmpty() {
        heap = new MinHeap(6);
        assertTrue(heap.isEmpty());
    }

    @Test
    public void constructorSetsHeapLengthRight() {
        assertEquals(7, heap.getHeap().length);
    }

    @Test
    public void heapSizeGrowsWhenNodeIsAdded() {
        heap.heapInsert(new Node(2, 0, 1, 1));
        assertEquals(3, heap.size());
    }

    @Test
    public void nodeHasCorrectHeapIndexWhenAdded() {
        assertEquals(1, heap.heapMin().getHeapIndex());
    }

    @Test
    public void heapDelMinReturnsTheMinNode() {
        assertEquals(2, heap.heapDelMin().getCost());
    }

    @Test
    public void nodeIsInTheRightPlaceAfterDecKey() {
        heap.heapDecKey(heap.getHeap()[2], 1);
        assertEquals(1, heap.heapMin().getCost());
    }

    @Test
    public void nodeHasRightHeapIndexAfteDecKey() {
        Node n = heap.getHeap()[2];
        heap.heapDecKey(n, 1);

        assertEquals(1, n.getHeapIndex());
    }

    @Test
    public void heapSizeDecreasesAfterDelMin() {
        heap.heapDelMin();
        assertEquals(1, heap.size());
    }

}
