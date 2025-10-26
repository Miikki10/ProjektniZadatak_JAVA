package utilities.menus;

import entities.people.Client;
import entities.people.Employee;
import entities.people.Person;
import entities.people.PersonUtils;
import entities.vehicles.Car;

import java.util.Scanner;


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

    public static int startMenu(Scanner scanner){
        System.out.println("Pretraživanje podataka prema zadanim kriterijima: ");
        System.out.println("Za pretraživanje  korisnika odaberite ----------------- 1: ");
        System.out.println("Za pretraživanje  zaposlenika odaberite --------------- 2: ");
        System.out.println("Za pretraživanje branda automobila odaberite ---------- 3: ");

        int selectedMenu = scanner.nextInt();
        scanner.nextLine();

        return selectedMenu;
    }

    public static void selectSearchMenu(Scanner scanner, Client[] clients, Employee[] employees, Car[] cars){
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
                    correctNumber = true;
                    boolean carBrandIsFound = false;
                    do{
                        System.out.println("Odaberite željenu marku automobila");
                        String carBrandName = scanner.nextLine();

                        for(int i = 0; i<5; i++){
                            if(carBrandName.equals(cars[i].getBrand())){
                                carBrandIsFound = true;
                                System.out.println(cars[i].getBrand() + " - " + cars[i].getModel() + " " + cars[i].getYear());
                            }
                        }
                        if(!carBrandIsFound){
                            System.out.println("Na lageru nemamo taj brand");
                        }
                    }while (!carBrandIsFound);
                }
                default -> {
                    System.out.println("Uneseni broj je neispravan");
                }
            }
        }while (!correctNumber);
    }
}
