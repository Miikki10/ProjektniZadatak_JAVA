package app;

import core.booking.BuildBookingRecord;
import core.booking.InvalidBookingDateException;
import core.people.Client;
import core.people.Employee;
import core.booking.Booking;
import core.people.Person;
import core.vehicles.Car;
import core.vehicles.CarFleetRepository;
import services.RentVehicleBookingService;
import utilities.input.InputHandlerUtil;
import utilities.menus.CarMenu;
import utilities.menus.SearchMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */

/**
 * The type Rent a car system.
 * Glavna klasa koja se pokreće u Mainu - predstavlja aplikaciju
 *
 * @author Bruno
 */
public class RentVehicleSystem {
    private final List<Person> people;
    private final List<Car> cars;
    private final List<Booking> bookings;
    private BuildBookingRecord buildBookingRecord;
    private CarFleetRepository fleetRepository;


    /**
     * Instantiates a new Rent a car system with pre-loaded data.
     *
     * @param people   The list of all people (clients and employees).
     * @param cars     The list of all cars.
     * @param bookings The list of all bookings.
     */
    public RentVehicleSystem(List<Person> people, List<Car> cars, List<Booking> bookings){
        this.people = people;
        this.cars = cars;
        this.bookings = bookings;
        this.buildBookingRecord = new BuildBookingRecord();
        this.fleetRepository = new CarFleetRepository();
        // Populate the fleet repository with cars loaded from JSON
        this.cars.forEach(this.fleetRepository::addCarToFleet);
    }

    /**
     * The Number of bookings.
     */
    //int numberOfBookings = 5;

    /**
     * Start booking.
     *
     * @throws InvalidBookingDateException the invalid booking date exception
     * @throws IOException                 the io exception
     */
    public void startBooking(Scanner scanner) throws InvalidBookingDateException, IOException {
        // PROVJERA 1: Postoji li ijedan zaposlenik u sustavu?
        boolean employeeExists = this.people.stream().anyMatch(person -> person instanceof Employee);
        if (!employeeExists) {
            System.out.println("\n!!! UPOZORENJE: U sustavu ne postoji nijedan zaposlenik.");
            System.out.println("Molimo, prvo dodajte zaposlenika putem opcije '2' u glavnom izborniku prije kreiranja rezervacije.");
            return; // Prekini izvođenje metode i vrati se u glavni izbornik
        }

        // PROVJERA 2: Postoji li ijedan klijent u sustavu?
        boolean clientExists = this.people.stream().anyMatch(person -> person instanceof Client);
        if (!clientExists) {
            System.out.println("\n!!! UPOZORENJE: U sustavu ne postoji nijedan klijent.");
            System.out.println("Molimo, prvo dodajte klijenta putem opcije '1' -> '1' (Unos nove rezervacije) prije nastavka.");
            // Ovdje bi se mogao dodati i direktan unos klijenta ako želimo
            return;
        }

        System.out.println("Unesite koliko rezervacija želite napraviti: ");
        int numberOfBookings = scanner.nextInt();
        scanner.nextLine();

        RentVehicleBookingService userBooking = new RentVehicleBookingService(
                scanner,
                numberOfBookings,
                people,
                cars,
                bookings,
                buildBookingRecord
        );

        userBooking.makeBooking();
    }

    /**
     * Start search menu.
     */
    public void startSearchMenu(Scanner scanner){
        SearchMenu.selectSearchMenu(scanner, people, fleetRepository);
    }

    /**
     * Start available cars menu.
     */
    public void startAvailableCarsMenu(){
        CarMenu.printAvailableCarsMenu(fleetRepository);
    }

    /**
     * Start car brand menu.
     */
    public void startCarBrandMenu(Scanner scanner){
        CarMenu.startCarBrandMenu(fleetRepository, scanner);
    }

    /**
     * Start inspection pairs.
     */
    public void startInspectionPairs(){
        CarMenu.printPairsForInspection(fleetRepository);
    }

    /**
     * First last added car.
     */
    public void firstLastAddedCar(){
        CarMenu.printInputFleetFirstLast(fleetRepository);
    }

    /**
     * Adds a new employee to the system by taking user input.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void addNewEmployee(Scanner scanner) throws IOException {
        System.out.println("--- Kreiranje novog zaposlenika ---");
        Employee newEmployee = InputHandlerUtil.inputEmployee(scanner);
        this.people.add(newEmployee);
        System.out.println("Novi zaposlenik uspješno dodan.");
    }

    public void addNewClient(Scanner scanner) throws IOException {
        System.out.println("--- Kreiranje novog klijenta ---");
        Client newClient = InputHandlerUtil.inputClient(scanner);
        this.people.add(newClient);
        System.out.println("Novi klijent uspješno dodan.");
    }

    public void addNewCar(Scanner scanner) throws IOException {
        System.out.println("--- Kreiranje novog automobila ---");
        Car newCar = InputHandlerUtil.inputCar(scanner);
        boolean addedToFleet = this.fleetRepository.addCarToFleet(newCar);
        if (addedToFleet) {
            this.cars.add(newCar);
            System.out.println("Novi automobil uspješno dodan.");
        }
    }
}
