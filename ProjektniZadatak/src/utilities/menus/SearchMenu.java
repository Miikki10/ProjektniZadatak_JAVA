package utilities.menus;

import core.people.Client;
import core.people.Employee;
import core.people.Person;
import core.people.PersonUtils;
import core.vehicles.Car;
import core.vehicles.CarFleetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


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
     * @param scanner         the scanner
     * @param clients         the clients
     * @param employees       the employees
     * @param cars            the cars
     * @param fleetRepository the fleet repository
     */
    public static void selectSearchMenu(Scanner scanner,
                                        Map<Integer, Client> clients,
                                        Map<Integer, Employee> employees,
                                        Map<Integer, Car> cars,
                                        CarFleetRepository fleetRepository){
        boolean correctNumber = false;
        do{
            int selectedMenu = startMenu(scanner);

            switch (selectedMenu){
                case 1-> {
                    correctNumber = true;
                    int youngest = 0;

                    System.out.println("Odaberite operaciju pretraživanja klijenata");
                    do{
                        System.out.println("Za pretraživanje najmlađeg 1, a za najstarijeg 0: ");
                        youngest = scanner.nextInt();
                    }while(youngest != 0 && youngest != 1);

                    if(youngest==1){
                        Person youngestClient = PersonUtils.youngestPerson(clients);
                        System.out.println("Najmlađa osoba je "+ youngestClient.getName() + " - " + youngestClient.getDateOfBirth());
                    }
                    else{
                        Person oldestClient = PersonUtils.oldestPerson(clients);
                        System.out.println("Najstarija osoba je "+ oldestClient.getName() + " - " + oldestClient.getDateOfBirth());
                    }
                }
                case 2 -> {
                    correctNumber = true;
                    int youngest = 0;
                    System.out.println("Odaberite operaciju pretraživanja zaposlenika");
                    do{
                        System.out.println("Za pretraživanje najmlađeg 1, a za najstarijeg 0: ");
                        youngest = scanner.nextInt();
                    }while(youngest != 0 && youngest != 1);

                    if(youngest==1){
                        Person youngestEmployee = PersonUtils.youngestPerson(clients);
                        System.out.println("Najmlađa osoba je "+ youngestEmployee.getName() + " - " + youngestEmployee.getDateOfBirth());
                    }
                    else{
                        Person oldestEmployee = PersonUtils.oldestPerson(clients);
                        System.out.println("Najstarija osoba je "+ oldestEmployee.getName() + " - " + oldestEmployee.getDateOfBirth());
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
