package optional.items;

import compulsory.exceptions.MyInvalidDataException;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

@Getter
public class Catalog {
    protected static final Logger logger = LogManager.getLogger(compulsory.catalog.Catalog.class);
    protected String name;
    protected String path;
    protected List<Item> itemList = new LinkedList<>(); // I want distinct items in catalog

    // constructor
    public Catalog(String name, String path) throws MyInvalidDataException {
        if (path != null && name != null) {
            this.path = path;
            this.name = name;
        } else {
            throw new MyInvalidDataException("Path of book could not be null in catalog constructor.");
        }
    }

    public Catalog(String name, String path, LinkedList<Item> itemList) throws MyInvalidDataException {
        this.itemList = itemList;
        if (path != null && name != null) {
            this.path = path;
            this.name = name;
        } else {
            throw new MyInvalidDataException("Path of book could not be null in catalog constructor.");
        }
    }

    /**
     * find element in a given catalog by id
     *
     * @param index searching for the item with this index
     * @return the item
     */
    public Item getById(String index) {
        return this.getItemList().stream()
                .filter(d -> d.getId().equals(index)).findFirst().orElse(null);
    }

    /**
     * add a new entry of items
     */
    public void add(List<Item> item) {
        itemList.addAll(item);
    }

    /**
     * add a new item
     */
    public void add(Item item) {
        itemList.add(item);
    }
}
