import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class LogFileReader {
    private String filePath;

    public LogFileReader(String pathToFile) {
        this.filePath = pathToFile;
    }

    public String[] readFile() {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException err) {
            System.err.println("Error reading file: " + err.getMessage());
            return new String[]{};
        }
        return contentBuilder.toString().split("\n");
    }
}