package services;

import entities.people.Client;
import entities.people.Employee;
import entities.booking.BuildBookingRecord;
import entities.exceptions.InvalidBookingDateException;

import java.awt.print.Book;
import java.io.IOException;
import entities.booking.Booking;
import entities.vehicles.Car;
import utilities.input.BookingInputHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The type Booking system.
 */
public class BookingSystem implements BookingSystemServices {
    private Map<Integer, Client> clients;
    private Map<Integer, Employee> employees;
    private Map<Integer, Car> cars;
    private Map<Integer, Booking> bookings;
    private BuildBookingRecord storage;
    private int numberOfBookings;
    private Scanner unos = new Scanner(System.in);

    /**
     * Instantiates a new Booking system.
     *
     * @param scanner          the scanner
     * @param numberOfBookings the number of bookings
     * @param clients          the clients
     * @param employees        the employees
     * @param cars             the cars
     * @param bookings         the bookings
     * @param storage          the storage
     */
    public BookingSystem(Scanner scanner,
                         int numberOfBookings,
                         Map<Integer, Client> clients,
                         Map<Integer, Employee> employees,
                         Map<Integer, Car> cars, Map<Integer, Booking> bookings,
                         BuildBookingRecord storage) {
        this.unos = scanner;
        this.numberOfBookings = numberOfBookings;
        this.clients = clients;
        this.employees = employees;
        this.cars = cars;
        this.bookings = bookings;
        this.storage = storage;
    }

    @Override
    public void makeBooking() throws InvalidBookingDateException, IOException {
        System.out.println("NAPRAVI REZERVACIJU PREMA POSTOJECIM PODATCIMA");

        for(int i = 0; i < this.numberOfBookings; i++){
            System.out.println("--- Unos za rezervaciju " + (i + 1) + " ---");

            System.out.println("Unos klijenta");
            Client bookingClient = BookingInputHandler.inputPersonBooking(this.unos, this.clients, "klijenta");

            System.out.println("Unos zaposlenika");
            Employee bookingEmployee = BookingInputHandler.inputPersonBooking(this.unos, this.employees, "zaposlenika");

            System.out.println("Unos automobila");
            Car bookingCar = BookingInputHandler.inputCarBooking(this.unos, this.cars);

            System.out.println("Unos podataka za rezervaciju");
            Booking makeNewBooking = BookingInputHandler.inputBooking(this.unos, bookingClient, bookingEmployee, bookingCar);
            Integer bookingId = makeNewBooking.getId();
            this.bookings.put(bookingId, makeNewBooking);

            this.storage.addPermanentRecord(makeNewBooking);
        }
    }
}
