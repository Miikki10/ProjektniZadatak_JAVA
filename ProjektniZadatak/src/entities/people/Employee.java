package entities.people;

import java.time.LocalDate;
import java.util.Scanner;

import static entities.dates.DateUtils.inputLocalDate;


public class Employee extends Person {

    @Override
    public String getRole(){
        return "Employee";
    }

    public Employee(String name, String email, LocalDate dateOfBirth) {
        super(name, email, dateOfBirth);
    }

    /*public static Employee inputEmployee(Scanner scanner){
        System.out.println("Name: ");
        String name = scanner.nextLine();

        System.out.println("Email: ");
        String email = scanner.nextLine();

        System.out.println("Unos datuma rođenja: ");
        LocalDate dateOfBirth = inputLocalDate(scanner);

        return new Employee(name, email, dateOfBirth);
    }*/
}
