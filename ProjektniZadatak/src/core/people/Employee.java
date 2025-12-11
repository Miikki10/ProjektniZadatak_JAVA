package core.people;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * The type Employee.
 */
public class Employee extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

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
