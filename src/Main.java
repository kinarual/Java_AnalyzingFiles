import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            LogFileReader fileReader = new LogFileReader(filePath);
            String[] logLines = fileReader.readFile();
            if (logLines.length == 0) {
                System.out.println("Failed to read log file.");
                return;
            }
            LogsProcessor processor = new LogsProcessor(Arrays.asList(logLines));

            Map<String, List<LogEntry>> groupedByMethod = processor.groupByMethod();
            System.out.println("Group by Method: \n" + groupedByMethod + "\n\n");

            Map<String, List<LogEntry>> groupedByEndpoint = processor.groupByEndpoint();
            System.out.println("Group by Endpoint: \n" + groupedByEndpoint);
        } else {
            System.out.println("User cancelled file selection.");
        }
    }
}