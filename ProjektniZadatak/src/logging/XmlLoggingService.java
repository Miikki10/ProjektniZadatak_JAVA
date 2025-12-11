package logging;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for logging user actions to an XML file.
 */
public class XmlLoggingService {

    private static final Logger logger = LoggerFactory.getLogger(XmlLoggingService.class);
    private static final String LOG_FILE_PATH = "data/user_actions.xml";
    private final XmlMapper xmlMapper;

    public XmlLoggingService() {
        xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Kreiraj 'data' direktorij ako ne postoji
        new File("data").mkdirs();
    }

    /**
     * Logs a user action by appending it to the XML log file.
     *
     * @param description A description of the action performed.
     */
    public void logAction(String description) {
        try {
            List<UserActionLog> actions = readAllActions();
            actions.add(new UserActionLog(LocalDateTime.now(), description));
            xmlMapper.writeValue(new File(LOG_FILE_PATH), actions);
            logger.debug("Logged user action: {}", description);
        } catch (IOException e) {
            logger.error("Failed to log user action to XML file.", e);
        }
    }

    /**
     * Reads all logged actions from the XML file.
     *
     * @return A list of all user actions, or an empty list if an error occurs or the file doesn't exist.
     */
    public List<UserActionLog> readAllActions() {
        File logFile = new File(LOG_FILE_PATH);
        if (!logFile.exists()) {
            return new ArrayList<>();
        }

        try {
            // Potrebno je specificirati wrapper za listu
            return xmlMapper.readValue(logFile, new TypeReference<List<UserActionLog>>() {});
        } catch (IOException e) {
            logger.error("Failed to read user actions from XML file. Returning empty list.", e);
            return new ArrayList<>();
        }
    }

    /**
     * Prints all logged actions to the console without XML tags.
     */
    public void printActions() {
        List<UserActionLog> actions = readAllActions();
        actions.forEach(System.out::println);
    }
}