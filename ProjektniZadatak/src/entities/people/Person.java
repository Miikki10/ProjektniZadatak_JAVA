package entities.people;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;

public abstract class Person {
    private static Integer nextId = 1;

    private final Integer id;
    private String name, email;
    private LocalDate dateOfBirth;

    public Person(String name, String email, LocalDate dateOfBirth) {
        this.id = nextId;
        nextId++;
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public abstract String getRole();
}
