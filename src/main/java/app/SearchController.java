package app;

import com.fasterxml.jackson.core.type.TypeReference;
import core.people.Client;
import core.people.Person;
import core.vehicles.Car;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import json_manager.JsonPersistenceService;

import java.util.List;
import java.util.stream.Collectors;

public class SearchController {

    @FXML
    private TextField clientSearchField;
    @FXML
    private TableView<Person> clientTableView;
    @FXML
    private TextField carSearchField;
    @FXML
    private TableView<Car> carTableView;

    private final JsonPersistenceService persistenceService = new JsonPersistenceService();
    private List<Person> allClients;
    private List<Car> allCars;

    @FXML
    public void initialize() {
        allClients = persistenceService.loadData("clients.json", new TypeReference<List<Person>>() {});
        clientTableView.setItems(FXCollections.observableArrayList(allClients));

        allCars = persistenceService.loadData("cars.json", new TypeReference<List<Car>>() {});
        carTableView.setItems(FXCollections.observableArrayList(allCars));

        clientSearchField.textProperty().addListener((observable, oldValue, newValue) -> filterClients(newValue));
        carSearchField.textProperty().addListener((observable, oldValue, newValue) -> filterCars(newValue));
    }

    private void filterClients(String searchText) {
        if (searchText == null || searchText.isEmpty()) {
            clientTableView.setItems(FXCollections.observableArrayList(allClients));
            return;
        }
        String lowerCaseFilter = searchText.toLowerCase();
        List<Person> filteredClients = allClients.stream()
                .filter(client -> client.getName().toLowerCase().contains(lowerCaseFilter))
                .collect(Collectors.toList());
        clientTableView.setItems(FXCollections.observableArrayList(filteredClients));
    }

    private void filterCars(String searchText) {
        if (searchText == null || searchText.isEmpty()) {
            carTableView.setItems(FXCollections.observableArrayList(allCars));
            return;
        }
        String lowerCaseFilter = searchText.toLowerCase();
        List<Car> filteredCars = allCars.stream()
                .filter(car -> car.getBrand().toLowerCase().contains(lowerCaseFilter) ||
                               car.getModel().toLowerCase().contains(lowerCaseFilter))
                .collect(Collectors.toList());
        carTableView.setItems(FXCollections.observableArrayList(filteredCars));
    }
}
