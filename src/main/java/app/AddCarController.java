package app;

import com.fasterxml.jackson.core.type.TypeReference;
import core.vehicles.Car;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import json_manager.JsonPersistenceService;

import java.math.BigDecimal;
import java.util.List;

public class AddCarController {

    @FXML
    private TextField brandField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField pricePerDayField;
    @FXML
    private TextField registrationField;
    @FXML
    private TextField colorField;
    @FXML
    private TextField passengerCapacityField;

    private final JsonPersistenceService persistenceService = new JsonPersistenceService();

    @FXML
    private void addCar() {
        try {
            String brand = brandField.getText();
            String model = modelField.getText();
            Integer year = Integer.parseInt(yearField.getText());
            BigDecimal pricePerDay = new BigDecimal(pricePerDayField.getText());
            String registration = registrationField.getText();
            String color = colorField.getText();
            Integer passengerCapacity = passengerCapacityField.getText().isEmpty() ? null : Integer.parseInt(passengerCapacityField.getText());

            Car.Builder builder = new Car.Builder()
                    .setBrand(brand)
                    .setModel(model)
                    .setYear(year)
                    .setPricePerDay(pricePerDay);

            if (!registration.isEmpty()) {
                builder.setRegistration(registration);
            }
            if (!color.isEmpty()) {
                builder.setColor(color);
            }
            if (passengerCapacity != null) {
                builder.setPassengerCapacity(passengerCapacity);
            }

            Car newCar = builder.build();

            List<Car> cars = persistenceService.loadData("cars.json", new TypeReference<>() {});
            cars.add(newCar);
            persistenceService.saveData(cars, "cars.json");

            System.out.println("New car created and saved: " + newCar.getFullDescription());

            // Close the window
            brandField.getScene().getWindow().hide();

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        } catch (Exception e) {
            System.out.println("Error creating car: " + e.getMessage());
        }
    }
}
