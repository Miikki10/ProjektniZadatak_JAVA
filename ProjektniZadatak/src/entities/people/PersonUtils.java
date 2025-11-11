package entities.people;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

/**
 * The type Person utils.
 */
public class PersonUtils {
    /**
     * Youngest person person.
     *
     * @param people the people
     * @return the person
     */
    public static <T extends Person>T youngestPerson(Map<Integer, T> people){
        if (people == null || people.isEmpty()) {
            return null;
        }

        Optional<T> youngestPerson= people.values()
                .stream()
                .max(Comparator.comparing(Person::getDateOfBirth));

        return youngestPerson.orElse(null);
        /*Arrays.sort(people, (p1, p2) -> p1.getDateOfBirth().compareTo(p2.getDateOfBirth()));
        return people[people.length-1];//najveći datum - najmlađa osoba*/
    }

    /**
     * Oldest person person.
     *
     * @param people the people
     * @return the person
     */
    public static <T extends Person>T oldestPerson(Map<Integer, T> people){
        if (people == null || people.isEmpty()) {
            return null;
        }

        Optional<T> oldestPerson = people.values()
                .stream()
                .min(Comparator.comparing(Person::getDateOfBirth));

        return oldestPerson.orElse(null);
        /*Arrays.sort(people, (p1, p2) -> p1.getDateOfBirth().compareTo(p2.getDateOfBirth()));
        return people[0];//najmanji datum - najstarija osoba*/
    }

}
