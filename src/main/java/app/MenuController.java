package app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private void runApplicationLogic() {
        System.out.println("runApplicationLogic called");
    }

    @FXML
    private void addNewEmployee() {
        System.out.println("addNewEmployee called");
    }

    @FXML
    private void addClient() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add_client.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Add New Client");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addCar() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add_car.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Add New Car");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openSearch() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("search.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Search");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void backupData() {
        System.out.println("backupData called");
    }

    @FXML
    private void restoreData() {
        System.out.println("restoreData called");
    }

    @FXML
    private void showLogs() {
        System.out.println("showLogs called");
    }

    @FXML
    private void saveAndExit() {
        System.out.println("saveAndExit called");
    }
}
