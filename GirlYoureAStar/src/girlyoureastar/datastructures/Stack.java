package girlyoureastar.datastructures;

/**
 *
 *
 */
public class Stack {

    Node[] table;
    int top;

    public Stack(int length) {
        top = -1;
        table = new Node[length];
    }

    public void push(Node n) {
        top++;
        table[top] = n;
    }

    public Node pop() {
        if (top > -1) {
            Node n = table[top];
            top--;
            return n;
        }

        return null;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

}
