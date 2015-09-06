
package girlyoureastar.algorithms;

import girlyoureastar.datastructures.Node;
import girlyoureastar.datastructures.Stack;

/**
 *
 * Rajapinta lyhimmän reitin hakualgoritmia varten.
 */
public interface RouteFinder {
    /**
     * Etsii verkosta lyhimmän reitin alku- ja loppupisteen välillä.
     * 
     * @param start  reitin aloituspiste
     * @param finish reitin lopetuspiste
     * @return       reitillä olevat solmut pinossa
     */
    public Stack findRoute(Node start, Node finish); 
}
