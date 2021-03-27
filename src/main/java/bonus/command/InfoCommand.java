package bonus.command;

import compulsory.exceptions.MyInvalidDataException;
import optional.commands.GenericCommands;
import optional.items.Catalog;
import optional.items.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

/**
 * InfoCommand class implements info command (extracts and displays metadata from item)
 */
public class InfoCommand extends Catalog implements GenericCommands {
    private static final Logger logger = LogManager.getLogger(InfoCommand.class);

    public InfoCommand(Catalog catalog) throws MyInvalidDataException {
        super(catalog.getName(), catalog.getPath(), (LinkedList<Item>) catalog.getItemList());
    }

    /**
     * is the command is valid then extracts and displays metadata from item
     * @param command a String from command line
     */
    @Override
    public void run(String command) {
        try {
            String[] words = command.split(" ");
            if (isValidCommand(words)) {
                File file = new File(words[1]);
                logger.info("Metadata from item:");
                displayMetadata(file);
            }
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException in run method");
        } catch (SAXException e) {
            logger.error("SAXException in run method");
        } catch (IOException exception) {
            logger.error("IOException  in run method");
        } catch (TikaException e) {
            logger.error("TikaException in run method");
        }
    }

    /**
     * extracts and displays metadata from an item using Apache Tike
     *
     * @param file
     * @throws IOException
     * @throws TikaException
     * @throws SAXException
     */
    private void displayMetadata(File file) throws IOException, TikaException, SAXException {
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputStream = new FileInputStream(file);
        ParseContext context = new ParseContext();
        parser.parse(inputStream, handler, metadata, context);
        System.out.println(handler.toString());
        String[] metadataNames = metadata.names();
        for (String name : metadataNames) {
            logger.info(name + ": " + metadata.get(name));
        }
    }

    /**
     * checks if the command and path are valid
     * @param words command from command line
     * @return true if the command is valid, else false
     */
    private boolean isValidCommand(String[] words) {
        if (words.length == 2) {
            File file = new File(words[1]);
            String mimetype = new MimetypesFileTypeMap().getContentType(file);
            String type = mimetype.split("/")[0];
            return file.exists() && type.equals("image");
        }
        return false;
    }
}