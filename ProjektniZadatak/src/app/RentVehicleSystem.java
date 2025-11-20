package app;

import core.booking.BuildBookingRecord;
import core.booking.InvalidBookingDateException;
import core.people.Client;
import core.people.Employee;
import core.booking.Booking;
import core.vehicles.Car;
import core.vehicles.CarFleetRepository;
import services.RentVehicleBookingService;
import utilities.input.InputHandlerUtil;
import utilities.menus.CarMenu;
import utilities.menus.SearchMenu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
    private Map<Integer, Client> clients;
    private Map<Integer, Employee> employees;
    private Map<Integer, Car> cars;
    private Map<Integer, Booking> bookings;
    private BuildBookingRecord buildBookingRecord;
    private CarFleetRepository fleetRepository;
    private Scanner unos = new Scanner(System.in);


    /**
     * Instantiates a new Rent a car system.
     *
     * @param capacity the capacity
     */
    public RentVehicleSystem(int capacity){
        this.clients = new HashMap<>();
        this.employees = new HashMap<>();
        this.cars = new HashMap<>();
        this.bookings = new HashMap<>();
        this.buildBookingRecord = new BuildBookingRecord();
        this.fleetRepository = new CarFleetRepository();
    }

    /**
     * Initialize data.
     *
     * @param count the count
     */
    public void initializeData(int count){
        System.out.println("--------------Unos podataka------------");
        for(int i = 0; i<count; i++){
            //clients[i] = InputHandlerUtil.inputClient(unos);
            Client tmpClient = InputHandlerUtil.inputClient(unos);
            Integer clientId = tmpClient.getId();

            /**
             * Provjerava postoji li klijetn s tim objektom
             * Ako postoji ne dodajemo novi
             * !!!!!!!!!DODATI LOGBACK ovjde kad ćeš imat vremena
             */
            if (clients.containsKey(clientId)) {
                System.out.println("Greška: Klijent s ID-em " + clientId + " već postoji.");
                return;
            }

            clients.put(clientId, tmpClient);

            //employees[i] = InputHandlerUtil.inputEmployee(unos);
            /**
             * Provjerava postoji li zaposlenik s tim objektom
             * Ako postoji ne dodajemo novi
             * !!!!!!!!!DODATI LOGBACK ovjde kad ćeš imat vremena
             */
            Employee tmpEmployee = InputHandlerUtil.inputEmployee(unos);
            Integer employeeId = tmpEmployee.getId();
            if(employees.containsKey(employeeId)){
                System.out.println("Greška: Klijent s ID-em " + clientId + " već postoji.");
                return;
            }
            employees.put(employeeId, tmpEmployee);


            //cars[i] = InputHandlerUtil.inputCar(unos);
            /**
             * Provjerava postoji li automobil s tim objektom
             * Ako postoji ne dodajemo novi
             * !!!!!!!!!DODATI LOGBACK ovjde kad ćeš imat vremena
             */
            Car tmpCar = InputHandlerUtil.inputCar(unos, fleetRepository);
            Integer carId = tmpCar.getId();
            if(cars.containsKey(carId)){
                System.out.println("Greška: Klijent s ID-em " + carId + " već postoji.");
                return;
            }
            cars.put(carId, tmpCar);
        }
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
    public void startBooking() throws InvalidBookingDateException, IOException {
        System.out.println("Unesite koliko rezervacija želite napraviti: ");
        int numberOfBookings = unos.nextInt();
        unos.nextLine();

        RentVehicleBookingService userBooking = new RentVehicleBookingService(
                unos,
                numberOfBookings,
                clients,
                employees,
                cars,
                bookings,
                buildBookingRecord
        );

        userBooking.makeBooking();
    }

    /**
     * Start search menu.
     */
    public void startSearchMenu(){
        SearchMenu.selectSearchMenu(unos, clients, employees, cars, fleetRepository);
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
    public void startCarBrandMenu(){
        CarMenu.startCarBrandMenu(fleetRepository);
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
}
