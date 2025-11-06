package core;

import entities.exceptions.InvalidBookingDateException;
import entities.people.Client;
import entities.people.Employee;
import entities.booking.RecordStorage;
import entities.booking.Booking;
import entities.vehicles.Car;
import services.BookingSystem;
import utilities.input.InputHandler;
import utilities.menus.SearchMenu;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 */

/**
 * The type Rent a car system.
 *
 * @author Bruno
 */
public class RentACarSystem {
    private static final int MAX_CAPACITY = 100;

    private Client[] clients = new Client[MAX_CAPACITY];
    private Employee[]employees = new Employee[MAX_CAPACITY];
    private Car[] cars = new Car[MAX_CAPACITY];
    private Booking[] bookings = new Booking[MAX_CAPACITY];
    private RecordStorage recordStorage;
    private Scanner unos = new Scanner(System.in);

    /**
     * Instantiates a new Rent a car system.
     *
     * @param capacity the capacity
     */
    public RentACarSystem(int capacity){
        this.clients = new Client[capacity];
        this.employees = new Employee[capacity];
        this.cars = new Car[capacity];
        this.bookings = new Booking[capacity];
        this.recordStorage = new RecordStorage();
    }

    /**
     * Initialize data.
     *
     * @param count the count
     */
    public void initializeData(int count){
        System.out.println("--------------Unos podataka------------");
        for(int i = 0; i<count; i++){
            clients[i] = InputHandler.inputClient(unos);

            employees[i] = InputHandler.inputEmployee(unos);

            cars[i] = InputHandler.inputCar(unos);
        }
    }

    /**
     * The Number of bookings.
     */
    int numberOfBookings = 5;

    /**
     * Start booking.
     *
     * @throws InvalidBookingDateException the invalid booking date exception
     * @throws IOException                 the io exception
     */
    public void startBooking() throws InvalidBookingDateException, IOException {
        BookingSystem userBooking = new BookingSystem(
                unos,
                numberOfBookings,
                clients,
                employees,
                cars,
                bookings,
                recordStorage
        );

        userBooking.makeBooking();
    }

    /**
     * Start search menu.
     */
    public void startSearchMenu(){
        SearchMenu.selectSearchMenu(unos, clients, employees, cars);
    }
}
