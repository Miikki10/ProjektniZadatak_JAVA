package utilities.menus;

import app.RentVehicleSystem;
import core.booking.InvalidBookingDateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Scanner;

public class MenuHandler {

    private static final Logger logger = LoggerFactory.getLogger(MenuHandler.class);
    private final RentVehicleSystem rentVehicleSystem;
    private final Scanner scanner;

    public MenuHandler(RentVehicleSystem rentVehicleSystem, Scanner scanner) {
        this.rentVehicleSystem = rentVehicleSystem;
        this.scanner = scanner;
    }

    public void initializeData() {
        try {
            System.out.print("Unesite broj klijenata koje želite unijeti: ");
            int clientCount = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < clientCount; i++) {
                rentVehicleSystem.addNewClient(scanner);
            }

            System.out.print("Unesite broj zaposlenika koje želite unijeti: ");
            int employeeCount = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < employeeCount; i++) {
                rentVehicleSystem.addNewEmployee(scanner);
            }

            System.out.print("Unesite broj automobila koje želite unijeti: ");
            int carCount = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < carCount; i++) {
                rentVehicleSystem.addNewCar(scanner);
            }
        } catch (NumberFormatException e) {
            logger.warn("Neispravan unos broja. Molimo unesite cijeli broj.", e);
            System.out.println("Greška: Unesite ispravan broj.");
        } catch (IOException e) {
            logger.error("Greška prilikom unosa podataka.", e);
            System.out.println("Došlo je do greške prilikom unosa.");
        }
    }

    public void runApplicationLogic() {
        boolean inAppMenu = true;

        while (inAppMenu) {
            System.out.println("\n--- IZBORNIK APLIKACIJE ---");
            System.out.println("1. Unos nove rezervacije");
            System.out.println("2. Pretraga (osobe, vozila)");
            System.out.println("3. Prikaži sva dostupna vozila");
            System.out.println("4. Prikaži vozila po brandu");
            System.out.println("5. Prikaži parove vozila za inspekciju");
            System.out.println("6. Prikaži prvo i zadnje dodano vozilo");
            System.out.println("7. Povratak na glavni izbornik");
            System.out.print("Odaberite opciju: ");

            String appChoice = scanner.nextLine();

            switch (appChoice) {
                case "1":
                    try {
                        logger.info("Starting booking process...");
                        rentVehicleSystem.startBooking(scanner);
                        logger.info("Booking process session finished.");
                    } catch (InvalidBookingDateException e) {
                        logger.warn("Error during date entry: {}. Please, try entering the booking information again.", e.getMessage());
                    } catch (IOException e) {
                        logger.error("Critical I/O error during booking: {}.", e.getMessage(), e);
                    } catch (Exception e) {
                        logger.error("An unexpected error occurred during booking: {}.", e.getMessage(), e);
                    }
                    break;
                case "2":
                    logger.info("Starting search menu...");
                    rentVehicleSystem.startSearchMenu(scanner);
                    break;
                case "3":
                    logger.info("Starting print of all available cars");
                    rentVehicleSystem.startAvailableCarsMenu();
                    break;
                case "4":
                    logger.info("Starting print of all cars by brand");
                    rentVehicleSystem.startCarBrandMenu(scanner);
                    break;
                case "5":
                    logger.info("Starting print of pairs of cars for inspection");
                    rentVehicleSystem.startInspectionPairs();
                    break;
                case "6":
                    logger.info("Starting print of first and last added car");
                    rentVehicleSystem.firstLastAddedCar();
                    break;
                case "7":
                    inAppMenu = false;
                    logger.info("Returning to main menu.");
                    break;
                default:
                    System.out.println("Nepoznata opcija. Molimo pokušajte ponovo.");
                    break;
            }
        }
    }
}
