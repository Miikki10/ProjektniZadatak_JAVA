package services;

import core.people.Client;
import core.people.Employee;
import core.booking.BuildBookingRecord;
import core.booking.InvalidBookingDateException;

import java.io.IOException;
import core.booking.Booking;
import core.vehicles.Car;
import utilities.input.BookingInputHandlerUtil;

import java.util.Map;
import java.util.Scanner;

/**
 * The type Booking system.
 */
public class RentVehicleBookingService implements BookingService {
    private final Map<Integer, Client> clients;
    private final Map<Integer, Employee> employees;
    private final Map<Integer, Car> cars;
    private final Map<Integer, Booking> bookings;
    private final BuildBookingRecord storage;
    private final int numberOfBookings;
    //private final Scanner unos = new Scanner(System.in);
    private final Scanner unos;

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
    public RentVehicleBookingService(Scanner scanner,
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
            Client bookingClient = BookingInputHandlerUtil.inputPersonBooking(this.unos, this.clients, "klijenta");

            System.out.println("Unos zaposlenika");
            Employee bookingEmployee = BookingInputHandlerUtil.inputPersonBooking(this.unos, this.employees, "zaposlenika");

            System.out.println("Unos automobila");
            Car bookingCar = BookingInputHandlerUtil.inputCarBooking(this.unos, this.cars);

            System.out.println("Unos podataka za rezervaciju");
            Booking makeNewBooking = BookingInputHandlerUtil.inputBooking(this.unos, bookingClient, bookingEmployee, bookingCar);
            Integer bookingId = makeNewBooking.getId();
            this.bookings.put(bookingId, makeNewBooking);

            this.storage.addPermanentRecord(makeNewBooking);
        }
    }
}
