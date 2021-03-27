package compulsory.catalog;

import compulsory.exceptions.MyInvalidDataException;
import compulsory.items.Item;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Catalog class which contains a name of catalog of multimedia items and its path and
 */
@Getter
@ToString
@EqualsAndHashCode
public class Catalog implements Serializable {
    private String name;
    private String path;
    private Set<Item> items = new HashSet<>(); // I want distinct items in catalog

    // constructor
    public Catalog(String name, String path) throws MyInvalidDataException {
        if (path != null && name != null) {
            this.path = path;
            this.name = name;
        } else {
            throw new MyInvalidDataException("Path of book could not be null in catalog constructor.");
        }
    }

    public Catalog(String name, String path, HashSet<Item> items) throws MyInvalidDataException {
        this.items = items;
        if (path != null && name != null) {
            this.path = path;
            this.name = name;
        } else {
            throw new MyInvalidDataException("Path of book could not be null in catalog constructor.");
        }
    }

    /**
     * adds a new item into the catalog
     *
     * @param item new entry
     */
    public void add(Item item) {
        items.add(item);
    }

    /**
     * adds a new list of items into the catalog
     *
     * @param item new entry
     */
    public void add(HashSet<Item> item) {
        items.addAll(item);
    }

    /**
     * prints the content of the catalog on the screen
     */
    public void list() {
        System.out.println("\nContent of the catalog:");
        for (Item item : items) {
            System.out.println(item.getId() + " " + item.getName());
        }
    }

    /**
     * find element in a given catalog by id
     *
     * @param index searching for the item with this index
     * @return the item
     */
    public Item getById(String index) {
        return this.getItems().stream()
                .filter(d -> d.getId().equals(index)).findFirst().orElse(null);
    }
}
