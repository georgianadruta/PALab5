package optional.items;

import compulsory.exceptions.MyInvalidDataException;
import compulsory.exceptions.MyPathException;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Book class which contains the title, the name of the author and the uri where it is located
 */
@Getter
public class Book extends Item {

    private static final Logger logger = LogManager.getLogger(Book.class);
    private final String authors;
    private final String uri;

    /**
     * constructor
     *
     * @param name        name of the book
     * @param authors     author/authors of the book (one String)
     * @param id          a given string to identify the book in catalog
     * @param uri         uri identifier
     * @throws MyInvalidDataException
     * @throws MyPathException
     */
    public Book(String name, String authors, String id, String uri) throws MyInvalidDataException, MyPathException {
        super(name, id);
        this.authors = authors;
        if (uri != null) {
            this.uri = uri;
        } else {
            throw new MyPathException("Path of the book could not be null.");
        }
    }
}
