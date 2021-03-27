package compulsory.catalog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.*;
import java.net.URI;

/**
 * CatalogUtil class contains commands that will manage the content of the catalog:
 * save
 * load
 * openUri
 * openLocalPath
 */
public class CatalogUtil {
    private static final Logger logger = LogManager.getLogger(CatalogUtil.class);

    /**
     * saves the catalog to an external file given as parameter.
     */
    public static void save(Catalog catalog, String fileName) {
        logger.info("\nSave...");
        try {
            File file = new File(fileName);
            FileWriter fw = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(catalog.toString());
            pw.close();
        } catch (IOException exception) {
           logger.error("IoException in save method");
        }
    }

    /**
     * loads the catalog from an external file.
     * de-serialization
     * some problem here...
     */
    public static void load(String filename) {
        logger.info("\nLoad...");
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            Catalog result = (Catalog) in.readObject(); // here throws IOException. i still don't get it, but I'm working on it
            in.close();
            logger.info(result.toString());
        } catch (FileNotFoundException e) {
            logger.error("File not found in load method.");
        } catch (IOException e) {
            logger.error("IOException in load method.");
        } catch (ClassNotFoundException e) {
            logger.info("Class not found in load method.");
        }
    }

    /**
     * helpful method to open a book
     *
     * @param uri book's path
     * @throws IOException
     */
    public static void openUri(String uri) throws IOException {
        logger.info("\nOpen...");
        try {
            Desktop.getDesktop().browse(URI.create(uri));
        } catch (IOException e) {
            throw new IOException("IOException in openUri method.");
        }
    }

    /**
     * helpful method to open an image
     *
     * @param path image's path
     * @throws IOException
     */
    public static void openLocalFile(String path) throws IOException {
        logger.info("\nOpen...");
        try {
            Desktop.getDesktop().open(new File(path));
        } catch (IOException exception) {
            throw new IOException("IOException in openLocalFile method.");
        }
    }
}
