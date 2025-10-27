package entities.vehicles;

import entities.people.Client;
import entities.people.Employee;
import entities.people.Person;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import static entities.dates.DateUtils.inputLocalDate;

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

    /*public static Person inputPersonBooking(Scanner scanner, Person[] persons, String roleName) {
        boolean personIsFound = false;
        Person foundPerson = null;
        do {
            System.out.println("Unesite ID" + roleName + ": ");
            Integer idPersonBooking = scanner.nextInt();
            scanner.nextLine(); // Čišćenje /n iz buffera
            for (int j = 0; j < 5; j++) {
                if (idPersonBooking.equals(persons[j].getId())) {
                    //personIsFound = true;
                    foundPerson = persons[j];
                    return foundPerson;
                }
            }
            if (!personIsFound) System.out.println("Uneseni korisnik ne postoji!");
        } while (true);
    }*/

    /*public static Car inputCarBooking(Scanner scanner, Car[] cars){
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
    }*/

    /*public static Booking inputBooking(Scanner scanner, Client client, Employee employee, Car car){
        System.out.println("Unos datuma početka rezervacije: ");
        LocalDate dateStartBooking = inputLocalDate(scanner);

        System.out.println("Unos datuma završetka rezervacije: ");
        LocalDate dateEndBooking = inputLocalDate(scanner);

        long daysBetween = ChronoUnit.DAYS.between(dateStartBooking, dateEndBooking);
        BigDecimal daysBetweenParsed = new BigDecimal(daysBetween);//radi računanja sa price per day(BigDecimal i Integer ne mogu izvoditi zajedničke operacije)
        BigDecimal totalBookingPrice = daysBetweenParsed.multiply(car.getPricePerDay());

        return new Booking(client, employee, car, dateStartBooking, dateEndBooking, totalBookingPrice);
    }*/
}
