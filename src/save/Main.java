package save;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        //Создать три экземпляра класса GameProgress.
        GameProgress firstGameProgress = new GameProgress(100, 1, 1, 0.1);
        GameProgress secondGameProgress = new GameProgress(75, 3, 4, 22.1);
        GameProgress thirdGameProgress = new GameProgress(97, 10, 8, 99);

        //Сохранить сериализованные объекты GameProgress в папку savegames из предыдущей задачи
        String path = "./src/games/savegames";
        saveGame(path, firstGameProgress);
        saveGame(path, secondGameProgress);
        saveGame(path, thirdGameProgress);

        //Созданные файлы сохранений из папки savegames запаковать в архив zip.
        zipFiles(path + "/GameSaves.zip", path + "/");

        //Удалить файлы сохранений, лежащие вне архива.
        File dir = new File(path);
        for (var file: dir.listFiles()) {
            String fileName = file.getName();
            String extensionFile = fileName.substring(fileName.indexOf('.'));
            if(!extensionFile.equals(".zip")) {
                file.delete();
            }
        }

    }

    private static void zipFiles(String path2Zip, String path2File) {
        File directory = new File(path2File);
        if(!directory.isDirectory())
            throw new IllegalArgumentException();

        try (ZipOutputStream zipOutputStream =
                     new ZipOutputStream(new FileOutputStream(path2Zip)))
        {
            for (var file: directory.listFiles()) {
                String fileName = file.getName();
                String extensionFile = fileName.substring(fileName.indexOf('.'));
                if(file.isHidden() || extensionFile.equals(".zip")) {
                    continue;
                }

                FileInputStream fileInputStream =
                        new FileInputStream(path2File + "/" + file.getName());
                var zipEntry = new ZipEntry("packed_" + file.getName());
                zipOutputStream.putNextEntry(zipEntry);
                byte[] buffer = new byte[fileInputStream.available()];
                fileInputStream.read(buffer);
                zipOutputStream.write(buffer);
                zipOutputStream.closeEntry();

                fileInputStream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveGame(String path, GameProgress gameProgress) {
        File file = new File(path, "saveGame" + gameProgress.hashCode() + ".dat");
        try(FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream))
        {
            objectOutputStream.writeObject(gameProgress);
            objectOutputStream.flush();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
