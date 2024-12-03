import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LogEntry {
    String method;
    String endpoint;
    int statusCode;
    String message;

    @Override
    public String toString() {
        return "LogEntry{" +
                "method=" + method +
                ", endpoint=" + endpoint +
                ", statusCode=" + statusCode +
                ", message=" + message + '"' +
                '}';
    }

    public LogEntry(String method, String endpoint, int statusCode, String message) {
        this.method = method;
        this.endpoint = endpoint;
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getMethod() {
        return method;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}

public class LogsProcessor {
    private List<LogEntry> logEntries;

    public LogsProcessor(List<String> logLines) {
        logEntries = new ArrayList<>();
        LogParser(logLines);
    }

    private void LogParser(List<String> logLines) {
        for (String logLine : logLines) {
            String[] parts = logLine.split(" ");
            String method = parts[5].toUpperCase();
            String endpoint = parts[6];
            int statusCode = Integer.parseInt(parts[8]);
            String message = parts[9];

            logEntries.add(new LogEntry(method, endpoint, statusCode, message));
        }
    }

    public Map<String, List<LogEntry>> groupByMethod() {
        Map<String, List<LogEntry>> grouped = new HashMap<>();
        for (LogEntry entry : logEntries) {
            grouped.computeIfAbsent(entry.getMethod(), k -> new ArrayList<>()).add(entry);
        }
        return grouped;
    }

    public Map<String, List<LogEntry>> groupByEndpoint() {
        Map<String, List<LogEntry>> grouped = new HashMap<>();
        for (LogEntry entry : logEntries) {
            grouped.computeIfAbsent(entry.getEndpoint(), k -> new ArrayList<>()).add(entry);
        }
        return grouped;
    }
}