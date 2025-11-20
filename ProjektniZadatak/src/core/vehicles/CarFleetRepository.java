package core.vehicles;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Gatherers;
import java.util.stream.Stream;


/**
 * Svaki put kada se kreira novo vozilo dodaje se ovdje
 * koristi se set jer ne mogu postojati dvije iste instance klase Car
 * (zbog registracijske oznake)
 */
public class CarFleetRepository {
    private final Set<Car> fleet = new HashSet<>();

    /**
     * Add car to fleet boolean.
     *
     * @param car the car
     * @return ako je uspio dodati true, inače vraća false
     */
    public boolean addCarToFleet(Car car){
        boolean isAdded = fleet.add(car);
        if (!isAdded) {
            System.err.printf("Automobil s registracijom %s je već u floti (duplikat nije dodan).%n", car.getRegistration());
        }
        return isAdded;
    }

    /**
     * Find by registration car.
     *
     * @param registration the registration
     * @return the car
     */
    public Car findByRegistration(String registration){
        for (Car car:fleet){
            if(car.getRegistration().equals(registration)){
                return car;
            }
        }
        return null;
    }

    /**
     * Find by brand model list.
     *
     * @param name the name
     * @return the list
     */
    public List<Car> findByBrandModel(String name){
        List<Car> matchingCars = new ArrayList<>();
        for(Car car: fleet){
            if(car.getCarBrandModel().equals(name)){
                matchingCars.add(car);
            }
        }
        return matchingCars;
    }

    /**
     * Print all brands models.
     */
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

    /**
     * Print all cars.
     */
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

    /**
     * Partition by availability map.
     *
     * @return the map
     */
    public Map<Boolean, List<Car>> partitionByAvailability(){
        return fleet.stream()
                .collect(Collectors.partitioningBy(Car::isAvailable));
    }

    /**
     * Get available cars list.
     *
     * @return the list
     */
    public List<Car> getAvailableCars(){
        return partitionByAvailability().get(true);
    }

    /**
     * Print available cars.
     */
    public void printAvailableCars() {
        System.out.println("--- Dostupni automobili za najam (" + getAvailableCars().size() + ") ---");

        // Sortiranje i ispis dostupnih vozila
        getAvailableCars().stream()
                .sorted(Comparator.comparing(Car::getRegistration))
                .forEach(car -> System.out.println(car.getFullDescription()));

        System.out.println("----------------------------------------");
    }

    /**
     * Group cars by brand model map .
     *
     * @return the map
     */
    public Map <String, List<Car>> groupCarsByBrandModel () {
            return fleet.stream()
                    .collect(Collectors.groupingBy(Car::getCarBrandModel));
    }

    /**
     * Print cars by brand model.
     */
    public void printCarsByBrandModel(){
        System.out.println("--- Broj automobila po markama/modelima ---");
        Map<String, List<Car>> grouped = groupCarsByBrandModel();

        grouped.entrySet()
                .stream().sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    System.out.printf("%s: %d komada%n", entry.getKey(), entry.getValue().size());
                });
        System.out.println("----------------------------------------");
    }

    /**
     * Print first and last added car.
     */
    public void printFirstAndLastAddedCar() {
        List<Car> orderedFleet = new ArrayList<>(fleet);

        if (orderedFleet.isEmpty()) {
            System.out.println("Flota je prazna.");
            return;
        }

        Car firstCar = orderedFleet.getFirst();
        Car lastCar = orderedFleet.getLast();

        System.out.println("--- Sequenced Collections Primjer ---");
        System.out.println("Najstariji (prvi dodan): " + firstCar.getFullDescription());
        System.out.println("Najnoviji (zadnji dodan): " + lastCar.getFullDescription());
    }

    /**
     * Grupiranje automobila u parove (prozore veličine 2) za inspekciju.
     *
     * @return Stream<List<Car>> gdje svaka lista sadrži 1 ili 2 automobila.
     */
    public Stream<List<Car>> pairCarsForInspection() {
        System.out.println("--- Kreiranje parova za inspekciju ---");

        return fleet.stream()
                .gather(Gatherers.windowFixed(2));
    }

    /**
     * Print car pairs.
     */
    public void printCarPairs() {
        System.out.println("----------------------------------------");
        pairCarsForInspection().forEach(pair -> {
            if (pair.size() == 2) {
                System.out.printf("Par: [%s] i [%s]%n",
                        pair.get(0).getRegistration(),
                        pair.get(1).getRegistration());
            } else if (pair.size() == 1) {
                System.out.printf("Neparni auto: [%s]%n", pair.get(0).getRegistration());
            }
        });
        System.out.println("----------------------------------------");
    }
}

