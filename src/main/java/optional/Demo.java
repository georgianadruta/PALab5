package optional;

import bonus.command.InfoCommand;
import compulsory.exceptions.MyInvalidDataException;
import compulsory.exceptions.MyPathException;
import lombok.Getter;
import optional.commands.*;
import optional.items.Catalog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * a helpful class to create model of the problem
 */
@Getter
public class Demo {
    private static final Logger logger = LogManager.getLogger(Demo.class);
    public Catalog catalog;
    public AddCommand addCommand;
    public InfoCommand infoCommand;
    public ListCommand listCommand;
    public LoadCommand loadCommand;
    public PlayCommand playCommand;
    public ReportCommand reportCommand;
    public SaveCommand saveCommand;

    /**
     * initialization of data
     */
    public Demo() {
        try {
            this.catalog = new Catalog("catalog", "./");
            this.addCommand = new AddCommand(this.catalog);
            this.listCommand = new ListCommand(this.catalog);
            this.loadCommand = new LoadCommand(this.catalog);
            this.playCommand = new PlayCommand(this.catalog);
            this.reportCommand = new ReportCommand(this.catalog);
            this.saveCommand = new SaveCommand(this.catalog);
            this.infoCommand = new InfoCommand(this.catalog);
        } catch (MyInvalidDataException exception) {
            logger.error("MyInvalidDataException in Demo constructor.");
        }
    }

    /**
     * displays on the screen the commands
     */
    public void displayCommands() {
        logger.info("\nWelcome to the shell :)\n" +
                "Attention! Values that represent variables and contain more than one word will contain '_' between words.\n" +
                "The commands are not sensitive case.\n" +
                "Commands to use:\n" +
                "1) add image name_of_image description id path: adds a new image into the catalog;\n" +
                "2) add book name_of_book authors_of_book id uri: adds a new book into the catalog;\n" +
                "2) list: prints the content of the catalog on the screen\n" +
                "3) play imageName: playback using the native operating system application\n" +
                "4) play videoName: playback using the native operating system application\n" +
                "5) save: saves the catalog to an external file\n" +
                "6) load: loads the catalog from an external file\n" +
                "7) report: create (and open) an HTML report representing the content of the catalog\n" +
                "8) info: extract and display metadata from catalog items\n" +
                "9) exit the shell\n");
    }

    /**
     * Repeated reads from the command line until the exit command is read
     */
    public void readCommands() {
        try {
            logger.info("\nFirst command:");
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader input = new BufferedReader(is);
            String command = input.readLine();
            while (!command.equalsIgnoreCase("exit")) {
                receiveCommands(command);
                logger.info("\nNext command:");
                command = input.readLine();
            }
        } catch (IOException exception) {
            logger.error("IOException in readCommands methods.");
        } catch (MyPathException exception) {
            logger.error("MyPathException in readCommands method.");
        } catch (MyInvalidDataException exception) {
            logger.error("MyInvalidDataException in readCommands method.");
        } catch (ClassNotFoundException exception) {
            logger.error("ClassNotFoundException  in readCommands method.");
        }
    }

    /**
     * execution of the command according to input
     *
     * @param command the string of the command line
     */
    public void receiveCommands(String command) throws MyPathException, MyInvalidDataException, ClassNotFoundException {
        String[] word = command.split(" ", 6);
        String firstWord = word[0];
        switch (firstWord.toLowerCase()) {
            case "add":
                addCommand.run(command);
                break;
            case "info":
                infoCommand.run(command);
                break;
            case "list":
                listCommand.run(command);
                break;
            case "load":
                loadCommand.run(command);
                break;
            case "play":
                playCommand.run(command);
                break;
            case "report":
                reportCommand.run(command);
                break;
            case "save":
                saveCommand.run(command);
                break;
            default:
                logger.info("Incorrect command.");
                break;
        }
    }
}

