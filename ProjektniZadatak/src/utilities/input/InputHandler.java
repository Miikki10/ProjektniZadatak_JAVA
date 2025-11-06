package utilities.input;

import entities.exceptions.InvalidVehicleDataException;
import entities.people.Client;
import entities.people.Employee;
import entities.vehicles.Car;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.InputMismatchException;

import static entities.dates.DateUtils.inputLocalDate;

/**
 * The type Input handler.
 */
public class InputHandler {

    /**
     * Input client client.
     *
     * @param scanner the scanner
     * @return the client
     */
    public static Client inputClient(Scanner scanner){
        System.out.println("Unesi klijenta: ");
        PersonInputData data = inputPersonData(scanner);
        return new Client(data.name(), data.email(), data.dateOfBirth());
    }

    /**
     * Input employee employee.
     *
     * @param scanner the scanner
     * @return the employee
     */
    public static Employee inputEmployee(Scanner scanner){
        System.out.println("Unesi zaposlenika: ");
        PersonInputData data = inputPersonData(scanner);
        return new Employee(data.name(), data.email(), data.dateOfBirth());
    }


    private static PersonInputData inputPersonData(Scanner scanner) {
        System.out.println("Ime: ");
        String name = scanner.nextLine();

        System.out.println("Email: ");
        String email = scanner.nextLine();

        System.out.println("Unos datuma rođenja: ");
        LocalDate dateOfBirth = inputLocalDate(scanner);
        return new PersonInputData(name, email, dateOfBirth);
    }


    private record PersonInputData(String name, String email, LocalDate dateOfBirth) {}

    /**
     * Input car car.
     *
     * @param scanner the scanner
     * @return the car
     */
    public static Car inputCar(Scanner scanner){
        while(true){
            try{
                System.out.println("Unesi vozilo: ");

                System.out.println("Brand: ");
                String brand = scanner.nextLine();

                System.out.println("Model: ");
                String model = scanner.nextLine();

                System.out.println("Registracija: ");
                String registration = scanner.nextLine();

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
                        .setRegistration(registration)
                        .setYear(godina)
                        .setPricePerDay(pricePerDay)
                        .setAvailable(available)
                        .build();
            }catch (InvalidVehicleDataException e){
                System.out.println("Greška u podacima: " + e.getMessage() + " Molimo, pokušajte ponovno.\n");
            }catch (InputMismatchException e){
                System.out.println("Greška pri unosu: Unijeli ste tekstualnu vrijednost umjesto broja ili logičke vrijednosti (true/false). Molimo, pokušajte ponovno.\n");
                scanner.nextLine(); // Jako važno: čisti neispravan unos iz scanner-a!
            }
        }
    }
}
