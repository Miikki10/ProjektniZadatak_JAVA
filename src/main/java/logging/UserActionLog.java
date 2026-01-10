package logging;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Represents a single user action log entry.
 */
@JacksonXmlRootElement(localName = "action")
public class UserActionLog {

    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

    @JacksonXmlProperty(localName = "timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
    private LocalDateTime timestamp;

    @JacksonXmlProperty(localName = "description")
    private String description;

    /**
     * Default constructor for Jackson deserialization.
     */
    public UserActionLog() {
    }

    /**
     * Instantiates a new User action log.
     *
     * @param timestamp   the timestamp of the action
     * @param description the description of the action
     */
    public UserActionLog(LocalDateTime timestamp, String description) {
        this.timestamp = timestamp;
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        // Formatiran ispis bez XML tagova, kako je traženo u zadatku
        return String.format("Vrijeme: %s, Akcija: %s", timestamp.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)), description);
    }
}