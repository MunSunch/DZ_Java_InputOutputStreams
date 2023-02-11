package setup;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Printer {
    private Logger logger;

    public Printer(Logger logger) {
        this.logger = new Logger();
    }

    public boolean createNewDirectory(String path, String nameNewDirectory) {
        var file = new File(path, nameNewDirectory);
        boolean status = file.mkdir();
        logger.log("create newDirectory " + nameNewDirectory, status);
        return status;
    }

    public void createNewDirectory(String path, String ...nameNewDirectory) {
        Arrays.stream(nameNewDirectory).forEach(x -> createNewDirectory(path, x));
    }

    public boolean createNewFile(String path, String nameNewFile) {
        try {
            var file = new File(path, nameNewFile);
            boolean status = file.createNewFile();
            logger.log("create new file " + nameNewFile, status);
            return status;
        } catch (IOException e) {
            return false;
        }
    }

    public void createNewFile(String path, String ...nameNewFile) {
        Arrays.stream(nameNewFile).forEach(x -> createNewFile(path, x));
    }

    public void print(File file) {
        logger.print(file);
    }
}
