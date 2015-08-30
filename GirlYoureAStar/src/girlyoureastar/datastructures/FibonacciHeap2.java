package girlyoureastar.datastructures;

/**
 *
 * @author tintti
 */
public class FibonacciHeap2 implements MinHeap {

    private FNode min;
    private int size;

    public FibonacciHeap2() {
        min = null;
        size = 0;
    }

    public boolean isEmpty() {
        return min == null;
    }

    public void clear() {
        min = null;
        size = 0;
    }

    public void insert(Node node) {
        FNode fnode = new FNode(node);

        if (min != null) {
            fnode.setRight(min);
            fnode.setLeft(min.getLeft());
            min.setLeft(fnode);
            fnode.getLeft().setRight(fnode);
            if (fnode.getKey() < min.getKey()) {
                min = fnode;
            }
        } else {
            min = fnode;
        }

        size++;
    }

    public Node delMin() {
        FNode z = min;

        if (z == null) {
            return null;
        }

        if (z.getChild() != null) {
            removeParentFromChildren(z);

            FNode minLeft = min.getLeft();
            FNode zChildLeft = z.getChild().getLeft();
            min.setLeft(zChildLeft);
            zChildLeft.setRight(min);
            z.getChild().setLeft(minLeft);
            minLeft.setRight(z.getChild());
        }

        z.getLeft().setRight(z.getRight());
        z.getRight().setLeft(z.getLeft());

        if (z == z.getRight()) {
            min = null;
        } else {
            min = z.getRight();
            consolidate();
        }
        size--;
        return z.getNode();
    }

    private void consolidate() {
        FNode[] nodesByDegree = new FNode[45];

        FNode start = min;
        FNode w = min;

        do {

            FNode smaller =  w;
            FNode nextW = w.getRight();

            int d = smaller.getDegree();

            while (nodesByDegree[d] != null) {

                FNode bigger = nodesByDegree[d];

                if (smaller.getKey() > bigger.getKey()) {
                    FNode temp = bigger;
                    bigger = smaller;
                    smaller = temp;
                }

                if (bigger == start) {
                    start = start.getRight();
                }

                if (bigger == nextW) {
                    nextW = nextW.getRight();
                }

                bigger.link(smaller);

                nodesByDegree[d] = null;
                d++;
            }
            nodesByDegree[d] = smaller;
            w = nextW;
        } while (w != start);

    }

    private void removeParentFromChildren(FNode parent) {
        FNode current = parent.getChild();
        do {
            current.setParent(null);
            current = current.getRight();
        } while (current != parent.getChild());
    }

    public Node peek() {
        if (min == null) {
            return null;
        }

        return min.getNode();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void decreaseKey(Node key, int newKeyValue) {
        if (newKeyValue > key.getCost()) {
            return;
        }
        
        FNode node = key.getFNode();
        node.setKey(newKeyValue);
        
        FNode y = node.getParent();
        
        if (y != null && newKeyValue < y.getKey()) {
            y.cut(node, min);
            y.cascadingCut(min);
        }
        
        if (newKeyValue < min.getKey()) {
            min = node;
        }
    }

}
