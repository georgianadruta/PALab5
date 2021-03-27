package compulsory;

import compulsory.catalog.Catalog;
import compulsory.catalog.CatalogUtil;
import compulsory.exceptions.MyInvalidDataException;
import compulsory.exceptions.MyPathException;
import compulsory.items.Book;
import compulsory.items.Image;
import compulsory.items.Type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * a helpful class to create model of the problem
 */
public class Demo {
    private static final Logger logger= LogManager.getLogger(Demo.class);
    /***
     * creates some kind of catalog with hardcoded values
     * @return demo catalog
     */
    public Catalog createDemoCatalog() throws IOException {
        logger.info("\nCreate...");
        Catalog catalog = null;
        try {
            catalog = new Catalog("something", "./");
            Image image1 = new Image("meme", Type.MEME, 7.1, "1", "./demoFiles/meme.jpg");
            Image image2 = new Image("anotherMeme", Type.MEME, 9, "2", "./demoFiles/anotherMeme.jpg");
            Book book1 = new Book("Cinderella story", "Brothers Grimm", 9, "3", "https://storiestogrowby.org/story/cinderella-fairy-tale-english-story-for-kids/");
            Book book2 = new Book("Snow White and the Seven Dwarfs", "Wilhelm Grimm and Jacob Grimm", 15, "4", "https://storiestogrowby.org/story/snow-white-and-the-seven-dwarfs-bedtime-stories-for-kids/");
            catalog.add(image1);
            catalog.add(image2);
            catalog.add(book1);
            catalog.add(book2);
            catalog.add(image2);
        } catch (MyInvalidDataException exception) {
            logger.error("Invalid data in createDemoCatalog method.");
        } catch (MyPathException exception) {
            logger.error("Invalid path in createDemoCatalog method..");
        }
        return catalog;
    }

    /**
     * a method to use all commands
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InterruptedException
     * @throws MyPathException
     * @throws MyInvalidDataException
     */
    public void run() throws IOException, InterruptedException, MyPathException, MyInvalidDataException {
        Catalog catalog = createDemoCatalog();
        CatalogUtil.save(catalog, "file.txt");
        catalog.list();
        CatalogUtil.load("file.txt");
        catalog.getById("1").play();
        TimeUnit.SECONDS.sleep(3);
        catalog.getById("2").play();
        TimeUnit.SECONDS.sleep(3);
        catalog.getById("3").play();
        TimeUnit.SECONDS.sleep(3);
        catalog.getById("4").play();
    }
}
