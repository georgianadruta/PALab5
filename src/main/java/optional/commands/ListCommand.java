package optional.commands;

import compulsory.exceptions.MyInvalidDataException;
import optional.items.Catalog;
import optional.items.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ListCommand class implements list command (prints the content of the catalog on the screen)
 */
public class ListCommand implements GenericCommands {
    private Catalog catalog;
    private static final Logger logger = LogManager.getLogger(ListCommand.class);

    /**
     * @param catalog that will be displayed
     * @throws MyInvalidDataException
     */
    public ListCommand(Catalog catalog) throws MyInvalidDataException {
        this.catalog = catalog;
    }

    /**
     * if the command from command line is valid (is of the form: list) then prints the content of the catalog on the screen
     *
     * @param command
     */
    @Override
    public void run(String command) {
        String[] words = command.split(" ");
        if (words.length == 1) {
            if (this.catalog.getItemList().isEmpty()) {
                logger.info("\nThe catalog is empty.");
            } else {
                logger.info("\nContent of the catalog:");
                for (Item item : this.catalog.getItemList()) {
                    logger.info("\n" + item.getId() + " " + item.getName());
                }
            }
        } else {
            logger.error("\nInvalid command. Command should be of the form: list");
        }
    }
}
