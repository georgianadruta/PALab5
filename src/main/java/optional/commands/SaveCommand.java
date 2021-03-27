package optional.commands;

import compulsory.exceptions.MyInvalidDataException;
import optional.items.Catalog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * SaveCommand class implements save command (saves the catalog to an external file (as a text or binary)
 */
public class SaveCommand implements GenericCommands {

    private Catalog catalog;
    private static final Logger logger = LogManager.getLogger(SaveCommand.class);

    /**
     * @param catalog catalog that will be saved
     * @throws MyInvalidDataException
     */
    public SaveCommand(Catalog catalog) throws MyInvalidDataException {
        this.catalog=catalog;
    }

    /**
     * check if the command is valid (is of the form: save fileName)
     * then saves the catalog to an external file from the command line(=words[1])
     */
    @Override
    public void run( String command) {
        logger.info("\nSave...");
        try {
            String[] words = command.split(" ");
            if (words.length == 2) {
                String fileName = words[1];
                File file = new File(fileName);
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(this.catalog.toString());
                pw.close();
            } else {
                logger.error("Invalid command. Command should be of the form: save fileName");
            }
        } catch (IOException exception) {
            logger.error("IoException in save method");
        }
    }
}
