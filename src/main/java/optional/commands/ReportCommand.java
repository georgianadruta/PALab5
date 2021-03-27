package optional.commands;

import compulsory.exceptions.MyInvalidDataException;
import optional.items.Catalog;
import optional.items.Item;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements GenericCommands {
    private Catalog catalog;
    public static final Configuration config = new Configuration(Configuration.VERSION_2_3_23);
    private static final Logger logger = LogManager.getLogger(ReportCommand.class);

    /**
     * constructor
     *
     * @param catalog a given catalog
     * @throws MyInvalidDataException
     */
    public ReportCommand(Catalog catalog) throws MyInvalidDataException {
        this.catalog = catalog;
    }

    /**
     * execute report command after validation
     *
     * @param command a string from command line
     */
    @Override
    public void run(String command) {//check if has .html extension
        logger.info("\nCreate an HTML report ...");
        try {
            String[] words = command.split(" ");
            if (words.length == 2) {
                File file = new File(words[1]);
                configuration();
                Map<String, Object> root = new HashMap<>();
                root.put("catalogName", catalog.getName());
                root.put("catalogPath", catalog.getPath());
                root.put("itemList", catalog.getItemList());
                Template temp = config.getTemplate("CatalogTemplate.ftlh");
                BufferedWriter out = new BufferedWriter(new FileWriter(words[1]));
                temp.process(root, out);
                Desktop.getDesktop().browse(file.toURI());
            } else {
                logger.error("Invalid command. Command should be of the form: save fileName");
            }
        } catch (IOException exception) {
            logger.error("IoException in report method");
        } catch (Exception e) {
            logger.error("Exception in report method");
        }
    }

    private static void configuration() {
        try {
            config.setDirectoryForTemplateLoading(new File("D:\\diverse\\FACULTATE\\ANUL 2\\sem2\\PA\\lab5\\templates"));
            config.setDefaultEncoding("UTF-8");
            config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            config.setLogTemplateExceptions(false);
        } catch (IOException exception) {
            logger.info("IOException in configuration method.");
        }
    }
}
