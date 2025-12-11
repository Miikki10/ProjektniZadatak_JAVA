package core.people;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Map;

/**
 * The type Person utils.
 */
public class PersonUtils {
    /**
     * Finds the youngest person in a list.
     *
     * @param <T>     The type of Person.
     * @param people The list of people to search through.
     * @return An Optional containing the youngest person, or an empty Optional if the list is null or empty.
     */
    public static <T extends Person> Optional<T> youngestPerson(List<T> people){
        if (people == null || people.isEmpty()) {
            return Optional.empty();
        }
        // The youngest person has the latest (maximum) date of birth.
        return people.stream().max(Comparator.comparing(Person::getDateOfBirth));
    }

    /**
     * Finds the youngest person in a map of people.
     *
     * @param <T>    The type of Person.
     * @param people The map of people to search through. The values of the map are the people.
     * @return An Optional containing the youngest person, or an empty Optional if the map is null or empty.
     */
    public static <T extends Person> Optional<T> youngestPerson(Map<Integer, ? extends T> people) {
        if (people == null || people.isEmpty()) {
            return Optional.empty();
        }
        return youngestPerson(new ArrayList<>(people.values()));
    }

    /**
     * Finds the oldest person in a list.
     *
     * @param <T>     The type of Person.
     * @param people The list of people to search through.
     * @return An Optional containing the oldest person, or an empty Optional if the list is null or empty.
     */
    public static <T extends Person> Optional<T> oldestPerson(List<T> people) {
        if (people == null || people.isEmpty()) {
            return Optional.empty();
        }
        // The oldest person has the earliest (minimum) date of birth.
        return people.stream().min(Comparator.comparing(Person::getDateOfBirth));
    }

}
