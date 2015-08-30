package girlyoureastar.datastructures;

/**
 * Luokka tarjoaa metodit Nodejen tallentamiseen Fibonacci-minimikekoon ja
 * cost-arvoltaan pienimmän Noden löytämiseen.
 *
 */
public class FibonacciHeap implements MinHeap {

    private FNode minNode;
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

    /**
     * Metodi lisää kekoon annetun alkion ja päivittää minNode-arvon jos annettu
     * Node on cost-arvoltaan pienempi kuin muut keossa olevat Nodet.
     *
     * @param value lisättävä Node
     */
    @Override
    public void insert(Node value) {
        FNode node = new FNode(value);
        // Nodeen viite siihen viittaavaan FNodeen
        value.setFNode(node);
        //FNoden next ja prev osoittamaan itseensä
        node.setRight(node);
        node.setLeft(node);

        // lisää solmun minNoden jälkeen root-listaan
        if (minNode != null) {
            node.setRight(minNode);
            node.setLeft(minNode.getLeft());
            minNode.setLeft(node);
            node.getLeft().setRight(node);

            // asettaa lisätyn solmun pienimmäksi jos se on pienempi kuin 
            // aikaisempi minimi
            if (node.getKey() < minNode.getKey()) {
                minNode = node;
            }
        } else {
            minNode = node;
        }

        size++;
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
        FNode z = minNode;

        if (z == null) {
            return null;
        }

        if (z.getChild() != null) {
            removeParentFromChildren(z);

            // lisää lapset root-listaan
            FNode minNodeLeft = minNode.getLeft();
            FNode minChildLeft = z.getChild().getLeft();
            minNode.setLeft(minChildLeft);
            minChildLeft.setRight(minNode);
            z.getChild().setLeft(minNodeLeft);
            minNodeLeft.setRight(z.getChild());

        }

        removeNodeFromList(z);
        

        if (z.getRight() == z) {
            minNode = null;
        } else {
            minNode = z.getRight();
            consolidate();
        }

        size--;

        return z.getNode();
    }

    private void removeParentFromChildren(FNode parent) {
        FNode current = parent.getChild();
        do {
            current.setParent(null);
            current = current.getRight();
        } while (current != parent.getChild());
    }

    private void removeNodeFromList(FNode node) {
        FNode next = node.getRight();
        FNode prev = node.getLeft();

        next.setLeft(prev);
        prev.setRight(next);
    }

    private void consolidate() {
        FNode[] nodesByDegree = new FNode[45];

        FNode start = minNode;
        FNode current = minNode;

        do {
            FNode min = current;
            FNode next = current.getRight();

            int degree = min.getDegree();

            while (nodesByDegree[degree] != null) {

                FNode max = nodesByDegree[degree];

                if (min.getKey() > max.getKey()) {
                    FNode temp = max;
                    max = min;
                    min = temp;
                }

                if (max == start) {
                    start = start.getRight();
                }

                if (max == next) {
                    next = next.getRight();
                }

                max.link(min);
                
                nodesByDegree[degree] = null;
                degree++;

            }

            nodesByDegree[degree] = min;
            current = next;

        } while (current != start);

        minNode = start;
        
        for (FNode node : nodesByDegree) {
            if (node != null && node.getKey() < minNode.getKey()) {
                minNode = node;
            }
        }

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
     * @param key Node, jonka arvoa muutetaan
     * @param newValue uusi arvo
     */
    @Override
    public void decreaseKey(Node key, int newValue) {
        if (newValue > key.getCost()) {
            return;
        }

        FNode node = key.getFNode();
        node.setKey(newValue);

        FNode parent = node.getParent();

        if (parent != null && newValue < parent.getKey()) {
            parent.cut(node, minNode);
            parent.cascadingCut(minNode);
        } else if (newValue < minNode.getKey()) {
            minNode = node;
        }

    }

}
