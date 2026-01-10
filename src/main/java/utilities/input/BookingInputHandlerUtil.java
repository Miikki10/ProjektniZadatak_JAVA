package utilities.input;

import core.people.Client;
import core.people.Employee;
import core.people.Person;
import core.booking.Booking;
import core.booking.InvalidBookingDateException;
import core.vehicles.Car;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Scanner;

import static utilities.DateUtils.inputLocalDate;

/**
 * The type Booking input handler.
 */
public class BookingInputHandlerUtil {
    /**
     * Input person booking person.
     *
     * @param <T>      the type parameter
     * @param scanner  the scanner
     * @param persons  the persons
     * @param roleName the role name
     * @return the person
     */
    public static <T extends Person>T inputPersonBooking(Scanner scanner, Map<Integer, T> persons, String roleName) {
        /**
         * Ispis svih postojećih klijenata i njihovih ID
         * Korisnik upisuje SAMO ID!!!!!!!!!
         */
        do {
            System.out.println("-----------ISPIS SVIH KLIJENATA----------------");
            for (Map.Entry<Integer, T> entry: persons.entrySet()){
                Integer id = entry.getKey();
                Person person = entry.getValue();

                System.out.println("ID: "+id+" - " + person.getName());
            }

            System.out.println("Unesite ID željenog klijenta: ");

            if(!scanner.hasNextInt()){
                System.out.println("Neispravan unos. Molimo unesite cijeli broj (ID).");
                scanner.next();
                continue;
            }

            int selectedId = scanner.nextInt();

            Person bookingPerson;
            bookingPerson = persons.get(selectedId);

            if (bookingPerson == null) {
                System.out.println("Klijent s tim ID-em ne postoji. Pokušajte ponovo.");
                continue;
            }

            System.out.println("Odabran klijent sa ID: " + bookingPerson.getId() + " - " + bookingPerson.getName());

        } while (true);
    }

    /**
     * Input car booking car.
     *
     * @param scanner the scanner
     * @param cars    the cars
     * @return the car
     */
    public static Car inputCarBooking(Scanner scanner, Map<Integer, Car> cars){

        /**
         * Ispis svih postojećih automobila i njihovih ID
         * Korisnik upisuje SAMO ID!!!!!!!!!
         */
        do{

            System.out.println("----------ISPIS SVIH AUTOMOBILA---------------");
            for(Map.Entry<Integer, Car> entry : cars.entrySet()){
                Integer carId = entry.getKey();
                Car car = entry.getValue();

                System.out.println("ID: " + carId + " - " + car.getCarBrandModel());
            }

            System.out.println("Unesite ID željenog automobila: ");

            if(!scanner.hasNextInt()){
                System.out.println("Neispravan unos. Molimo unesite cijeli broj (ID).");
                scanner.next();
                continue;
            }

            int selectedId = scanner.nextInt();

            Car bookingCar;
            bookingCar = cars.get(selectedId);

            if(bookingCar == null){
                System.out.println("Klijent s tim ID-em ne postoji. Pokušajte ponovo.");
                continue;
            }

            System.out.println("Odabrani automobil je ID: " + bookingCar.getId() + " - " + bookingCar.getCarBrandModel());

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
