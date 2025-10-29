package services;

import entities.people.Client;
import entities.people.Employee;
import entities.booking.RecordStorage;
import entities.booking.Booking;
import entities.vehicles.Car;
import utilities.input.BookingInputHandler;

import java.util.Scanner;

public class BookingSystem implements BookingSystemServices {
    private static final int MAX_CAPACITY = 100;

    private Client[] clients = new Client[MAX_CAPACITY];
    private Employee[]employees = new Employee[MAX_CAPACITY];
    private Car[] cars = new Car[MAX_CAPACITY];
    private Booking[] bookings = new Booking[MAX_CAPACITY];
    //private RecordStorage[] records = new RecordStorage[MAX_CAPACITY];
    private final RecordStorage storage;
    private int numberOfBookings;
    private Scanner unos = new Scanner(System.in);

    public BookingSystem(Scanner scanner, int numberOfBookings, Client[] clients, Employee[] employees, Car[] cars, Booking[] bookings, RecordStorage storage) {
        this.unos = scanner;
        this.numberOfBookings = numberOfBookings;
        this.clients = clients;
        this.employees = employees;
        this.cars = cars;
        this.bookings = bookings;
        this.storage = storage;
    }

    @Override
    public void makeBooking() {
        System.out.println("NAPRAVI REZERVACIJU PREMA POSTOJECIM PODATCIMA");

        for(int i = 0; i < this.numberOfBookings; i++){
            System.out.println("--- Unos za rezervaciju " + (i + 1) + " ---");

            System.out.println("Unos klijenta");
            Client bookingClient = (Client) BookingInputHandler.inputPersonBooking(this.unos, this.clients, "klijenta");

            System.out.println("Unos zaposlenika");
            Employee bookingEmployee = (Employee) BookingInputHandler.inputPersonBooking(this.unos, this.employees, "zaposlenika");

            System.out.println("Unos automobila");
            Car bookingCar = BookingInputHandler.inputCarBooking(this.unos, this.cars);

            System.out.println("Unos podataka za rezervaciju");
            this.bookings[i] = BookingInputHandler.inputBooking(this.unos, bookingClient, bookingEmployee, bookingCar);

            //this.records[i] = new RecordStorage(this.bookings[i], LocalDate.now());
            this.storage.addPermanentRecord(this.bookings[i]);
        }
    }
}
