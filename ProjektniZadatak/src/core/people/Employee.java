package core.people;

import java.time.LocalDate;


/**
 * The type Employee.
 */
public class Employee extends Person {

    @Override
    public String getRole(){
        return "Employee";
    }

    /**
     * Instantiates a new Employee.
     *
     * @param name        the name
     * @param email       the email
     * @param dateOfBirth the date of birth
     */
    public Employee(String name, String email, LocalDate dateOfBirth) {
        super(name, email, dateOfBirth);
    }

}
