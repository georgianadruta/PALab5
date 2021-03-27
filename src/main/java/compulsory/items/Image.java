package compulsory.items;

import compulsory.catalog.CatalogUtil;
import compulsory.exceptions.MyInvalidDataException;
import compulsory.exceptions.MyPathException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.IOException;

/**
 * image class which contains the type of the image and the rate and the path where it is located
 */
@Getter
@ToString
@EqualsAndHashCode
public class Image extends Item {
    private final Type type;
    private double rate;
    private String path;

    /**
     * constructor
     *
     * @param name name of the image
     * @param type      ARCHITECTURAL, ABSTRACT, COMMERCIAL, COMPOSITE, FASHION, MEME
     * @param rate      a positive number<=10
     * @param id        a given uniq string to identify the book in catalog
     * @param path      path of the image
     * @throws MyInvalidDataException
     * @throws MyPathException
     */
    public Image(String name, Type type, double rate, String id, String path) throws MyInvalidDataException, MyPathException {
        super(name, id);
        if (rate > 0) {
            this.rate = rate;
        } else {
            throw new MyInvalidDataException("Rate must be a positive number.");
        }
        this.type = type;
        if (path != null) {
            this.path = path;
        } else {
            throw new MyPathException("Path of book could not be null.");
        }
    }

    /**
     * playback using the native operating system application
     *
     * @throws MyInvalidDataException
     */
    public void play() throws MyInvalidDataException {
        try {
            CatalogUtil.openLocalFile(this.path);
        } catch (IOException exception) {
            throw new MyInvalidDataException("Path could not be null.");
        }
    }
}
