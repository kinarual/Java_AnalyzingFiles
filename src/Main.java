import java.util.Arrays;
import java.util.List;
import java.util.Map;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LogFileReader fileReader = new LogFileReader("C:\\Users\\User\\IdeaProjects\\untitled\\test\\log.txt");
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
    }
}