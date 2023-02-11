package setup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    private List<String> logs;

    public Logger() {
        logs = new ArrayList<>();
    }

    public void log(String message, boolean status) {
        String currentDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        String separator = " ";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("date:" + currentDate);
        stringBuilder.append(separator);
        stringBuilder.append("message:<<" + message + ">> ");
        stringBuilder.append(separator);
        stringBuilder.append("status:" + status);
        logs.add(stringBuilder.toString());
    }

    public void print(File file) {
        try(FileWriter writer = new FileWriter(file, true)) {
            for (String log: logs) {
                writer.write(log);
                writer.write('\n');
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
