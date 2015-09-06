package girlyoureastar.datastructures;

/**
 *
 * Luokkaan tallennetaan yksittäisen Fibonacci-keon alkion tiedot. Luokka
 * tarjoaa metodit Fibonacci-keon solmun siirtämiseen pois sen parent-solmun
 * alta ja keon root-listaan.
 *
 */
public class FNode {

    private final Node node;
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

    /**
     * Metodi tarvittaessa merkitsee FNoden lapsen menettäneeksi tai leikkaa sen
     * pois parent-solmustaan root-listaan.
     *
     * @param min root-listan solmu
     */
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

    /**
     * Metodi poistaa annetun solmun tämän solmun lapsien listalta ja siirtää
     * sen Fibonacci-keon root-listalle.
     *
     * @param childToRemove poistettava child-solmu
     * @param min root-listan solmu
     */
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

    /**
     *
     * @return Node, joka solmuun on tallennettu
     */
    public Node getNode() {
        return node;
    }

    /**
     *
     * @return solmun avain, sama kuin solmuun talletetun Noden cost
     */
    public int getKey() {
        return key;
    }

    /**
     *
     * @param key solmun avain, sama kuin solmuun talletetun Noden cost
     */
    public void setKey(int key) {
        this.key = key;
        node.setCost(key);
    }

    /**
     *
     * @return solmun parent-solmu
     */
    public FNode getParent() {
        return parent;
    }

    /**
     *
     * @param parent solmulle asetettava parent-solmu
     */
    public void setParent(FNode parent) {
        this.parent = parent;
    }

    /**
     *
     * @return solmun ensimmäinen lapsi
     */
    public FNode getChild() {
        return child;
    }

    /**
     *
     * @param child solmun ensimmäinen lapsi
     */
    public void setChild(FNode child) {
        this.child = child;
    }

    /**
     * @return solmusta seuraava solmu
     */
    public FNode getRight() {
        return right;
    }

    /**
     *
     * @param right solmusta seuraava solmu
     */
    public void setRight(FNode right) {
        this.right = right;
    }

    /**
     *
     * @return solmua edeltävä solmu
     */
    public FNode getLeft() {
        return left;
    }

    /**
     *
     * @param left solmua edeltävä solmu
     */
    public void setLeft(FNode left) {
        this.left = left;
    }

    /**
     *
     * @return solmun lapsien määrä
     */
    public int getDegree() {
        return degree;
    }

    /**
     *
     * @return onko solmulta poistettu lapsia viimeisen cut-operaation jälkeen
     */
    public boolean isMarked() {
        return marked;
    }

    /**
     *
     * @param marked onko solmulta poistettu lapsia viimeisen cut-operaation
     * jälkeen
     */
    public void setMarked(boolean marked) {
        this.marked = marked;
    }

}
