package utilities.menus;

import core.people.Client;
import core.people.Employee;
import core.people.Person;
import core.people.PersonUtils;
import core.vehicles.Car;
import core.vehicles.CarFleetRepository;

import java.util.*;
import java.util.stream.Collectors;


/**
 * The type Search menu.
 */
public class SearchMenu {
    /*private int selectedNumber;
    private Client[] clients;
    private Employee[] employees;
    private Car[] vars;

    public SearchMenu(int selectedNumber, Client[] clients, Employee[] employees, Car[] vars) {
        this.selectedNumber = selectedNumber;
        this.clients = clients;
        this.employees = employees;
        this.vars = vars;
    }

    public int getSelectedNumber() {
        return selectedNumber;
    }

    public void setSelectedNumber(int selectedNumber) {
        this.selectedNumber = selectedNumber;
    }

    public Client[] getClients() {
        return clients;
    }

    public void setClients(Client[] clients) {
        this.clients = clients;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public Car[] getVars() {
        return vars;
    }

    public void setVars(Car[] vars) {
        this.vars = vars;
    }*/

    /**
     * Start menu int.
     *
     * @param scanner the scanner
     * @return the int
     */
    public static int startMenu(Scanner scanner){
        System.out.println("Pretraživanje podataka prema zadanim kriterijima: ");
        System.out.println("Za pretraživanje  korisnika odaberite ----------------- 1: ");
        System.out.println("Za pretraživanje  zaposlenika odaberite --------------- 2: ");
        System.out.println("Za pretraživanje branda automobila odaberite ---------- 3: ");

        int selectedMenu = scanner.nextInt();
        scanner.nextLine();

        return selectedMenu;
    }

    /**
     * Select search menu.
     *
     * @param scanner the scanner
     * @param people the people
     * @param fleetRepository the fleet repository
     */
    public static void selectSearchMenu(Scanner scanner,
                                        List<Person> people,
                                        CarFleetRepository fleetRepository){
        boolean correctNumber = false;
        do{
            int selectedMenu = startMenu(scanner);

            switch (selectedMenu){
                case 1-> {
                    correctNumber = true;
                    int youngest = 0;

                    List<Client> clients = people.stream()
                            .filter(p -> p instanceof Client)
                            .map(p -> (Client) p)
                            .collect(Collectors.toList());

                    System.out.println("Odaberite operaciju pretraživanja klijenata");
                    do{
                        System.out.println("Za pretraživanje najmlađeg 1, a za najstarijeg 0: ");
                        youngest = scanner.nextInt();
                    }while(youngest != 0 && youngest != 1);

                    if(youngest==1){
                        Optional<? extends Client> youngestClient = PersonUtils.youngestPerson(clients);
                        youngestClient.ifPresent(client -> {
                            System.out.println("Najmlađa osoba je "+ client.getName() + " - " + client.getDateOfBirth());
                        });
                        if (youngestClient.isEmpty()) {
                            System.out.println("Nema klijenata u sustavu.");
                        }
                    }
                    else{
                        Optional<? extends Client> oldestClient = PersonUtils.oldestPerson(clients);
                        oldestClient.ifPresentOrElse(
                                // AKO JE KLIENT PRISUTAN (isPresent)
                                oldest -> {
                                    System.out.println("Najstarija osoba je " + oldest.getName() + " - " + oldest.getDateOfBirth());
                                },
                                // AKO NIJE PRISUTAN (isEmpty)
                                () -> {
                                    System.out.println("Nema klijenata u sustavu za pretragu.");
                                }
                        );
                    }
                }
                case 2 -> {
                    correctNumber = true;
                    int youngest = 0;

                    List<Employee> employees = people.stream()
                            .filter(p -> p instanceof Employee)
                            .map(p -> (Employee) p)
                            .collect(Collectors.toList());
                    System.out.println("Odaberite operaciju pretraživanja zaposlenika");
                    do{
                        System.out.println("Za pretraživanje najmlađeg 1, a za najstarijeg 0: ");
                        youngest = scanner.nextInt();
                    }while(youngest != 0 && youngest != 1);

                    if(youngest==1){
                        Optional<? extends Employee> youngestEmployee = PersonUtils.youngestPerson(employees);

                        youngestEmployee.ifPresentOrElse(
                                // AKO JE ZAPOSLENIK PRISUTAN
                                youngestE -> {
                                    System.out.println("Najmlađa osoba je " + youngestE.getName() + " - " + youngestE.getDateOfBirth());
                                },
                                () -> {
                                    System.out.println("Nema zaposlenika u sustavu za pretragu.");
                                }
                        );
                    }
                    else{
                        Optional<? extends Employee> oldestEmployee = PersonUtils.oldestPerson(employees);
                        oldestEmployee.ifPresentOrElse(
                                oldestE -> {
                                    System.out.println("Najstarija osoba je " + oldestE.getName() + " - " + oldestE.getDateOfBirth());
                                },
                                () -> {
                                    System.out.println("Nema zaposlenika u sustavu za pretragu.");
                                }
                        );
                    }
                }
                case 3 -> {
                    List<Car> foundCars = new ArrayList<>();
                    do{
                        System.out.println("Odaberite željenu marku automobila:");
                        String carBrandModelName = scanner.nextLine();
                        fleetRepository.printAllBrandsModels();

                        foundCars = (fleetRepository.findByBrandModel(carBrandModelName));

                        if(foundCars.isEmpty()){
                            System.out.println("Na lageru nemamo to vozilo!");
                        }else{
                            System.out.println("Pronađeni automobili za upit: "+ carBrandModelName);
                            for (Car car : foundCars){
                                car.getFullDescription();
                            }
                        }

                    }while(foundCars.isEmpty());
                }
                default -> {
                    System.out.println("Uneseni broj je neispravan");
                }
            }
        }while (!correctNumber);
    }
}
