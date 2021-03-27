package bonus.problem;

import optional.items.Book;
import optional.items.Catalog;
import optional.items.Image;
import optional.items.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Problem class creates a graph G in which the catalog items represent the nodes,
 * two nodes being connected if they share some common feature (for example, two images that have the same name, etc).
 * Suppose that we want to play all the items in the catalog in as few days as possible,
 * so that in one day we will not play two items that are connected in G.
 */
public class Problem {
    private static final Logger logger = LogManager.getLogger(Problem.class);
    protected final Catalog catalog;
    protected int numberItems;
    protected int[][] adjacencyMatrix;

    /**
     * constructor
     *
     * @param catalog a given catalog which contains a list of items
     */
    public Problem(Catalog catalog) {
        this.catalog = catalog;
        this.numberItems = catalog.getItemList().size();
        this.adjacencyMatrix = new int[this.numberItems][this.numberItems];
        createMatrix();
    }

    /**
     * create adjacency matrix
     * two items are adjacent if they have something in common (name, path..)
     */
    private void createMatrix() {
        for (int i = 0; i < this.numberItems; i++) {
            for (int j = 0; j < this.numberItems; j++) {
                if (i != j) {
                    Item item1 = catalog.getItemList().get(i);
                    Item item2 = catalog.getItemList().get(j);
                    if (item1 instanceof Book && item2 instanceof Book) {
                        this.adjacencyMatrix[i][j] = compareTwoBooks(item1, item2);
                    } else {
                        if ((item1 instanceof Image && item2 instanceof Book) || (item2 instanceof Image && item1 instanceof Book)) {
                            if (item1.getName().equals(item2.getName())) {
                                this.adjacencyMatrix[i][j] = 1;
                            }
                        } else {
                            if (item1 instanceof Image && item2 instanceof Image) {
                                this.adjacencyMatrix[i][j] = compareTwoImages(item1, item2);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * compare two book objects
     * if they have the same name, the same authors or the same uri
     *
     * @param item1 first book
     * @param item2 second book
     * @return 1 if they have something in common, else 0
     */
    private int compareTwoBooks(Item item1, Item item2) {
        if (item1.getName().equals(item2.getName())) {
            return 1;
        } else {
            if (((Book) item1).getAuthors().equals(((Book) item2).getAuthors())) {
                return 1;
            } else {
                if (((Book) item1).getUri().equals(((Book) item2).getUri())) {
                    return 1;
                }
            }
        }
        return 0;
    }

    /**
     * compare two image objects
     * if they have the same name, the same description or the same path
     *
     * @param item1 first image
     * @param item2 second image
     * @return 1 if they have something in common, else 0
     */
    private int compareTwoImages(Item item1, Item item2) {
        if (item1.getName().equals(item2.getName())) {
            return 1;
        } else {
            if (((Image) item1).getDescription().equals(((Image) item2).getDescription())) {
                return 1;
            } else {
                if (((Image) item1).getPath().equals(((Image) item2).getPath())) {
                    return 1;
                }
            }
        }
        return 0;
    }

    /**
     * displays the adjacency matrix
     */
    public void displayMatrix() {
        logger.info(catalog.getItemList());
        for (int i = 0; i < numberItems; i++) {
            for (int j = 0; j < numberItems; j++) {
                logger.info(adjacencyMatrix[i][j] + " ");
            }
           logger.info("\n");
        }
    }


}
