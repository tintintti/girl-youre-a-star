package girlyoureastar.datastructures;

/**
 * Luokka tarjoaa metodit Nodejen tallentamiseen pinoon.
 *
 */
public class Stack {

    Node[] table;
    int top;

    public Stack(int length) {
        top = -1;
        table = new Node[length];
    }

    /**
     * Lisää pinoon annetun Noden
     * @param n lisättävä Node
     */
    public void push(Node n) {
        top++;
        table[top] = n;
    }

    /**
     * Poistaa pinosta viimeksi lisätyn Noden
     * @return  poistettu Node
     */
    public Node pop() {
        if (top > -1) {
            Node n = table[top];
            top--;
            return n;
        }

        return null;
    }

    /**
     * @return boolean onko pino tyhjä
     */
    public boolean isEmpty() {
        return top == -1;
    }
    
    /**
     * 
     * @return Nodejen määrä pinossa
     */
    public int size() {
        return top + 1;
    }

}
