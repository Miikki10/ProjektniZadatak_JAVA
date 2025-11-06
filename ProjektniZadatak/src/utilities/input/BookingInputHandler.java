package utilities.input;

import entities.people.Client;
import entities.people.Employee;
import entities.people.Person;
import entities.booking.Booking;
import entities.exceptions.InvalidBookingDateException;
import entities.vehicles.Car;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import static entities.dates.DateUtils.inputLocalDate;

/**
 * The type Booking input handler.
 */
public class BookingInputHandler {
    /**
     * Input person booking person.
     *
     * @param scanner  the scanner
     * @param persons  the persons
     * @param roleName the role name
     * @return the person
     */
    public static Person inputPersonBooking(Scanner scanner, Person[] persons, String roleName) {
        Person foundPerson = null;
        // Ponavljamo sve dok ne nađemo osobu
        do {
            System.out.println("Unesite IME i PREZIME " + roleName + ": ");
            // Koristimo nextLine() jer ime može sadržavati razmake
            String namePersonBooking = scanner.nextLine();

            // Prolazimo kroz cijelo polje
            // NOTE: Ovdje je riješena NullPointerException greška
            for (Person person : persons) {
                // 1. Provjera je li polje popunjeno (ako je null, preskačemo)
                // 2. Provjera imena (zanemarujemo velika/mala slova)
                if (person != null && namePersonBooking.equalsIgnoreCase(person.getName())) {
                    return person; // Odmah vraćamo pronađenu osobu
                }
            }

            // Ako petlja završi, a osoba nije pronađena
            System.out.println("Osoba s tim imenom ne postoji u bazi podataka!");

        } while (true);
    }

    /**
     * Input car booking car.
     *
     * @param scanner the scanner
     * @param cars    the cars
     * @return the car
     */
    public static Car inputCarBooking(Scanner scanner, Car[] cars){
        boolean carIsFound = false;
        Car foundCar = null;

        do{
            System.out.println("Unesite ID automobila: ");
            Integer idCarBooking = scanner.nextInt();
            scanner.nextLine(); // Čišćenje /n iz buffera
            for(int j=0;j<5;j++){
                if (idCarBooking.equals(cars[j].getId())) {
                    //carIsFound = true;
                    foundCar = cars[j];
                    return foundCar;
                }
            }
            if (!carIsFound) System.out.println("Uneseni automobil ne postoji!");
        }while(true);
    }

    /**
     * Input booking booking.
     *
     * @param scanner  the scanner
     * @param client   the client
     * @param employee the employee
     * @param car      the car
     * @return the booking
     * @throws InvalidBookingDateException the invalid booking date exception
     * @throws IOException                 the io exception
     */
    public static Booking inputBooking(Scanner scanner, Client client, Employee employee, Car car) throws InvalidBookingDateException, IOException {
        System.out.println("Unos datuma početka rezervacije: ");
        LocalDate dateStartBooking = inputLocalDate(scanner);

        System.out.println("Unos datuma završetka rezervacije: ");
        LocalDate dateEndBooking = inputLocalDate(scanner);

        // Provjera poslovne logike
        long daysBetween = ChronoUnit.DAYS.between(dateStartBooking, dateEndBooking);

        if (daysBetween < 0) {
            // Ako uvjet nije zadovoljen, bacamo našu specifičnu poslovnu iznimku
            throw new InvalidBookingDateException("Greška: Datum završetka ne može biti prije datuma početka!");
        }

        BigDecimal daysBetweenParsed = new BigDecimal(daysBetween);//radi računanja sa price per day(BigDecimal i Integer ne mogu izvoditi zajedničke operacije)
        BigDecimal totalBookingPrice = daysBetweenParsed.multiply(car.getPricePerDay());

        return new Booking(client, employee, car, dateStartBooking, dateEndBooking, totalBookingPrice);
    }
}
