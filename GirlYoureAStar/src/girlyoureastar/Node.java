package girlyoureastar;

/**
 * Luokkaan tallennetaan verkon yksittäisen solmun id, x- ja y-koordinaatit,
 * 'hinta' (summa matkasta reitin loppuun ja alkuun), parent (mitä kautta reitti
 * päätyi kyseiseen solmuun, ja boolean passable, joka kertoo pystyykö solmun
 * kautta kulkemaan.
 *
 */
public class Node implements Comparable<Node> {

    private int nodeId;
    private int x;
    private int y;
    private int cost;
    private Node parent;
    private boolean passable;

    public Node(int nodeId, int x, int y) {
        this.nodeId = nodeId;
        this.x = x;
        this.y = y;
        this.cost = -1;
        this.passable = true;
    }

 
    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    @Override
    public int compareTo(Node t) {
        return this.cost - t.getCost();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
