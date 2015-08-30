package girlyoureastar.datastructures;

/**
 * Rajapinta minimikeon totetutukseen.
 *
 */
public interface MinHeap {

    /**
     * Lisää annetun Noden kekoon.
     *
     * @param node lisättävä Node
     */
    public void insert(Node node);

    /**
     * Palauttaa keon pienimmän Noden poistamatta sitä
     *
     * @return pienin alkio
     */
    public Node peek();

    /**
     * @return true, jos keko on tyhjä, false jos ei
     */
    public boolean isEmpty();

    /**
     * Poistaa keon pienimmän Noden
     *
     * @return keon pienin alkio ennen poistoa
     */
    public Node delMin();

    /**
     * @return keon alkioiden määrä
     */
    public int size();

    /**
     * Pienentää annetun alkion arvoa.
     *
     * @param node Node jonka arvoa pienennetään
     * @param newKeyValue uusi arvo
     */
    public void decreaseKey(Node node, int newKeyValue);

}
