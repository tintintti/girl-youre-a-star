package girlyoureastar.datastructures;

/**
 * Luokkaan tallennetaan yksittäisen Fibonacci-keon alkion tiedot.
 *
 */
public class FiboNode implements Comparable<FiboNode> {

    private Node node;

    private FiboNode parent;
    private FiboNode child;
    private FiboNode prev;
    private FiboNode next;

    private int degree;
    private boolean marked;

    public FiboNode(Node node) {
        this.node = node;
        degree = 0;
        marked = false;
    }

    
    public void link(FiboNode parent) {
        prev.next = next;
        next.prev = prev;
        
        this.parent = parent;
        if (parent.child == null) {
            parent.child = this;
            next = this;
            prev = this;
        } else {
            prev = parent.child;
            next = parent.child.next;
            parent.child.next = this;
            next.prev = this;
        }
        
        parent.degree++;
        
        marked = false;
    }
    
    public void cascadingCut(FiboNode min) {
        FiboNode z = parent;
        
        if (z != null) {
            if (marked) {
                cut(this, min);
                cascadingCut(min);
            } else {
                marked = true;
            }
        }
    }
    
    public void cut(FiboNode x, FiboNode min) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        degree--;
        
        if (degree == 0) {
            child = null;
        } else if (child == x) {
            child = x.next;
        }
        
        x.next = min;
        x.prev = min.prev;
        min.prev = x;
        x.prev.next = x;
        
        x.parent = null;
        
        x.marked = false;
        
    }
    
    
    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public void increaseDegree() {
        degree++;
    }

    public void decreaseDegree() {
        degree--;
    }

    public FiboNode getParent() {
        return parent;
    }

    public void setParent(FiboNode parent) {
        this.parent = parent;
    }

    public FiboNode getChild() {
        return child;
    }

    public void setChild(FiboNode child) {
        this.child = child;
    }

    public FiboNode getPrev() {
        return prev;
    }

    public void setPrev(FiboNode prev) {
        this.prev = prev;
    }

    public FiboNode getNext() {
        return next;
    }

    public void setNext(FiboNode next) {
        this.next = next;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean isMarked) {
        this.marked = isMarked;
    }

    @Override
    public int compareTo(FiboNode t) {
        return node.compareTo(t.getNode());
    }

}
