package services;

import entities.people.Client;
import entities.people.Employee;
import entities.records.Records;
import entities.vehicles.Booking;
import entities.vehicles.Car;
import utilities.input.BookingInputHandler;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.Scanner;

public class BookingSystem {
    private Client[] clients;
    private Employee[]employees;
    private Car[] cars;
    private Booking[] bookings;
    private Records[] records;
    private int numberOfBookings;
    private Scanner unos = new Scanner(System.in);

    public BookingSystem(Scanner scanner, int numberOfBookings, Client[] clients, Employee[] employees, Car[] cars, Booking[] bookings, Records[] records) {
        this.unos = scanner;
        this.numberOfBookings = numberOfBookings;
        this.clients = clients;
        this.employees = employees;
        this.cars = cars;
        this.bookings = bookings;
        this.records = records;
    }

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

            this.records[i] = new Records(this.bookings[i], LocalDate.now());
        }
    }
}
