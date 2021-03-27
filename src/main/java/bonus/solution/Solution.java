package bonus.solution;

import bonus.problem.Problem;
import lombok.Getter;
import optional.items.Catalog;
import optional.items.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution class create an algorithm that generates the playlists for each day.
 */
@Getter
public class Solution extends Problem {
    private static final Logger logger = LogManager.getLogger(Solution.class);
    private final List<ArrayList<Integer>> connectedComp = new ArrayList<>(numberItems);
    private int numberConnectedComp = 1;

    /**
     * constructor
     *
     * @param catalog a given catalog
     */
    public Solution(Catalog catalog) {
        super(catalog);
    }

    /**
     *
     */
    public void create() {
        if (this.numberItems > 0) {
            boolean[] visited = new boolean[numberItems];
            ArrayList<Integer> path = new ArrayList<>();
            dfs(0, visited, path);
            connectedComp.add(path);
            for (int i = 0; i < numberItems; i++) {
                if (!visited[i]) {
                    path = new ArrayList<>();
                    this.numberConnectedComp++;
                    dfs(i, visited, path);
                    connectedComp.add(path);
                }
            }
        }
    }

    /**
     * display the solution
     */
    public void display() {
        if (this.numberConnectedComp == 1) {
            displayAnItemPerDay();
        } else {
            displayPlaylists();
        }
    }

    /**
     * if we a have one connected component in graph, we'll display one item/day
     */
    private void displayAnItemPerDay() {
        logger.info("Playlists for " + numberItems + " days");
        int i = 1;
        for (Item item : catalog.getItemList()) {
            logger.info("Day " + i + ": " + item.getName());
            i++;
        }
    }

    /**
     * if we have more connected components, we'll display one item from each playlist(connected component)/day
     */
    private void displayPlaylists() {
        int itemDisplayed = numberItems;
        int k = 1;
        while (itemDisplayed > 0) {
            logger.info("Day " + k + ": ");
            k++;
            for (int i = 0; i < connectedComp.size(); i++) {
                if (connectedComp.get(i).size() > 0) {
                    logger.info(catalog.getItemList().get(connectedComp.get(i).get(0)));
                    itemDisplayed--;
                    connectedComp.get(i).remove(0);
                }
            }
        }
    }

    /**
     * dfs recursive algorithm
     *
     * @param vertex  start vertex
     * @param visited visited array
     * @param path    the list in which we will save the new connected component
     */
    private void dfs(int vertex, boolean[] visited, ArrayList<Integer> path) {
        if (!visited[vertex]) {
            visited[vertex] = true;
            path.add(vertex);
            for (int neighbour = 0; neighbour < adjacencyMatrix[vertex].length; neighbour++) {
                if (adjacencyMatrix[vertex][neighbour] == 1 && !visited[neighbour]) {
                    dfs(neighbour, visited, path);
                }
            }
        }
    }
}
