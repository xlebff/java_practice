import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Message {
    final Pattern TIME_PATTERN = Pattern.compile("\\d{2}:\\d{2}");
    final Pattern SENDER_PATTERN = Pattern.compile("(?<=(\\d{2}:\\d{2}\\s))\\w+");
    final Pattern MESSAGE_PATTERN = Pattern.compile("(?<=(:\\s)).+");

    private LocalTime timestamp;
    private String sender;
    private String message;

    private String fullString;

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public Message(String fullString) {
        this.fullString = fullString;
        setTimestamp();
        setSender();
        setMessage();
    }

    private void setTimestamp() {
        Matcher matcher = TIME_PATTERN.matcher(fullString);
        while(matcher.find()) {
            this.timestamp = LocalTime.parse(matcher.group());
        }
    }

    private void setMessage() {
        Matcher matcher = MESSAGE_PATTERN.matcher(fullString);
        while (matcher.find()) {
            this.message = matcher.group();
        }
    }

    private void setSender() {
        Matcher matcher = SENDER_PATTERN.matcher(fullString);
        while (matcher.find()) {
            this.sender = matcher.group();
        }
    }
}
