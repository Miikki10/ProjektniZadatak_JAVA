package entities.people;

import java.time.LocalDate;
import java.util.Scanner;

import static entities.dates.DateUtils.inputLocalDate;


public class Client extends Person {
    public Client(String name, String email, LocalDate dateOfBirth) {
        super(name, email, dateOfBirth);
    }

    @Override
    public String getRole(){
        return "Client";
    }

}
