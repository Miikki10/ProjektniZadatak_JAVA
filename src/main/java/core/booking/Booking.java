package core.booking;

import core.people.Client;
import core.people.Employee;
import core.vehicles.Car;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The type Booking.
 */
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

    private static Integer nextId = 1;

    private final Integer id;
    private Client client;
    private Employee employee;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalPrice;

    private BookingStatusEnum.BookingStatus bookingStatus;


    /**
     * Instantiates a new Booking.
     *
     * @param client     the client
     * @param employee   the employee
     * @param car        the car
     * @param startDate  the start date
     * @param endDate    the end date
     * @param totalPrice the total price
     */
    public Booking(Client client, Employee employee, Car car, LocalDate startDate, LocalDate endDate, BigDecimal totalPrice) {
        this.id = nextId++;
        this.client = client;
        this.employee = employee;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;

        this.bookingStatus = BookingStatusEnum.BookingStatus.PENDING;
    }

    /**
     * Updates the nextId to be one greater than the provided id if it's higher.
     * This should be called after loading data from a persistent source.
     *
     * @param id the id from a loaded booking
     */
    public static void updateNextId(Integer id) {
        if (id >= nextId) {
            nextId = id + 1;
        }
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets client.
     *
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Sets client.
     *
     * @param client the client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Gets employee.
     *
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets employee.
     *
     * @param employee the employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Gets car.
     *
     * @return the car
     */
    public Car getCar() {
        return car;
    }

    /**
     * Sets car.
     *
     * @param car the car
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets total price.
     *
     * @return the total price
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets total price.
     *
     * @param totalPrice the total price
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

}
