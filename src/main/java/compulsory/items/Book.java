package compulsory.items;

import compulsory.catalog.CatalogUtil;
import compulsory.exceptions.MyInvalidDataException;
import compulsory.exceptions.MyPathException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.IOException;
import java.io.Serializable;

/**
 * book class which contains the name of the author, number of pages of the book and the uri where it is located
 */
@Getter
@ToString
@EqualsAndHashCode
public class Book extends Item implements Serializable {
    private String authors;
    private int numberPages;
    private String uri;

    /**
     * constructor
     *
     * @param name        name of the book
     * @param authors     author/authors of the book (one String)
     * @param numberPages number of pages
     * @param id          a given string to identify the book in catalog
     * @param uri
     * @throws MyInvalidDataException
     * @throws MyPathException
     */
    public Book(String name, String authors, int numberPages, String id, String uri) throws MyInvalidDataException, MyPathException {
        super(name, id);
        this.authors = authors;
        if (numberPages > 0) {
            this.numberPages = numberPages;
        } else {
            throw new MyInvalidDataException("Number of pages must be a positive integer.");
        }
        if (uri != null) {
            this.uri = uri;
        } else {
            throw new MyPathException("Path of the book could not be null.");
        }
    }

    /**
     * playback using the native operating system application
     *
     * @throws MyInvalidDataException
     */
    public void play() throws MyInvalidDataException {
        try {
            CatalogUtil.openUri(this.uri);
        } catch (IOException e) {
            throw new MyInvalidDataException("Path could not be null in play method.");
        }
    }
}
