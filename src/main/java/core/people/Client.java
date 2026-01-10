package core.people;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * The type Client.
 */
public class Client extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Client.
     *
     * @param name        the name
     * @param email       the email
     * @param dateOfBirth the date of birth
     */
    public Client(String name, String email, LocalDate dateOfBirth) {
        super(name, email, dateOfBirth);
    }

    @JsonCreator
    public Client(@JsonProperty("id") Integer id, @JsonProperty("name") String name, @JsonProperty("email") String email, @JsonProperty("dateOfBirth") LocalDate dateOfBirth) {
        super(id, name, email, dateOfBirth);
    }

    @Override
    @JsonProperty("role")
    public String getRole(){
        return "Client";
    }

}
