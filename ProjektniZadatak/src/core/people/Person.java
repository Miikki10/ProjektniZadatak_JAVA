package core.people;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Person.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "role")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Client.class, name = "Client"),
    @JsonSubTypes.Type(value = Employee.class, name = "Employee")
})
public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private static Integer nextId = 1;

    private Integer id;
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
        this.id = nextId++;
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Instantiates a new Person with a specific ID.
     * This constructor is useful for deserialization from JSON.
     *
     * @param id          the id
     * @param name        the name
     * @param email       the email
     * @param dateOfBirth the date of birth
     */
    @JsonCreator
    public Person(@JsonProperty("id") Integer id, @JsonProperty("name") String name, @JsonProperty("email") String email, @JsonProperty("dateOfBirth") LocalDate dateOfBirth) {
        this.id = id;
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

    /**
     * Updates the nextId to be one greater than the provided id if it's higher.
     * This should be called after loading people from a persistent source.
     *
     * @param id the id from a loaded person
     */
    public static void updateNextId(Integer id) {
        if (id >= nextId) {
            nextId = id + 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s (ID: %d, Role: %s)", name, id, getRole());
    }
}
