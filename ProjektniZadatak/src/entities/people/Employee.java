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

}
