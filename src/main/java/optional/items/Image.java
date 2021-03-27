package optional.items;

import compulsory.exceptions.MyInvalidDataException;
import compulsory.exceptions.MyPathException;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

/**
 * Image class which contains the name of the image, the description and the path where it is located
 */
@Getter
public class Image extends Item {

    private static final Logger logger = LogManager.getLogger(Image.class);
    private final String path;
    private final String description;

    /**
     * constructor
     *
     * @param name name of the image
     * @param id   a given uniq string to identify the book in catalog
     * @param path path of the image
     * @throws MyInvalidDataException
     * @throws MyPathException
     */
    public Image(String name, String description, String id, String path) throws MyInvalidDataException, MyPathException {
        super(name, id);
        this.description = description;
        if (path != null) {
            this.path = path;
        } else {
            throw new MyPathException("Path of book could not be null.");
        }
    }
}
