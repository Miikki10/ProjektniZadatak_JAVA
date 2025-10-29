package core;

import entities.people.Client;
import entities.people.Employee;
import entities.booking.RecordStorage;
import entities.booking.Booking;
import entities.vehicles.Car;
import services.BookingSystem;
import utilities.input.InputHandler;
import utilities.menus.SearchMenu;

import java.util.Scanner;

public class RentACarSystem {
    private static final int MAX_CAPACITY = 100;

    private Client[] clients = new Client[MAX_CAPACITY];
    private Employee[]employees = new Employee[MAX_CAPACITY];
    private Car[] cars = new Car[MAX_CAPACITY];
    private Booking[] bookings = new Booking[MAX_CAPACITY];
    private RecordStorage recordStorage;
    private Scanner unos = new Scanner(System.in);

    public RentACarSystem(int capacity){
        this.clients = new Client[capacity];
        this.employees = new Employee[capacity];
        this.cars = new Car[capacity];
        this.bookings = new Booking[capacity];
        this.recordStorage = new RecordStorage();
    }

    public void initializeData(int count){
        System.out.println("--------------Unos podataka------------");
        for(int i = 0; i<count; i++){
            clients[i] = InputHandler.inputClient(unos);

            employees[i] = InputHandler.inputEmployee(unos);

            cars[i] = InputHandler.inputCar(unos);
        }
    }

    int numberOfBookings = 5;

    public void startBooking(){
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

    public void startSearchMenu(){
        SearchMenu.selectSearchMenu(unos, clients, employees, cars);
    }
}
