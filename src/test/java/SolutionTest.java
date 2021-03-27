import bonus.solution.Solution;
import compulsory.exceptions.MyInvalidDataException;
import compulsory.exceptions.MyPathException;
import optional.items.Book;
import optional.items.Catalog;
import optional.items.Image;
import optional.items.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class SolutionTest {
    private static final Logger logger = LogManager.getLogger(ResolverUtil.Test.class);
    private Catalog catalog;


    @SafeVarargs
    private final List<List<Integer>> getSolution(List<Integer>... indexItem) {
        return new ArrayList<>(Arrays.asList(indexItem));
    }

    // TEST 1

    /**
     * constructor
     * empty catalog
     */
    private void noItems() {
        try {
            this.catalog = new Catalog("something", "./");
        } catch (MyInvalidDataException exception) {
            logger.info("MyInvalidDataException in test");
        }
    }

    /**
     * catalog is empty
     */
    @Test
    public void testForNoItems() {
        noItems();
        Solution solution = new Solution(this.catalog);
        solution.create();
        assertEquals(solution.getConnectedComp(), getSolution());
    }

    // TEST 2

    /**
     * constructor
     * catalog contains a single item
     */
    private void onlyItem() {
        try {
            Image image1 = new Image("meme", "funny", "1", "./demoFiles/meme.jpg");
            this.catalog = new Catalog("something", "./");
            this.catalog.add(image1);
        } catch (MyPathException exception) {
            logger.info("MyPathException in test");
        } catch (MyInvalidDataException exception) {
            logger.info("MyInvalidDataException in test");
        }
    }

    /**
     * catalog contains a single item
     */
    @Test
    public void testForOneItem() {
        onlyItem();
        Solution solution = new Solution(this.catalog);
        solution.create();
        assertEquals(solution.getConnectedComp(), getSolution(Collections.singletonList(0)));
    }

    // TEST 3

    /**
     * constructor
     * non-adjacency items
     */
    private void dataWithNothingInCommon() {
        try {
            Image image1 = new Image("meme", "funny", "1", "./demoFiles/meme.jpg");
            Image image2 = new Image("anotherMeme", "sad", "2", "./demoFiles/anotherMeme.jpg");
            Image image3 = new Image("abc", "ross", "3", "./demoFiles/true.jpg");
            Book book1 = new Book("Cinderella story", "Brothers Grimm", "4", "https://storiestogrowby.org/story/cinderella-fairy-tale-english-story-for-kids/");
            Book book2 = new Book("Story", "Wilhelm Grimm and Jacob Grimm", "5", "https://storiestogrowby.org/story/snow-white-and-the-seven-dwarfs-bedtime-stories-for-kids/");
            Book book3 = new Book("Stories of a Surreal Nature", "Graeme", "6", "https://www.obooko.com/free-short-story-collections/stories-of-a-surreal-nature");
            Book book4 = new Book("Another story", "Kelly Link", "7", "https://www.obooko.com/free-short-story-collections/stranger-things-happen-keyy-link");
            LinkedList<Item> list = new LinkedList<>(Arrays.asList(image1, image2, image3, book1, book2, book3, book4));
            this.catalog = new Catalog("something", "./", list);
        } catch (MyPathException exception) {
            logger.info("MyPathException in test");
        } catch (MyInvalidDataException exception) {
            logger.info("MyInvalidDataException in test");
        }
    }

    /**
     * the catalog don't have adjacency items(with something in common)
     */
    @Test
    public void testForIsolatedItems() {
        dataWithNothingInCommon();
        Solution solution = new Solution(this.catalog);
        solution.create();
        assertEquals(solution.getConnectedComp(), getSolution(Collections.singletonList(0), Collections.singletonList(1), Collections.singletonList(2), Collections.singletonList(3), Collections.singletonList(4), Collections.singletonList(5), Collections.singletonList(6)));
    }

    // TEST 4

    /**
     * constructor for some data have something in common case
     */
    private void someDataHaveSomethingInCommon() {
        try {
            Image image1 = new Image("meme", "funny", "1", "./demoFiles/meme.jpg");
            Image image2 = new Image("anotherMeme", "sad", "2", "./demoFiles/anotherMeme.jpg");
            Image image3 = new Image("meme", "ross", "3", "./demoFiles/true.jpg");
            Book book1 = new Book("first book", "Brothers Grimm", "4", "https://storiestogrowby.org/story/cinderella-fairy-tale-english-story-for-kids/");
            Book book2 = new Book("book", "Wilhelm Grimm and Jacob Grimm", "5", "https://storiestogrowby.org/story/snow-white-and-the-seven-dwarfs-bedtime-stories-for-kids/");
            Book book3 = new Book("book", "Graeme", "6", "https://www.obooko.com/free-short-story-collections/stories-of-a-surreal-nature");
            Book book4 = new Book("book", "Kelly Link", "7", "https://www.obooko.com/free-short-story-collections/stranger-things-happen-keyy-link");
            LinkedList<Item> list = new LinkedList<>(Arrays.asList(image1, image2, image3, book1, book2, book3, book4));
            this.catalog = new Catalog("something", "./", list);
        } catch (MyPathException exception) {
            logger.info("MyPathException in test");
        } catch (MyInvalidDataException exception) {
            logger.info("MyInvalidDataException in test");
        }
    }

    /**
     * catalog contains some adjacency items => have some days with more then one item/day
     */
    @Test
    public void testForSomeItemsWithSomethingInCommon() {
        someDataHaveSomethingInCommon();
        Solution solution = new Solution(this.catalog);
        solution.create();
        assertEquals(solution.getConnectedComp(), getSolution(Arrays.asList(0, 2), Collections.singletonList(1), Collections.singletonList(3), Arrays.asList(4, 5, 6)));
    }

    // TEST 5

    /**
     * constructor
     * items from catalog have in common the name
     */
    private void allDataHaveSomethingInCommon() {
        try {
            Image image1 = new Image("meme", "funny", "1", "./demoFiles/meme.jpg");
            Image image2 = new Image("meme", "sad", "2", "./demoFiles/anotherMeme.jpg");
            Image image3 = new Image("meme", "ross", "3", "./demoFiles/true.jpg");
            Book book1 = new Book("meme", "Brothers Grimm", "4", "https://storiestogrowby.org/story/cinderella-fairy-tale-english-story-for-kids/");
            Book book2 = new Book("meme", "Wilhelm Grimm and Jacob Grimm", "5", "https://storiestogrowby.org/story/snow-white-and-the-seven-dwarfs-bedtime-stories-for-kids/");
            Book book3 = new Book("meme", "Graeme", "6", "https://www.obooko.com/free-short-story-collections/stories-of-a-surreal-nature");
            Book book4 = new Book("meme", "Kelly Link", "7", "https://www.obooko.com/free-short-story-collections/stranger-things-happen-keyy-link");
            LinkedList<Item> list = new LinkedList<>(Arrays.asList(image1, image2, image3, book1, book2, book3, book4));
            this.catalog = new Catalog("something", "./", list);
        } catch (MyPathException exception) {
            logger.info("MyPathException in test");
        } catch (MyInvalidDataException exception) {
            logger.info("MyInvalidDataException in test");
        }
    }

    /**
     * catalog contains only adjacency items => one item/day
     */
    @Test
    public void testForAllItemsWithSomethingInCommon() {
        allDataHaveSomethingInCommon();
        Solution solution = new Solution(this.catalog);
        solution.create();
        assertEquals(solution.getConnectedComp(), getSolution(Arrays.asList(0, 1, 2, 3, 4, 5, 6)));
    }
}