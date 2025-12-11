package services;

import core.booking.BuildBookingRecord;
import core.booking.Booking;
import core.booking.InvalidBookingDateException;
import core.people.Client;
import core.people.Employee;
import core.people.Person;
import core.vehicles.Car;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * The type Booking system.
 */
public class RentVehicleBookingService implements BookingService {
    private final List<Person> people;
    private final List<Car> cars;
    private final List<Booking> bookings;
    private final BuildBookingRecord storage;
    private final int numberOfBookings;
    //private final Scanner unos = new Scanner(System.in);
    private final Scanner unos;

    /**
     * Instantiates a new Booking system.
     *
     * @param scanner          the scanner
     * @param numberOfBookings the number of bookings
     * @param people           the people
     * @param cars             the cars
     * @param bookings         the bookings
     * @param storage          the storage
     */
    public RentVehicleBookingService(Scanner scanner,
                                     int numberOfBookings,
                                     List<Person> people,
                                     List<Car> cars,
                                     List<Booking> bookings,
                                     BuildBookingRecord storage) {
        this.unos = scanner;
        this.numberOfBookings = numberOfBookings;
        this.people = people;
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
            Client bookingClient = inputClientBooking(this.unos, this.people);

            System.out.println("Unos zaposlenika");
            Employee bookingEmployee = inputEmployeeBooking(this.unos, this.people);

            System.out.println("Unos automobila");
            Car bookingCar = inputCarBooking(this.unos, this.cars);

            System.out.println("Unos podataka za rezervaciju");
            Booking makeNewBooking = inputBooking(this.unos, bookingClient, bookingEmployee, bookingCar);
            this.bookings.add(makeNewBooking);

            this.storage.addPermanentRecord(makeNewBooking);
        }
    }

    private Client inputClientBooking(Scanner scanner, List<Person> people) {
        while (true) {
            System.out.print("Unesite ID klijenta: ");
            String line = scanner.nextLine();
            try {
                int id = Integer.parseInt(line);
                Optional<Person> person = people.stream().filter(p -> p.getId().equals(id) && p instanceof Client).findFirst();
                if (person.isPresent()) {
                    return (Client) person.get();
                } else {
                    System.out.println("Klijent s tim ID-om ne postoji. Pokušajte ponovno.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Neispravan unos. Molimo unesite broj.");
            }
        }
    }

    private Employee inputEmployeeBooking(Scanner scanner, List<Person> people) {
        while (true) {
            System.out.print("Unesite ID zaposlenika: ");
            String line = scanner.nextLine();
            try {
                int id = Integer.parseInt(line);
                Optional<Person> person = people.stream().filter(p -> p.getId().equals(id) && p instanceof Employee).findFirst();
                if (person.isPresent()) {
                    return (Employee) person.get();
                } else {
                    System.out.println("Zaposlenik s tim ID-om ne postoji. Pokušajte ponovno.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Neispravan unos. Molimo unesite broj.");
            }
        }
    }

    private Car inputCarBooking(Scanner scanner, List<Car> cars) {
        while (true) {
            System.out.print("Unesite ID automobila: ");
            String line = scanner.nextLine();
            try {
                int id = Integer.parseInt(line);
                Optional<Car> car = cars.stream().filter(c -> c.getId().equals(id)).findFirst();
                if (car.isPresent()) {
                    return car.get();
                } else {
                    System.out.println("Automobil s tim ID-om ne postoji. Pokušajte ponovno.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Neispravan unos. Molimo unesite broj.");
            }
        }
    }

    private Booking inputBooking(Scanner scanner, Client client, Employee employee, Car car) throws InvalidBookingDateException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        LocalDate startDate;
        LocalDate endDate;

        while (true) {
            try {
                System.out.print("Unesite datum početka rezervacije (dd.MM.yyyy.): ");
                startDate = LocalDate.parse(scanner.nextLine(), formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Neispravan format datuma. Pokušajte ponovno.");
            }
        }

        while (true) {
            try {
                System.out.print("Unesite datum završetka rezervacije (dd.MM.yyyy.): ");
                endDate = LocalDate.parse(scanner.nextLine(), formatter);
                if (endDate.isBefore(startDate)) {
                    throw new InvalidBookingDateException("Datum završetka ne može biti prije datuma početka.");
                }
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Neispravan format datuma. Pokušajte ponovno.");
            } catch (InvalidBookingDateException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.print("Unesite ukupnu cijenu: ");
        BigDecimal totalPrice = new BigDecimal(scanner.nextLine());

        return new Booking(client, employee, car, startDate, endDate, totalPrice);
    }
}
