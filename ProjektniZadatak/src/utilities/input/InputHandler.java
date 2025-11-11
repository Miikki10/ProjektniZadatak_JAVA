package utilities.input;

import entities.exceptions.InvalidVehicleDataException;
import entities.people.Client;
import entities.people.Employee;
import entities.vehicles.Car;
import entities.vehicles.CarFleetRepository;

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
    public static Car inputCar(Scanner scanner, CarFleetRepository fleetRepository){
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

                String carColor = null;
                int passengerCapacity = 0;
                while(true){
                    System.out.println("Želite li unijeti boju vozila? (true/false): ");

                    // Provjera je li sljedeći token boolean
                    if (scanner.hasNextBoolean()) {
                        boolean inputColor = scanner.nextBoolean();
                        scanner.nextLine(); // Potroši ostatak linije nakon booleana

                        if (inputColor) {
                            System.out.println("Unesite boju vozila: ");
                            carColor = scanner.nextLine();
                            // Provjera da boja nije prazna
                            if (!carColor.trim().isEmpty()) {
                                System.out.println("Boja uspješno unesena: " + carColor);
                                break; // Izlazi iz petlje nakon unosa boje
                            } else {
                                System.out.println("Boja ne može biti prazna. Pokušajte ponovo.");
                            }
                        } else {
                            System.out.println("Boja vozila nije unesena.");
                            break;
                        }
                    } else {
                        System.out.println("Neispravan unos. Molimo unesite 'true' ili 'false'.");
                        scanner.nextLine(); // Potroši neispravan unos i priprema za novi
                    }
                }

                while (true) {
                    System.out.println("Želite li unijeti kapacitet putnika? (true/false): ");

                    // Provjera je li sljedeći token boolean
                    if (scanner.hasNextBoolean()) {
                        boolean inputCapacity = scanner.nextBoolean();
                        scanner.nextLine(); // Potroši ostatak linije

                        if (inputCapacity) {
                            System.out.println("Unesite kapacitet putnika (cijeli broj): ");
                            // Provjera je li sljedeći unos cijeli broj
                            if (scanner.hasNextInt()) {
                                passengerCapacity = scanner.nextInt();
                                scanner.nextLine();

                                // Provjera da je kapacitet > 0
                                if (passengerCapacity > 0) {
                                    System.out.println("Kapacitet uspješno unesen: " + passengerCapacity);
                                    break;
                                } else {
                                    System.out.println("Kapacitet mora biti pozitivan broj. Pokušajte ponovo.");
                                }
                            } else {
                                System.out.println("Neispravan unos. Kapacitet mora biti cijeli broj.");
                                scanner.nextLine();
                            }
                        } else {
                            System.out.println("Kapacitet putnika nije unesen.");
                            break;
                        }
                    } else {
                        System.out.println("Neispravan unos. Molimo unesite 'true' ili 'false'.");
                        scanner.nextLine();
                    }
                }

                Car newCar = new Car.Builder()
                        .setBrand(brand)
                        .setModel(model)
                        .setRegistration(registration)
                        .setYear(godina)
                        .setPricePerDay(pricePerDay)
                        .setAvailable(available)
                        .setColor(carColor)
                        .setPassengerCapacity(passengerCapacity)
                        .build();

                fleetRepository.addCarToFleet(newCar);

                return newCar;

            }catch (InvalidVehicleDataException e){
                System.out.println("Greška u podacima: " + e.getMessage() + " Molimo, pokušajte ponovno.\n");
            }catch (InputMismatchException e){
                System.out.println("Greška pri unosu: Unijeli ste tekstualnu vrijednost umjesto broja ili logičke vrijednosti (true/false). Molimo, pokušajte ponovno.\n");
                scanner.nextLine(); // Jako važno: čisti neispravan unos iz scanner-a!
            }
        }
    }
}
