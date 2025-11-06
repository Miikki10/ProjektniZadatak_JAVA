package entities.people;

import java.time.LocalDate;


/**
 * The type Client.
 */
public class Client extends Person {
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

    @Override
    public String getRole(){
        return "Client";
    }

}
