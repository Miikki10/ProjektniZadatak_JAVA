package app;


import backup_manager.ApplicationData;
import com.fasterxml.jackson.core.type.TypeReference;
import core.booking.InvalidBookingDateException;
import core.booking.Booking;
import core.people.Person;
import core.vehicles.Car;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import json_manager.JsonPersistenceService;
import logging.XmlLoggingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.menus.MenuHandler;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * The type Main.
 */
public class Main extends Application {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[] args){
        logger.trace("Entering main method.");

        // 1. Inicijalizacija servisa
        JsonPersistenceService persistenceService = new JsonPersistenceService();
        XmlLoggingService loggingService = new XmlLoggingService();

        // 2. Učitavanje svih podataka na početku aplikacije
        List<Person> people = persistenceService.loadData("clients.json", new TypeReference<List<Person>>() {});
        List<Car> cars = persistenceService.loadData("cars.json", new TypeReference<List<Car>>() {});
        List<Booking> bookings = persistenceService.loadData("bookings.json", new TypeReference<List<Booking>>() {});
        logger.info("Učitano {} osoba, {} automobila, {} rezervacija.", people.size(), cars.size(), bookings.size());

        launch(args);

        // Kreiranje sustava i prosljeđivanje učitanih podataka - SAMO JEDNOM
        RentVehicleSystem app = new RentVehicleSystem(people, cars, bookings);
        logger.debug("RentVehicleSystem initialized for the entire application lifecycle.");
        
        Scanner scanner = new Scanner(System.in);
        MenuHandler menuHandler = new MenuHandler(app, scanner);

        /*while (true) {
            System.out.println("\n--- GLAVNI IZBORNIK ---");
            System.out.println("1. Pokreni aplikaciju (unos rezervacija, pretraga...)");
            System.out.println("2. Dodaj novog zaposlenika");
            System.out.println("3. Kreiraj pričuvnu kopiju (Backup)");
            System.out.println("4. Vrati podatke iz pričuvne kopije (Restore)");
            System.out.println("5. Prikaži zapisnik akcija (XML Log)");
            System.out.println("6. Spremi i izađi");
            System.out.print("Odaberite opciju: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    try {
                        menuHandler.initializeData();
                        menuHandler.runApplicationLogic();
                        loggingService.logAction("Završen rad u aplikacijskoj logici (unos, pretraga).");
                    } catch (Exception e) {
                        logger.error("Dogodila se neočekivana greška u glavnoj aplikacijskoj logici.", e);
                        System.out.println("Dogodila se greška. Provjerite log datoteku za detalje. Vraćam na glavni izbornik.");
                    }
                    break;
                case "2":
                    try {
                        app.addNewEmployee(scanner);
                        loggingService.logAction("Dodan novi zaposlenik.");
                    } catch (IOException e) {
                        logger.error("Greška prilikom unosa novog zaposlenika.", e);
                        loggingService.logAction("Neuspješan pokušaj dodavanja novog zaposlenika.");
                    }
                    break;
                case "3":
                    logger.info("Starting backup process...");
                    ApplicationData dataToBackup = new ApplicationData(people, cars, bookings);
                    persistenceService.backupData(dataToBackup);
                    loggingService.logAction("Kreirana pričuvna kopija (backup.bin).");
                    break;
                case "4":
                    logger.info("Starting restore process...");
                    ApplicationData restoredData = persistenceService.restoreData();
                    if (restoredData != null) {
                        // "Pregazi" trenutne podatke s podacima iz backupa
                        people = restoredData.getPeople();
                        cars = restoredData.getCars();
                        bookings = restoredData.getBookings();

                        // KRITIČNO: Ažuriraj i glavnu app instancu s novim podacima!
                        app = new RentVehicleSystem(people, cars, bookings);
                        menuHandler = new MenuHandler(app, scanner); // Ponovno inicijaliziraj s novim podacima

                        // KRITIČNO: Ažuriranje statičkih ID-jeva kako bi se izbjegli duplikati
                        people.forEach(person -> Person.updateNextId(person.getId()));
                        // Pretpostavka da Car.java ima sličnu metodu
                        // cars.forEach(car -> Car.updateNextId(car.getId()));
                        bookings.forEach(booking -> Booking.updateNextId(booking.getId()));

                        loggingService.logAction("Podaci vraćeni iz pričuvne kopije.");
                        logger.info("Podaci uspješno obnovljeni. Učitano {} osoba, {} automobila, {} rezervacija.",
                                people.size(), cars.size(), bookings.size());
                    } else {
                        loggingService.logAction("Pokušaj vraćanja podataka iz pričuvne kopije bio neuspješan.");
                    }
                    break;
                case "5":
                    System.out.println("\n--- ZAPISNIK KORISNIČKIH AKCIJA ---");
                    loggingService.printActions();
                    System.out.println("------------------------------------");
                    loggingService.logAction("Prikazan zapisnik korisničkih akcija.");
                    break;
                case "6":
                    logger.info("Saving all data to JSON files before exiting...");
                    persistenceService.saveData(people, "clients.json");
                    persistenceService.saveData(cars, "cars.json");
                    persistenceService.saveData(bookings, "bookings.json");
                    logger.info("Exiting application.");
                    return; // Izađi iz main metode
                default:
                    loggingService.logAction("Unesena nepoznata opcija: '" + choice + "'.");
            }
        }*/
    }

    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        // 2. Učitavanje prvog FXML prozor (npr. menu.fxml)
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        stage.setTitle("Rent-a-Vehicle System");
        stage.setScene(scene);
        stage.show();

        logger.info("JavaFX aplikacija je uspješno pokrenuta.");
    }

    public static Stage getMainStage() {
        return mainStage;
    }
}
