package optional.commands;

import compulsory.exceptions.MyInvalidDataException;
import compulsory.exceptions.MyPathException;
import optional.items.Item;
import optional.items.Book;
import optional.items.Image;
import optional.items.Catalog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * AddCommand class implements add command (adds a new entry into the catalog)
 */
public class AddCommand implements GenericCommands {
    private static final Logger logger = LogManager.getLogger(AddCommand.class);
    private Catalog catalog;

    /**
     * @param catalog the class on which the addition operation is performed
     * @throws MyInvalidDataException
     */
    public AddCommand(Catalog catalog) throws MyInvalidDataException {
        this.catalog = catalog;
    }

    /**
     * receive a string and separates the words from the string
     * the string is the form: "add image fileName description id path"
     * or "add book fileName authors id uri"
     * if the second word is "book", the id(=words[4]) is unique and the uri(=words[5]) is valid call the book constructor
     * ..similar for image
     *
     * @param command a string from command line
     * @throws MyPathException
     * @throws MyInvalidDataException
     */
    @Override
    public void run(String command) {
        try {
            String[] words = command.split(" ");
            if (words.length == 6) { // have all arguments for constructors
                String item = words[1];
                if (item.equals("book") && !foundId(words[4]) && isValidUri(words[5])) {
                    addBook(words);
                } else if (item.equals("image") && !foundId(words[4]) && isValidPath(words[5])) {
                    addImage(words);
                }
            } else {
                logger.error("Invalid command. Command should be of the form: add image/video fileName description/authors id path/uri ");
            }
        } catch (Exception exception) {
            logger.error("Exception in run method.");
        }
    }

    /**
     * checks if a book URI is valid
     *
     * @param uri the uri from the command line
     * @return true if the URI is valid, else false and displays an error message
     */
    private boolean isValidUri(String uri) {
        try {
            new URL(uri).toURI();
        } catch (MalformedURLException | URISyntaxException exception) {
            logger.error("Invalid Uri");
            return false;
        }
        return true;
    }

    /**
     * checks if an image path is valid
     *
     * @param path the path from the command line
     * @return true if the path is valid, else false and displays an error message
     */
    private boolean isValidPath(String path) {
        File file = new File(path);
        String mimetype = new MimetypesFileTypeMap().getContentType(file);
        String type = mimetype.split("/")[0];
        if (file.exists() && type.equals("image")) {
            return true;
        }
        logger.error("Invalid path to image");
        return false;
    }

    /**
     * create a new image object then add it into catalog
     *
     * @param words an word array, the words from the command line
     */
    private void addImage(String[] words) {
        try {
            Image image = new Image(words[2], words[3], words[4], words[5]);
            this.add(image);
        } catch (MyPathException exception) {
            logger.error("MyPathException in addImage method");
        } catch (MyInvalidDataException exception) {
            logger.error("MyInvalidDataException in addImage method");
        }
    }

    /**
     * create a new book object then add it into catalog
     *
     * @param words an word array, the words from the command line
     */
    private void addBook(String[] words) {
        try {
            Book book = new Book(words[2], words[3], words[4], words[5]);
            this.add(book);
        } catch (MyPathException exception) {
            logger.error("MyPathException in addBook method");
        } catch (MyInvalidDataException exception) {
            logger.error("MyInvalidDataException in addBook method");
        }
    }

    /**
     * checks if the new id is already in item list
     *
     * @param id id of the new item we want to insert
     * @return true if the id is unique, else false
     */
    private boolean foundId(String id) {
        for (Item item : this.catalog.getItemList()) {
            if (id.equals(item.getId())) {
                logger.error("Id is not unique.");
                return true;
            }
        }
        return false;
    }

    /**
     * adds a new item into the catalog
     *
     * @param item new entry
     */
    public void add(Item item) {
        this.catalog.getItemList().add(item);
    }

}
