package entities.people;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * The type Person.
 */
public abstract class Person {
    private static Integer nextId = 1;

    private final Integer id;
    private String name, email;
    private LocalDate dateOfBirth;

    /**
     * Instantiates a new Person.
     *
     * @param name        the name
     * @param email       the email
     * @param dateOfBirth the date of birth
     */
    public Person(String name, String email, LocalDate dateOfBirth) {
        this.id = nextId;
        nextId++;
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public abstract String getRole();
}
