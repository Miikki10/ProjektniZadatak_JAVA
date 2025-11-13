package utilities.menus;

import entities.vehicles.CarFleetRepository;

public class CarMenu {
    public static void startCarBrandMenu(CarFleetRepository fleetRepository){
        System.out.println("Pokrećem ispis svih autobmoibla po markama vozila");
        fleetRepository.printCarsByBrandModel();
    }

    public static void printAvailableCarsMenu(CarFleetRepository fleetRepository){
        System.out.println("Pokrećem ispis svih dostupnih autobmoibla");
        fleetRepository.printAvailableCars();
    }

    public static void printPairsForInspection(CarFleetRepository fleetRepository){
        fleetRepository.printCarPairs();
    }

    public static void printInputFleetFirstLast(CarFleetRepository fleetRepository){
        fleetRepository.printFirstAndLastAddedCar();
    }
}
