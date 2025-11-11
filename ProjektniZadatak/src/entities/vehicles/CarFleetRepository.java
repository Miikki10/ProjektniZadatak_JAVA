package entities.vehicles;

import java.util.*;

/**
 * Svaki put kada se kreira novo vozilo dodaje se ovdje
 * koristi se set jer ne mogu postojati dvije iste instance klase Car
 * (zbog registracijske oznake)
 */


public class CarFleetRepository {
    private final Set<Car> fleet = new HashSet<>();

    /**
     * @param car
     * @return
     * ako je uspio dodati true, inače vraća false
     */
    public boolean addCarToFleet(Car car){
        boolean isAdded = fleet.add(car);
        if (!isAdded) {
            System.err.printf("Automobil s registracijom %s je već u floti (duplikat nije dodan).%n", car.getRegistration());
        }
        return isAdded;
    }

    public Car findByRegistration(String registration){
        for (Car car:fleet){
            if(car.getRegistration().equals(registration)){
                return car;
            }
        }
        return null;
    }

    public List<Car> findByBrandModel(String name){
        List<Car> matchingCars = new ArrayList<>();
        for(Car car: fleet){
            if(car.getCarBrandModel().equals(name)){
                matchingCars.add(car);
            }
        }
        return matchingCars;
    }

    public void printAllBrandsModels(){
        System.out.println("--- Trenutna Flota (" + fleet.size() + " brandova i modela) ---");
        // Pretvaranje Set-a u Listu radi sortiranja
        List<Car> cars = new ArrayList<>(fleet);
        cars.sort(Comparator.comparing(Car::getCarBrandModel));

        for(Car car:cars){
            System.out.println(car.getCarBrandModel());
        }
        System.out.println("----------------------------------------");
    }

    public void printAllCars(){
        System.out.println("--- Trenutna Flota (" + fleet.size() + " automobila) ---");
        // Pretvaranje Set-a u Listu radi sortiranja
        List<Car> cars = new ArrayList<>(fleet);
        cars.sort(Comparator.comparing(Car::getRegistration));

        for(Car car:cars){
            System.out.println(car.getFullDescription());
        }
        System.out.println("----------------------------------------");
    }
}
