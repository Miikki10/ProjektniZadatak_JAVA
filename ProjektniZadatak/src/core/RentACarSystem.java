package core;

import entities.booking.BuildBookingRecord;
import entities.exceptions.InvalidBookingDateException;
import entities.people.Client;
import entities.people.Employee;
import entities.booking.Booking;
import entities.vehicles.Car;
import entities.vehicles.CarFleetRepository;
import services.BookingSystem;
import utilities.input.InputHandler;
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
public class RentACarSystem {
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
    public RentACarSystem(int capacity){
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
            //clients[i] = InputHandler.inputClient(unos);
            Client tmpClient = InputHandler.inputClient(unos);
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

            //employees[i] = InputHandler.inputEmployee(unos);
            /**
             * Provjerava postoji li zaposlenik s tim objektom
             * Ako postoji ne dodajemo novi
             * !!!!!!!!!DODATI LOGBACK ovjde kad ćeš imat vremena
             */
            Employee tmpEmployee = InputHandler.inputEmployee(unos);
            Integer employeeId = tmpEmployee.getId();
            if(employees.containsKey(employeeId)){
                System.out.println("Greška: Klijent s ID-em " + clientId + " već postoji.");
                return;
            }
            employees.put(employeeId, tmpEmployee);


            //cars[i] = InputHandler.inputCar(unos);
            /**
             * Provjerava postoji li automobil s tim objektom
             * Ako postoji ne dodajemo novi
             * !!!!!!!!!DODATI LOGBACK ovjde kad ćeš imat vremena
             */
            Car tmpCar = InputHandler.inputCar(unos, fleetRepository);
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

        BookingSystem userBooking = new BookingSystem(
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
}
