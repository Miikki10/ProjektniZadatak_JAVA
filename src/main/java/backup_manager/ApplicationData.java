package backup_manager;

import core.booking.Booking;
import core.people.Person;
import core.vehicles.Car;

import java.io.Serializable;
import java.util.List;

/**
 * A container class to hold all application data for easy serialization.
 */
public class ApplicationData implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<Person> people;
    private final List<Car> cars;
    private final List<Booking> bookings;

    public ApplicationData(List<Person> people, List<Car> cars, List<Booking> bookings) {
        this.people = people;
        this.cars = cars;
        this.bookings = bookings;
    }

    public List<Person> getPeople() {
        return people;
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}