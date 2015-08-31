
package girlyoureastar.datastructures;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class StackTest {
    private Stack stack;
    
    public StackTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        stack = new Stack(10);
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void newStackIsEmpty() {
         assertTrue(stack.isEmpty());
     }
     
     @Test
     public void pushAddsAnElementToStack() {
         Node n = new Node(0, 0, 0, 1);
         stack.push(n);
         
         assertEquals(1, stack.size());
     }
     
     @Test
     public void popReturnsTheLatestElementAdded() {
         Node n1 = new Node(0, 0, 0, 1);
         Node n2 = new Node(1, 0, 1, 1);
         
         stack.push(n1);
         stack.push(n2);
         
         assertEquals(n2, stack.pop());
     }
     
     @Test
     public void returnsNullIfStackIsEmpty() {
         assertNull(stack.pop());
     }
     
}
