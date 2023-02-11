package setup;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer(new Logger());

        //Предварительно вручную создайте папку Games в удобном для Вас месте
        printer.createNewDirectory("./src", "games");

        //В папке Games создайте несколько директорий: src, res, savegames, temp
        String[] gamesDirectories = {"src", "res", "savegames", "temp"};
        printer.createNewDirectory("./src/games", gamesDirectories);


        //В каталоге src создайте две директории: main, test
        String[] srcDirectories = {"main", "test"};
        printer.createNewDirectory("./src/games/src", srcDirectories);


        //В подкаталоге main создайте два файла: Main.java, Utils.java.
        String[] mainFiles = {"Main.java", "Utils.java"};
        printer.createNewFile("./src/games/main", mainFiles);


        //В каталог res создайте три директории: drawables, vectors, icons.
        String[] resDirectories = {"drawables", "vectors", "icons"};
        printer.createNewDirectory("./src/games/res", resDirectories);


        //В директории temp создайте файл temp.txt.
        printer.createNewFile("./src/games/temp", "temp.txt");
        printer.print(new File("./src/games/temp/temp.txt"));
    }
}
