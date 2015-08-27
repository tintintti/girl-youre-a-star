
package girlyoureastar.datastructures;

/**
 * Luokkaan tallennetaan LinkedList-luokan yksittäiset solmut.
 * 
 */
public class LinkedListNode {
    Node key;
    LinkedListNode next;

    public LinkedListNode(Node key) {
        this.key = key;
        next = null;
    }
    
    /**
     * @return solmuun tallennettu Node
     */
    public Node getKey() {
        return key;
    }

    /**
     * @param key solmuun tallennettava Node
     */
    public void setKey(Node key) {
        this.key = key;
    }

    /**
     * @return listassa seuraavana oleva solmu
     */
    public LinkedListNode getNext() {
        return next;
    }

    /**
     * @param next listassa seuraavana oleva solmu
     */
    public void setNext(LinkedListNode next) {
        this.next = next;
    }
    
    
    
    
}
