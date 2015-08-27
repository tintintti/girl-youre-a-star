package girlyoureastar.datastructures;

/**
 * Luokkaan tallennetaan kaikki verkon solmut ja kaaret. Muuttaa
 * kaksiuloitteisena talukkona annetun kartan Nodeista muodostuvaksi verkoksi.
 *
 */
public class Graph {

    int[][] map;
    Node[][] nodes;
    LinkedList[] edges;

    public Graph(int[][] map) {
        this.map = map;
        this.nodes = new Node[map.length][map[0].length];
        this.edges = new LinkedList[(this.map.length * this.map[0].length)];
        this.mapToNodes();
    }

    private void mapToNodes() {

        createNodes();

        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[0].length; j++) {
                Node n = nodes[i][j];
                edges[n.getNodeId()] = new LinkedList();

                if (map[i][j] == 9) {
                    nodes[i][j].setPassable(false);
                    continue;
                }

                if (i - 1 >= 0 && map[i - 1][j] != 9) {
                    Node neighbor = nodes[i - 1][j];
                    edges[n.getNodeId()].add(neighbor);
                }

                if (i + 1 < map.length && map[i + 1][j] != 9) {
                    Node neighbor = nodes[i + 1][j];
                    edges[n.getNodeId()].add(neighbor);
                }

                if (j - 1 >= 0 && map[i][j - 1] != 9) {
                    Node neighbor = nodes[i][j - 1];
                    edges[n.getNodeId()].add(neighbor);
                }

                if (j + 1 < map[0].length && map[i][j + 1] != 9) {
                    Node neighbor = nodes[i][j + 1];
                    edges[n.getNodeId()].add(neighbor);
                }

            }

        }

    }

    private void createNodes() {
        int nodeId = 0;
        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[0].length; j++) {
                Node toAdd = new Node(nodeId, i, j, this.map[i][j] + 1);
                nodes[i][j] = toAdd;
                nodeId++;
            }
        }
    }

    /**
     *
     * @return Nodejen määrä verkossa
     */
    public int length() {
        return this.edges.length;
    }

    /**
     *
     * @return verkon vieruslistaesitys
     */
    public LinkedList[] getEdges() {
        return edges;
    }

    /**
     *
     * @return Nodet kaksiulotteisessa taulukossa
     */
    public Node[][] getNodes() {
        return nodes;
    }

    /**
     *
     * @return kartan numeroesitys kaksiulotteisessa taulukossa
     */
    public int[][] getMap() {
        return map;
    }

    /**
     * Metodi asettaa Graphille uuden kartan ja muuttaa annetun kartan "ruudut"
     * Nodeiksi sekä verkon viruslistaesityksen
     *
     * @param map uusi kartta numeroina kaksiulotteisessa taulukossa
     */
    public void setMap(int[][] map) {
        this.map = map;
        this.mapToNodes();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (Node[] node : nodes) {
            for (Node n : node) {
                if (n.isPassable()) {
                    s.append(n.getCostOfMovement());
                } else {
                    s.append('#');
                }
            }
            s.append("\n");
        }

        return s.toString();
    }

}
