package optional.items;

import compulsory.exceptions.MyInvalidDataException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@ToString
public class Item implements Serializable {
    private final String id;
    private final String name;

    /**
     * constructor
     * @param name name of item
     * @param id a given string to identify the item in catalog
     */
    public Item(String name, String id) throws MyInvalidDataException {
        if (name != null && id != null) {
            this.name = name;
            this.id = id;
            System.out.println("File " + this.name + " created successfully.");
        } else {
            throw new MyInvalidDataException("Name and id of the item could not be null.");
        }
    }
}
