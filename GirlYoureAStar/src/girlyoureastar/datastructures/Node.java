package girlyoureastar.datastructures;

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
    private int costOfMovement;
    private Node parent;
    private boolean passable;
    private int heapIndex;
    private FNode fiboNode;

    public Node(int nodeId, int x, int y, int costOfMovement) {
        this.nodeId = nodeId;
        this.x = x;
        this.y = y;
        this.costOfMovement = costOfMovement;
        this.cost = -1;
        this.passable = true;
    }

    /**
     *
     * @return kuinka paljon solmuun siirtyminen verkossa maksaa, eli solmuun
     * kulkevien kaarien kaaripaino
     */
    public int getCostOfMovement() {
        return costOfMovement;
    }

    /**
     *
     * @param costOfMovement kuinka paljon solmuun siirtyminen verkossa maksaa,
     * eli solmuun kulkevien kaarien kaaripaino
     */
    public void setCostOfMovement(int costOfMovement) {
        this.costOfMovement = costOfMovement;
    }

    /**
     * 
     * @return Noden yksilöllinen id-numero
     */
    public int getNodeId() {
        return nodeId;
    }

    /**
     * 
     * @param nodeId Noden yksilöllinen id-numero
     */
    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    /**
     *
     * @return rivin numero, jolla Node kartalla on
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @param x rivin numero, jolla Node kartalla on
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return sarakkeen numero, jolla Node kartalla on
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @param y sarakkeen numero, jolla Node kartalla on
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @return Noden parent, eli mistä nodesta reitillä on päädytty
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Metodi asettaa nodeen tiedon siitä, mistä siihen on päädytty reitillä
     *
     * @param parent Node, josta reitillä on tämänhetkiseen nodeen päädytty
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     *
     * @return reitinhaussa käytettävä arvo, joka kertoo miten suuri todellinen
     * tai arvioitu kustannus nodeen siirtymisellä reitillä on
     */
    public int getCost() {
        return cost;
    }

    /**
     * Metodi asettaa nodelle arvon, joka kertoo reitin kokonaiskustannuksen jos
     * nodeen siirrytään ja jonka mukaan nodet järjestetään tarvittaessa
     *
     * @param cost noden arvo
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     *
     * @return onko nodeen mahdollista siirtyä
     */
    public boolean isPassable() {
        return passable;
    }

    /**
     * Metodi asettaa Nodelle arvon true, jos siihen on mahdollista siirtyä ja
     * false jos ei.
     *
     * @param passable boolean-arvo, joka kertoo onko nodeen mahdollista siirtyä
     * reittiä etsiessä
     */
    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    /**
     * Metodi vertaa nodeja cost-attribuutin perusteella.
     *
     * @param t Node johon verrataan
     * @return verrattavien nodejen cost-arvojen erotus
     */
    @Override
    public int compareTo(Node t) {
        return this.cost - t.getCost();
    }

    /**
     * 
     * @return Noden indeksi binäärikeossa
     */
    public int getHeapIndex() {
        return heapIndex;
    }

    /**
     * 
     * @param heapIndex Noden indeksi binäärikeossa
     */
    public void setHeapIndex(int heapIndex) {
        this.heapIndex = heapIndex;
    }

    /**
     *
     * @return FNode, johon Nodessa on viite
     */
    public FNode getFNode() {
        return fiboNode;
    }

    /**
     * Asettaa viitteen FNodeen, johon Node on lisätty
     *
     * @param fiboNode FNode johon Node on lisätty
     */
    public void setFNode(FNode fiboNode) {
        this.fiboNode = fiboNode;
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
        return nodeId + " (" + x + ", " + y + ") cost: " + cost;
    }

}
