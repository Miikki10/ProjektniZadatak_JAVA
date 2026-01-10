package utilities.menus;

import core.vehicles.Car;
import core.vehicles.CarFleetRepository;

import java.util.List;
import java.util.Scanner;

/**
 * The type Car menu.
 */
public class CarMenu {
    /**
     * Start car brand menu.
     *
     * @param fleetRepository the fleet repository
     * @param scanner the scanner
     */
    public static void startCarBrandMenu(CarFleetRepository fleetRepository, Scanner scanner){
        System.out.println("--- Pretraga vozila po brandu ---");
        System.out.println("Dostupni brandovi:");
        fleetRepository.printAllBrandsModels(); // Prikaz svih dostupnih brandova

        System.out.print("Unesite brand koji želite pretražiti: ");
        String brand = scanner.nextLine();

        List<Car> foundCars = fleetRepository.findByBrandModel(brand);

        if (foundCars.isEmpty()) {
            System.out.println("Nema vozila marke '" + brand + "'.");
        } else {
            System.out.println("--- Vozila marke: " + brand + " ---");
            foundCars.forEach(car -> System.out.println(car.getFullDescription()));
            System.out.println("------------------------------------");
        }
    }

    /**
     * Print available cars menu.
     *
     * @param fleetRepository the fleet repository
     */
    public static void printAvailableCarsMenu(CarFleetRepository fleetRepository){
        System.out.println("Pokrećem ispis svih dostupnih autobmoibla");
        fleetRepository.printAvailableCars();
    }

    /**
     * Print pairs for inspection.
     *
     * @param fleetRepository the fleet repository
     */
    public static void printPairsForInspection(CarFleetRepository fleetRepository){
        fleetRepository.printCarPairs();
    }

    /**
     * Print input fleet first last.
     *
     * @param fleetRepository the fleet repository
     */
    public static void printInputFleetFirstLast(CarFleetRepository fleetRepository){
        fleetRepository.printFirstAndLastAddedCar();
    }
}
