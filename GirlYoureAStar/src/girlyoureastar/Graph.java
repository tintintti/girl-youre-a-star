package girlyoureastar;

import java.util.ArrayList;

/**
 * Luokkaan tallennetaan kaikki verkon solmut ja kaaret. Muuttaa ascii-merkkeinä
 * annetun kartan Nodeista muodostuvaksi verkoksi.
 *
 */
public class Graph {

    char[][] map;
    Node[][] nodes;
    ArrayList<Node>[] edges;

    public Graph(char[][] map) {
        this.map = map;
        this.nodes = new Node[map.length][map[0].length];
        this.edges = (ArrayList<Node>[]) new ArrayList[(this.map.length * this.map[0].length)];
        this.mapToNodes();
    }

    private void mapToNodes() {

        int nodeId = 0;
        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[0].length; j++) {
                Node toAdd = new Node(nodeId, i, j);
                nodes[i][j] = toAdd;
                nodeId++;
            }
        }

        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[0].length; j++) {
                Node n = nodes[i][j];
                edges[n.getNodeId()] = new ArrayList<>();

                if (map[i][j] == '#') {
                    nodes[i][j].setPassable(false);
                    continue;
                }

                if (i - 1 >= 0 && map[i - 1][j] != '#') {
                    Node neighbor = nodes[i - 1][j];
                    edges[n.getNodeId()].add(neighbor);
                }

                if (i + 1 < map.length && map[i + 1][j] != '#') {
                    Node neighbor = nodes[i + 1][j];
                    edges[n.getNodeId()].add(neighbor);
                }

                if (j - 1 >= 0 && map[i][j - 1] != '#') {
                    Node neighbor = nodes[i][j - 1];
                    edges[n.getNodeId()].add(neighbor);
                }

                if (j + 1 < map[0].length && map[i][j + 1] != '#') {
                    Node neighbor = nodes[i][j + 1];
                    edges[n.getNodeId()].add(neighbor);
                }

            }

        }

    }

    public int length() {
        return this.edges.length;
    }

    public ArrayList<Node>[] getEdges() {
        return edges;
    }

    public Node[][] getNodes() {
        return nodes;
    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }
    
    

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (Node[] node : nodes) {
            for (Node n : node) {
                if (n.isPassable()) {
                    s.append(' ');
                } else {
                    s.append('#');
                }
            }
            s.append("\n");
        }

        return s.toString();
    }

}
