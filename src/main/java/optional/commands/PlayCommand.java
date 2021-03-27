package optional.commands;

import compulsory.exceptions.MyInvalidDataException;
import compulsory.exceptions.MyPathException;
import optional.items.Book;
import optional.items.Catalog;
import optional.items.Image;
import optional.items.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * PlayCommand class implements play command (playback using the native operating system application)
 */
public class PlayCommand implements GenericCommands {

    private Catalog catalog;
    private static final Logger logger = LogManager.getLogger(PlayCommand.class);

    /**
     * @param catalog catalog where the item specified on the command line is located
     * @throws MyInvalidDataException
     */
    public PlayCommand(Catalog catalog) throws MyInvalidDataException {
        this.catalog = catalog;
    }

    /**
     * checks if the command is valid (is of the form: play fileName),
     * then search the item and display it on the screen if the item exist
     *
     * @param command
     */
    @Override
    public void run(String command) {
        try {
            String[] words = command.split(" ");
            if (words.length == 2) {
                String nameFile = words[1];
                if (!searchAndPlayItem(nameFile)) {
                    logger.info("File " + nameFile + " is not found.");
                }
            } else {
                logger.error("Invalid command. Command should be of the form: play fileName");
            }
        } catch (MyInvalidDataException exception) {
            logger.error("MyInvalidDataException in play method");
        } catch (MyPathException exception) {
            logger.error("MyPathException in play method");
        }
    }

    /**
     * searchs the item from the command line, then plays it
     *
     * @param nameFile name of the item
     * @return true if found it in the list of items, else false
     * @throws MyPathException
     * @throws MyInvalidDataException
     */
    private boolean searchAndPlayItem(String nameFile) throws MyPathException, MyInvalidDataException {
        for (Item item : this.catalog.getItemList()) {
            if (item.getName().equals(nameFile)) {
                if (item instanceof Book) {
                    play(new Book(item.getName(), ((Book) item).getAuthors(), item.getId(), ((Book) item).getUri()));
                } else {
                    play(new Image(item.getName(), ((Image) item).getDescription(), item.getId(), ((Image) item).getPath()));
                }
                return true;
            }
        }
        return false;
    }

    /**
     * playback using the native operating system application
     *
     * @throws MyInvalidDataException
     */
    public void play(Book book) throws MyInvalidDataException {
        try {
            openUri(book.getUri());
        } catch (IOException exception) {
            throw new MyInvalidDataException("Path could not be null in play method.");
        }
    }

    /**
     * playback using the native operating system application
     *
     * @throws MyInvalidDataException
     */
    public void play(Image image) throws MyInvalidDataException {
        try {
            openLocalFile(image.getPath());
        } catch (IOException exception) {
            throw new MyInvalidDataException("Path could not be null.");
        }
    }

    /**
     * helpful method to open a book
     *
     * @param uri book's path
     * @throws IOException
     */
    public static void openUri(String uri) throws IOException {
        logger.info("\nOpen...");
        try {
            Desktop.getDesktop().browse(URI.create(uri));
        } catch (IOException exception) {
            throw new IOException("IOException in openUri method.");
        }
    }

    /**
     * helpful method to open an image
     *
     * @param path image's path
     * @throws IOException
     */
    public static void openLocalFile(String path) throws IOException {
        logger.info("\nOpen...");
        try {
            Desktop.getDesktop().open(new File(path));
        } catch (IOException exception) {
            throw new IOException("IOException in openLocalFile method.");
        }
    }
}


