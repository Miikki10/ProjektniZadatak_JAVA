package json_manager;

import backup_manager.ApplicationData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import core.people.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for saving and loading application data to/from JSON files and handling binary backups.
 */
public class JsonPersistenceService {

    private static final Logger logger = LoggerFactory.getLogger(JsonPersistenceService.class);
    private static final String DATA_DIRECTORY = "data";
    private static final String BACKUP_FILE = DATA_DIRECTORY + "/backup.bin";
    private final ObjectMapper objectMapper;

    public JsonPersistenceService() {
        objectMapper = new ObjectMapper();
        // Pretty print JSON
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        // Register module for Java 8 date/time types (LocalDate, etc.)
        objectMapper.registerModule(new JavaTimeModule());
        // Ensure date/time is not written as a timestamp
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Create data directory if it doesn't exist
        new File(DATA_DIRECTORY).mkdirs();
    }

    /**
     * Saves a list of objects to a specified JSON file.
     *
     * @param data     The list of objects to save.
     * @param filename The name of the file (e.g., "people.json").
     * @param <T>      The generic type of the objects in the list.
     */
    public <T> void saveData(List<T> data, String filename) {
        try {
            File file = new File(DATA_DIRECTORY, filename);
            objectMapper.writeValue(file, data);
            logger.info("Successfully saved {} records to {}.", data.size(), file.getPath());
        } catch (IOException e) {
            logger.error("Failed to save data to {}.", filename, e);
        }
    }

    /**
     * Loads a list of objects from a specified JSON file.
     *
     * @param filename      The name of the file (e.g., "people.json").
     * @param typeReference The TypeReference for the list of objects, needed for generics.
     * @param <T>           The generic type of the objects in the list.
     * @return A list of loaded objects, or an empty list if the file doesn't exist or an error occurs.
     */
    public <T> List<T> loadData(String filename, TypeReference<List<T>> typeReference) {
        File file = new File(DATA_DIRECTORY, filename);
        if (!file.exists()) {
            logger.warn("File {} not found, returning empty list.", file.getPath());
            return new ArrayList<>();
        }

        try {
            List<T> data = objectMapper.readValue(file, typeReference);
            logger.info("Successfully loaded {} records from {}.", data.size(), file.getPath());
            return data;
        } catch (IOException e) {
            logger.error("Failed to load data from {}. Returning empty list.", filename, e);
            return new ArrayList<>();
        }
    }

    /**
     * A specific method to load people and update the static nextId counter.
     *
     * @return A list of loaded people.
     */
    public List<Person> loadPeople() {
        List<Person> people = loadData("people.json", new TypeReference<>() {});
        // Update the static ID counter to avoid collisions with new objects
        people.forEach(person -> Person.updateNextId(person.getId()));
        return people;
    }

    /**
     * Serializes the application data to a binary backup file.
     *
     * @param data The application data container to back up.
     */
    public void backupData(ApplicationData data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BACKUP_FILE))) {
            oos.writeObject(data);
            logger.info("Successfully created a backup at {}", BACKUP_FILE);
        } catch (IOException e) {
            logger.error("Failed to create a backup.", e);
        }
    }

    /**
     * Deserializes application data from the binary backup file.
     *
     * @return The restored ApplicationData, or null if an error occurs.
     */
    public ApplicationData restoreData() {
        File backup = new File(BACKUP_FILE);
        if (!backup.exists()) {
            logger.warn("Backup file {} not found. Cannot restore.", BACKUP_FILE);
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BACKUP_FILE))) {
            ApplicationData data = (ApplicationData) ois.readObject();
            logger.info("Successfully restored data from backup.");
            return data;
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Failed to restore data from backup.", e);
            return null;
        }
    }
}