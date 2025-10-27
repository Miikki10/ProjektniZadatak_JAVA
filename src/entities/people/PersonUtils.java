package entities.people;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class PersonUtils {
    public static Person youngestPerson(Person[] people){
        if (people == null || people.length == 0) {
            return null;
        }

        Arrays.sort(people, (p1, p2) -> p1.getDateOfBirth().compareTo(p2.getDateOfBirth()));
        return people[people.length-1];//najveći datum - najmlađa osoba
    }

    public static Person oldestPerson(Person[] people){
        if (people == null || people.length == 0) {
            return null;
        }

        Arrays.sort(people, (p1, p2) -> p1.getDateOfBirth().compareTo(p2.getDateOfBirth()));
        return people[0];//najmanji datum - najstarija osoba
    }

    /*public static LocalDate inputLocalDate(Scanner scanner){
        LocalDate date = null;

        final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        do{
            System.out.println("Unesite datum formata (DD.MM.YYYY): ");
            String dateString = scanner.nextLine();

            try{
                date = LocalDate.parse(dateString, DATE_FORMATTER);
            }
            catch (DateTimeException e){
                System.out.println("Unijeli ste neispravan format datuma!");
            }
        }while(date==null);

        return date;
    }*/

}
