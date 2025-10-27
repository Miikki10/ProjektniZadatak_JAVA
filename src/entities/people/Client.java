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

    /*public static Client inputClient(Scanner scanner){
        System.out.println("Ime: ");
        String name = scanner.nextLine();

        System.out.println("Email: ");
        String email = scanner.nextLine();

        System.out.println("Unos datuma rođenja: ");
        LocalDate dateOfBirth = inputLocalDate(scanner);

        return new Client(name, email, dateOfBirth);
    }*/

}
