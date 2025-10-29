package entities.booking;

import entities.people.Client;
import entities.people.Employee;
import entities.vehicles.Car;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Booking {
    private  static Integer nextId = 1;

    private final Integer id;
    private Client client;
    private Employee employee;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalPrice;

    public Booking(Client client, Employee employee, Car car, LocalDate startDate, LocalDate endDate, BigDecimal totalPrice) {
        this.id = nextId;
        nextId++;
        this.client = client;
        this.employee = employee;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

}
