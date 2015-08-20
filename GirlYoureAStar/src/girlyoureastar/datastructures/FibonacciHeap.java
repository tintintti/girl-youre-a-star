package girlyoureastar.datastructures;

import java.util.ArrayList;

/**
 * Luokka tarjoaa metodit Nodejen tallentamiseen Fibonacci-minimikekoon ja
 * cost-arvoltaan pienimmän Noden löytämiseen.
 *
 */
public class FibonacciHeap {

    private FiboNode minNode;
    private int size;

    public FibonacciHeap() {
        minNode = null;
        size = 0;
    }

    /**
     * Tyhjentään keon.
     */
    public void clear() {
        minNode = null;
        size = 0;
    }

    /**
     *
     * @return cost-arvoltaan pienin Node
     */
    public FiboNode peek() {
        return minNode;
    }

    private FiboNode mergeLists(FiboNode node1, FiboNode node2) {
        if (node1 == null && node2 == null) {
            return null;
        }
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }

        FiboNode fnode = node1.getNext();
        node1.setNext(node2.getNext());
        node1.getNext().setPrev(node1);

        node2.setNext(fnode);
        node2.getNext().setPrev(node2);

        if (node1.getNode().compareTo(node2.getNode()) < 0) {
            return node1;
        }
        return node2;
    }

    /**
     * Metodi lisää kekoon annetun alkion ja päivittää minNode-arvon jos annettu
     * Node on cost-arvoltaan pienempi kuin muut keossa olevat Nodet.
     *
     * @param node lisättävä Node
     */
    public void insert(Node node) {
        FiboNode fnode = new FiboNode(node);
        fnode.setNext(fnode);
        fnode.setPrev(fnode);
        minNode = mergeLists(minNode, fnode);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node delMin() {
        if (isEmpty()) {
            return null;
        }

        FiboNode min = minNode;

        if (min.getNext() == min) {
            minNode = null;
        } else {
            minNode.getNext().setPrev(min.getPrev());
            minNode.getPrev().setNext(minNode.getNext());
            minNode = minNode.getNext();
        }

        size--;

        if (minNode.getChild() != null) {

            FiboNode current = minNode.getChild();
            do {
                current.setParent(null);
                current = current.getNext();
            } while (current != min.getChild());
        }

        minNode = mergeLists(minNode, min.getChild());

        if (minNode != null) {

            // Kesken, jatka tästä
        }

        return min.getNode();
    }

    /**
     * Metodi palauttaa keon koon, eli sen kuinka monta alkiota keossa on
     *
     * @return keon koko
     */
    public int size() {
        return size;
    }

}
