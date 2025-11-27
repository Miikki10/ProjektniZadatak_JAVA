package core.people;

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
     * @param <T>    the type parameter
     * @param people the people
     * @return the person
     */
    public static <T extends Person> Optional<? extends T> youngestPerson(Map<Integer,? extends T> people){
        if (people == null || people.isEmpty()) {
            return Optional.empty();
        }

        return people.values()
                .stream()
                .max(Comparator.comparing(Person::getDateOfBirth));
        /*Arrays.sort(people, (p1, p2) -> p1.getDateOfBirth().compareTo(p2.getDateOfBirth()));
        return people[people.length-1];//najveći datum - najmlađa osoba*/
    }

    /**
     * Oldest person person.
     *
     * @param <T>    the type parameter
     * @param people the people
     * @return the person
     */
    public static <T extends Person> Optional<? extends T> oldestPerson(Map<Integer, ? extends T> people) {
        if (people == null) {
            return Optional.empty();
        }

        return people.values()
                .stream()
                .min(Comparator.comparing(Person::getDateOfBirth));
    }

}
