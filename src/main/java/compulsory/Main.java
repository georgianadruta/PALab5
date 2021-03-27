package compulsory;

import compulsory.exceptions.MyInvalidDataException;
import compulsory.exceptions.MyPathException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger= LogManager.getLogger(Main.class);
    public static void main(String[] args) {

        Demo instance = new Demo();
        try {
            instance.run();
        } catch (MyInvalidDataException | MyPathException exception) {
            logger.error("Invalid path or data.");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            logger.error("\nProgram executed successfully.");
        }
    }
}
