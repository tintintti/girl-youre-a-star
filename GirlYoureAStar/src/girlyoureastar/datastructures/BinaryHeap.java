package girlyoureastar.datastructures;

/**
 * Luokka tarjoaa metodit Nodejen tallentamiseen minimikekoon ja cost-arvoltaan
 * pienimmän Noden löytämiseen
 *
 */
public class BinaryHeap implements MinHeap {

    private final Node[] heap;
    private int heapSize;

    public BinaryHeap(int length) {
        this.heap = new Node[length + 1];
        this.heapSize = 0;
    }

    private int parent(int i) {
        return i / 2;
    }

    private int left(int i) {
        return 2 * i;
    }

    private int right(int i) {
        return 2 * i + 1;
    }

    private void heapify(int i) {
        int left = left(i);
        int right = right(i);
        int smallest = right;

        if (right <= heapSize) {
            if (heap[left].compareTo(heap[right]) < 0) {
                smallest = left;
            }
            if (heap[smallest].compareTo(heap[i]) < 0) {
                switchNodes(i, smallest);
                heapify(smallest);
            }
        } else if (left == heapSize && heap[left].compareTo(heap[i]) < 0) {
            switchNodes(i, left);
        }
    }

    private void switchNodes(int index1, int index2) {
        Node temp = heap[index1];

        heap[index1] = heap[index2];
        heap[index2] = temp;

        heap[index1].setHeapIndex(index1);
        heap[index2].setHeapIndex(index2);
    }

    private void moveNodeToCorrectPlace(int i, Node node) {
        while (i > 1 && heap[parent(i)].compareTo(node) > 0) {
            int parent = parent(i);
            heap[i] = heap[(parent)];
            heap[i].setHeapIndex(i);
            i = parent;
        }
        heap[i] = node;
        node.setHeapIndex(i);
    }

    /**
     *
     * @return keon ensimmäinen ja pienin alkio
     */
    @Override
    public Node peek() {
        return heap[1];
    }

    /**
     * Metodi poistaa keon ensimmäisen eli pienimmän alkion.
     *
     * @return keon ensimmäinen alkio, eli node, jonka cost-arvo on keon pienin
     */
    @Override
    public Node delMin() {
        if (heapSize == 0) {
            return null;
        }

        Node min = heap[1];
        heap[1] = heap[heapSize];
        heap[1].setHeapIndex(1);
        heapSize--;
        heapify(1);

        return min;
    }

    /**
     * Metodi lisää annetun Noden kekoon ja siirtää sen oikealle paikalleen.
     *
     * @param node kekoon lisättävä Node
     */
    @Override
    public void insert(Node node) {
        heapSize++;
        int i = heapSize;
        heap[i] = node;

        moveNodeToCorrectPlace(i, node);
    }

    /**
     * Metodi asettaa halutulle Nodelle uuden cost-arvon mikäli se on
     * alkuperäistä pienempi ja siirtää sen oikealle paikalleen keossa
     *
     * @param node Node, jonka arvoa halutaan muuttaa
     * @param newKeyValue arvo, joksi Noden cost muutetaan
     */
    @Override
    public void decreaseKey(Node node, int newKeyValue) {

        if (node.getCost() < newKeyValue) {
            return;
        }

        node.setCost(newKeyValue);

        int i = node.getHeapIndex();

        moveNodeToCorrectPlace(i, node);

    }

    /**
     *
     * @return true jos keko on tyhjä, false jos ei
     */
    @Override
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     *
     * @return taulukko, johon Nodet on tallennettu
     */
    public Node[] getHeap() {
        return heap;
    }

    /**
     *
     * @return keon koko, eli missä indeksissä taulukon viimeinen kekoon kuuluva
     * alkio on
     */
    @Override
    public int size() {
        return heapSize;
    }

}
