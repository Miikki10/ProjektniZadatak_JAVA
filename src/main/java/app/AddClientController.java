package app;

import com.fasterxml.jackson.core.type.TypeReference;
import core.people.Client;
import core.people.Person;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import json_manager.JsonPersistenceService;

import java.time.LocalDate;
import java.util.List;

public class AddClientController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private DatePicker dateOfBirthPicker;

    private final JsonPersistenceService persistenceService = new JsonPersistenceService();

    @FXML
    private void addClient() {
        String name = nameField.getText();
        String email = emailField.getText();
        LocalDate dateOfBirth = dateOfBirthPicker.getValue();

        if (name.isEmpty() || email.isEmpty() || dateOfBirth == null) {
            // Handle error - for now, just print to console
            System.out.println("Please fill in all fields.");
            return;
        }

        Client newClient = new Client(name, email, dateOfBirth);

        List<Person> people = persistenceService.loadData("clients.json", new TypeReference<List<Person>>() {});
        people.add(newClient);
        persistenceService.saveData(people, "clients.json");

        System.out.println("New client created and saved: " + newClient);
        
        // Close the window
        nameField.getScene().getWindow().hide();
    }
}
