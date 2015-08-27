package girlyoureastar.datastructures;

/**
 * Luokka tarjoaa metodit Nodejen tallentamiseen Fibonacci-minimikekoon ja
 * cost-arvoltaan pienimmän Noden löytämiseen.
 *
 */
public class FibonacciHeap implements MinHeap {

    private FiboNode minNode;
    private int size;

    public FibonacciHeap() {
        minNode = null;
        size = 0;
    }

    /**
     *
     * @return cost-arvoltaan pienin Node
     */
    @Override
    public Node peek() {
        if (minNode == null) {
            return null;
        }
        return minNode.getNode();
    }

    public FiboNode getFirst() {
        return minNode;
    }

    /**
     * Metodi lisää kekoon annetun alkion ja päivittää minNode-arvon jos annettu
     * Node on cost-arvoltaan pienempi kuin muut keossa olevat Nodet.
     *
     * @param node lisättävä Node
     */
    @Override
    public void insert(Node node) {
        FiboNode fnode = new FiboNode(node);

        node.setFiboNode(fnode);
        fnode.setNext(fnode);
        fnode.setPrev(fnode);

        minNode = mergeLists(minNode, fnode);

        size++;
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

        FiboNode node1Next = node1.getNext();
        FiboNode node2Next = node2.getNext();

        node1.setNext(node2Next);
        node2Next.setPrev(node1);

        node2.setNext(node1Next);
        node1Next.setPrev(node2);

        if (node1.compareTo(node2) < 0) {
            return node1;
        }
        return node2;
    }

    @Override
    public boolean isEmpty() {
        return minNode == null;
    }

    /**
     * Poistaa keosta pienimmän alkion. Järjestää samalla keon niin, ettei siitä
     * löydy kahta puuta, jolla olisi yhtä monta lasta. Asettaa minNodeen keon
     * pienimmän alkion poistetun tilalle.
     *
     * @return keon cost-arvoltaan pienin Node
     */
    @Override
    public Node delMin() {
        FiboNode min = minNode;

        if (minNode == null) {
            return null;
        }

        if (min.getNext() == min) {
            minNode = null;
        } else {
            minNode = min.getNext();
        }

        if (min.getChild() != null) {
            removeParentFromChildren(min);
        }

        removeNodeFromList(min);
        size--;

        minNode = mergeLists(minNode, min.getChild());

        if (minNode != null) {
            consolidate();
        }

        return min.getNode();
    }

    private void removeParentFromChildren(FiboNode parent) {
        FiboNode current = parent.getChild();
        do {
            current.setParent(null);
            current = current.getNext();
        } while (current != parent.getChild());
    }

    private void removeNodeFromList(FiboNode node) {
        FiboNode next = node.getNext();
        FiboNode prev = node.getPrev();

        next.setPrev(prev);
        prev.setNext(next);
    }

    private void consolidate() {
        FiboNode[] nodesByDegree = new FiboNode[45];

        FiboNode start = minNode;
        FiboNode current = minNode;

        do {
            FiboNode next = current.getNext();

            int degree = current.getDegree();
            FiboNode min = current;
            FiboNode max = null;

            while (nodesByDegree[degree] != null) {

                FiboNode sameDegreeNode = nodesByDegree[degree];
                if (current.compareTo(sameDegreeNode) > 0) {
                    max = current;
                    min = sameDegreeNode;
                } else {
                    max = sameDegreeNode;
                }

                if (max == start) {
                    start = start.getNext();
                }

                if (max == next) {
                    next = next.getNext();
                }

                setNodeAsChild(min, max);
                nodesByDegree[degree] = null;
                degree++;

            }

            nodesByDegree[degree] = min;
            current = next;

        } while (current != start);

        for (FiboNode node : nodesByDegree) {
            if (node != null && node.compareTo(minNode) < 0) {
                minNode = node;
            }
        }

    }

    private void setNodeAsChild(FiboNode parent, FiboNode child) {
        FiboNode childPrev = child.getPrev();
        FiboNode childNext = child.getNext();

        childPrev.setNext(childNext);
        childNext.setPrev(childPrev);

        child.setParent(parent);

        if (parent.getChild() == null) {
            parent.setChild(child);
            child.setNext(child);
            child.setPrev(child);
        } else {
            FiboNode originalChild = parent.getChild();

            child.setPrev(originalChild);
            child.setNext(originalChild.getNext());
            originalChild.getNext().setPrev(child);
            originalChild.setNext(child);

        }

        parent.increaseDegree();
        child.setMarked(false);
    }

    /**
     * Metodi palauttaa keon koon, eli sen kuinka monta alkiota keossa on
     *
     * @return keon koko
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Muuttaa annetun Noden cost-arvon annetuksi jos se on pienempi kuin
     * alkuperäinen ja siirtää Noden oikealle paikalleen keossa.
     *
     * @param node Node, jonka arvoa muutetaan
     * @param newValue uusi arvo
     */
    @Override
    public void decreaseKey(Node node, int newValue) {
        if (newValue > node.getCost()) {
            return;
        }

        node.setCost(newValue);

        FiboNode fnode = node.getFiboNode();
        FiboNode parent = fnode.getParent();

        if (parent != null && fnode.compareTo(parent) < 0) {
            cut(fnode, parent);
            cascadingCut(parent);
        } else if (fnode.compareTo(minNode) < 0) {
            minNode = fnode;
        }

    }

    private void cut(FiboNode node, FiboNode parent) {
        removeNodeFromList(node);
        parent.decreaseDegree();
        mergeLists(minNode, node);
        node.setMarked(false);
    }

    private void cascadingCut(FiboNode node) {
        FiboNode parent = node.getParent();
        if (parent != null) {
            if (node.isMarked()) {
                cut(node, parent);
                cascadingCut(parent);
            } else {
                node.setMarked(true);
            }
        }
    }

}
