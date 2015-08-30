
package girlyoureastar.datastructures;

/**
 *
 * @author tintti
 */
public class FNode {
    private Node node;
    private int key;
    
    private FNode parent;
    private FNode child;
    private FNode right;
    private FNode left;
    
    private int degree;
    private boolean marked;

    public FNode(Node node) {
        this.node = node;
        key = node.getCost();
        node.setFNode(this);
        right = this;
        left = this;
    }
    
    public void cascadingCut(FNode min) {
        FNode z = parent;
        
        if (z != null) {
            if (marked) {
                z.cut(this, min);
                z.cascadingCut(min);
            } else {
                marked = true;
            }
        }
    }
    
    public void cut(FNode childToRemove, FNode min) {
        
        childToRemove.left.right = childToRemove.right;
        childToRemove.right.left = childToRemove.left;
        degree--;
        
        if (degree == 0) {
            child = null;
        } else if (child == childToRemove) {
            child = childToRemove.right;
        }
        
        childToRemove.right = min;
        childToRemove.left = min.left;
        min.left = childToRemove;
        childToRemove.left.right = childToRemove;
        
        childToRemove.parent = null;
        
        childToRemove.marked = false;
        
    }
    
    /**
     * Metodi tekee tästä solmusta annetun solmun lapsen.
     * 
     * @param parent uusi parent-solmu
     */
    public void link(FNode parent) {
        left.right = right;
        right.left = left;
        
        this.parent = parent;
        if (parent.child == null) {
            parent.child = this;
            right = this;
            left = this;
        } else {
            left = parent.child;
            right = parent.child.right;
            parent.child.right = this;
            right.left = this;
        }
        
        parent.degree++;
        
        marked = false;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
        key = node.getCost();
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
        node.setCost(key);
    }

    public FNode getParent() {
        return parent;
    }

    public void setParent(FNode parent) {
        this.parent = parent;
    }

    public FNode getChild() {
        return child;
    }

    public void setChild(FNode child) {
        this.child = child;
    }

    public FNode getRight() {
        return right;
    }

    public void setRight(FNode right) {
        this.right = right;
    }

    public FNode getLeft() {
        return left;
    }

    public void setLeft(FNode left) {
        this.left = left;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
    
    
    
}
