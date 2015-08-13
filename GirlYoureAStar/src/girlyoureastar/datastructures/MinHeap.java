package girlyoureastar.datastructures;

/**
 * Luokka tarjoaa metodit Nodejen tallentamiseen minimikekoon ja cost-arvoltaan
 * pienimmän Noden löytämiseen
 *
 */
public class MinHeap {

    private final Node[] heap;
    private int heapSize;

    public MinHeap(int length) {
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
        Node node = heap[i];
        int left = left(i);
        int right = right(i);
        int smallest = left;

        if (right <= heapSize) {
            if (heap[right].compareTo(heap[left]) > 0) {
                smallest = left;
            }
            if (heap[smallest].compareTo(node) < 0) {

                heap[i] = heap[smallest];
                heap[i].setHeapIndex(i);
                heap[smallest] = node;
                node.setHeapIndex(smallest);

                heapify(smallest);
            }
        } else if (left == heapSize && heap[left].compareTo(node) < 0) {

            heap[i] = heap[left];
            heap[i].setHeapIndex(i);
            heap[left] = node;
            node.setHeapIndex(left);
        }
    }

    /**
     *
     * @return keon ensimmäinen ja pienin alkio
     */
    public Node heapMin() {
        return heap[1];
    }

    /**
     * Metodi poistaa keon ensimmäisen eli pienimmän alkion.
     *
     * @return keon ensimmäinen alkio, eli node, jonka cost-arvo on keon pienin
     */
    public Node heapDelMin() {
        Node min = heap[1];
        heap[1] = heap[heapSize];
        heap[1].setHeapIndex(1);
        heapSize -= 1;
        heapify(1);

        return min;
    }

    /**
     * Metodi lisää annetun Noden kekoon ja siirtää sen oikealle paikalleen.
     *
     * @param node kekoon lisättävä Node
     */
    public void heapInsert(Node node) {
        heapSize += 1;
        int i = heapSize;

        while (i > 1 && heap[parent(i)].compareTo(node) > 0) {
            heap[i] = heap[(parent(i))];
            heap[i].setHeapIndex(i);
            i = parent(i);
        }
        heap[i] = node;
        node.setHeapIndex(i);
    }

    /**
     * Metodi asettaa halutulle Nodelle uuden cost-arvon ja siirtää sen oikealle
     * paikalleen keossa
     *
     * @param node Node, jonka arvoa halutaan muuttaa
     * @param newKeyValue arvo, joksi Noden cost muutetaan
     */
    public void heapDecKey(Node node, int newKeyValue) {
        node.setCost(newKeyValue);

        int i = node.getHeapIndex();

        while (i > 1 && node.compareTo(heap[parent(i)]) < 0) {
            heap[i] = heap[parent(i)];
            heap[i].setHeapIndex(i);
            heap[parent(i)] = node;
            node.setHeapIndex(parent(i));
        }

    }

    /**
     *
     * @return true jos keko on tyhjä, false jos ei
     */
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
    public int getHeapSize() {
        return heapSize;
    }

}
