package utilities.input;

import entities.people.Client;
import entities.people.Employee;
import entities.vehicles.Car;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import static entities.dates.DateUtils.inputLocalDate;

public class InputHandler {


    public static Client inputClient(Scanner scanner){
        System.out.println("Unesi klijenta: ");

        System.out.println("Ime: ");
        String name = scanner.nextLine();

        System.out.println("Email: ");
        String email = scanner.nextLine();

        System.out.println("Unos datuma rođenja: ");
        LocalDate dateOfBirth = inputLocalDate(scanner);

        return new Client(name, email, dateOfBirth);
    }

    public static Employee inputEmployee(Scanner scanner){
        System.out.println("Unesi zaposlenika: ");
        System.out.println("Name: ");
        String name = scanner.nextLine();

        System.out.println("Email: ");
        String email = scanner.nextLine();

        System.out.println("Unos datuma rođenja: ");
        LocalDate dateOfBirth = inputLocalDate(scanner);

        return new Employee(name, email, dateOfBirth);
    }

    public static Car inputCar(Scanner scanner){
        System.out.println("Unesi vozilo: ");

        System.out.println("Brand: ");
        String brand = scanner.nextLine();

        System.out.println("Model: ");
        String model = scanner.nextLine();

        System.out.println("Godina: ");
        Integer godina = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Cijena po danu: ");
        BigDecimal pricePerDay = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.println("Status dostupnosti vozila (true/false): ");
        boolean available = scanner.nextBoolean();
        scanner.nextLine();

        return new Car.Builder()
                .setBrand(brand)
                .setModel(model)
                .setYear(godina)
                .setPricePerDay(pricePerDay)
                .setAvailable(available)
                .build();
    }
}
