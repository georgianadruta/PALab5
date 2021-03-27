package optional.commands;

import compulsory.exceptions.MyInvalidDataException;
import optional.items.Catalog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * LoadCommand class implements load command (loads the catalog from an external file)
 */
public class LoadCommand implements GenericCommands {
    private Catalog catalog;
    private static final Logger logger = LogManager.getLogger(LoadCommand.class);

    /**
     * constructor
     *
     * @throws MyInvalidDataException
     */
    public LoadCommand(Catalog catalog) throws MyInvalidDataException {
        this.catalog = catalog;
    }

    /**
     * loads the catalog from an external file(=words[2]).
     * de-serialization
     * some problem here...
     */
    public void run(String command) {
        logger.info("\nLoad...");
        String[] words = command.split(" ");
        if (words.length == 2) {
            String fileName = words[1];
            readFromFile(fileName);
        } else {
            logger.error("Invalid command. Command should be of the form: load fileName");
        }
    }


    /**
     * checks if a given file exists then reads the contents of the file
     *
     * @param fileName a given file
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readFromFile(String fileName) {
        try {
            File f = new File(fileName);
            if (f.exists() && !f.isDirectory()) {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
                Catalog result = (Catalog) in.readObject(); // here throws IOException. i still don't get it, but I'm working on it
                in.close();
                logger.info(result.toString());
            } else {
                logger.error("The file " + fileName + " doesn't exist");
            }
        } catch (IOException exception) {
            logger.error("IOException in readFromFile method.");
        } catch (ClassNotFoundException e) {
            logger.info("Class not found in readFromFile method.");
        }
    }
}
