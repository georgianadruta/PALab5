package compulsory.items;

import compulsory.exceptions.MyInvalidDataException;
import compulsory.exceptions.MyPathException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.IOException;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@ToString
public abstract class Item implements Serializable {
    private String id;
    private String name;

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

    /**
     * an abstract method to playback using the native operating system application
     * @throws IOException
     * @throws MyPathException
     * @throws MyInvalidDataException
     */
    public abstract void play() throws IOException, MyPathException, MyInvalidDataException;
}
