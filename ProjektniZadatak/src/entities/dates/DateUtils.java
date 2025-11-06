package entities.dates;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * The type Date utils.
 */
public class DateUtils {
    /**
     * Input local date local date.
     *
     * @param scanner the scanner
     * @return the local date
     */
    public static LocalDate inputLocalDate(Scanner scanner){
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
    }
}
