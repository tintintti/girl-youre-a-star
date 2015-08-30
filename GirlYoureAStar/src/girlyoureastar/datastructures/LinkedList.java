package girlyoureastar.datastructures;

/**
 * Luokka tarjiaa metodit Nodejen tallentamiseen linkitettyyn listaan
 *
 */
public class LinkedList {

    private LinkedListNode head;

    public LinkedList() {
        head = null;
    }

    /**
     *
     * @return tieto onko lista tyhjä
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     *
     * @return listan ensimmäinen Node
     */
    public LinkedListNode getHead() { // if you know what I mean :33
        return head;
    }

    /**
     * Lisää annetun Noden listaan.
     *
     * @param key lisättävä Node
     */
    public void add(Node key) {
        LinkedListNode node = new LinkedListNode(key);

        node.next = head;
        head = node;
    }

}
