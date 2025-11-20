package utilities.menus;

import core.vehicles.CarFleetRepository;

/**
 * The type Car menu.
 */
public class CarMenu {
    /**
     * Start car brand menu.
     *
     * @param fleetRepository the fleet repository
     */
    public static void startCarBrandMenu(CarFleetRepository fleetRepository){
        System.out.println("Pokrećem ispis svih autobmoibla po markama vozila");
        fleetRepository.printCarsByBrandModel();
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
