package app;


import core.RentACarSystem;
import utilities.input.BookingInputHandler;
import utilities.input.InputHandler;
import utilities.menus.SearchMenu;
import entities.people.Client;
import entities.people.Employee;
import entities.records.Records;
import entities.vehicles.Booking;
import entities.vehicles.Car;


import java.time.LocalDate;
import java.util.Scanner;

public class Main{

    static void main(String[] args){

        /*Scanner unos = new Scanner(System.in);
        Client[] clients = new Client[5];
        Car[] cars = new Car[5];
        Employee[] employees = new Employee[5];
        Booking[] bookings = new Booking[5];
        Records[] records = new Records[5];
        System.out.println("--------------Unos podataka------------");
        for(int i = 0; i<5; i++){
            //System.out.println("Unesi " + (i+1) + ". klijenta: ");
            clients[i] = InputHandler.inputClient(unos);

            //System.out.println("Unesi " + (i+1) + ". zaposlenika: ");
            employees[i] = InputHandler.inputEmployee(unos);

            //System.out.println("Unesi " + (i+1) +". vozilo");
            cars[i] = InputHandler.inputCar(unos);
        }*/

        RentACarSystem app = new RentACarSystem(5);
        app.initializeData(5);

        app.startBooking();
        //pretvori OVO U FUNKCIJE!!!!!!!!!!!!!!!!!!!!!!
        /*System.out.println("NAPRAVI REZERVACIJU PREMA POSTOJECIM PODATCIMA");
        for(int i=0; i<5;i++){
            System.out.println("Unos klijenata");
            Client bookingClient = (Client) BookingInputHandler.inputPersonBooking(unos, clients, "klijenta");
            System.out.println("Unos zaposlenika");
            Employee bookingEmployee = (Employee) BookingInputHandler.inputPersonBooking(unos, employees, "zaposlenika");
            System.out.println("Unos automobila");
            Car bookingCar = BookingInputHandler.inputCarBooking(unos, cars);
            System.out.println("Unos podataka za rezervaciju");
            bookings[i] = BookingInputHandler.inputBooking(unos, bookingClient, bookingEmployee, bookingCar);
            records[i] = new Records(bookings[i], LocalDate.now());
        }*/



        //SearchMenu.selectSearchMenu(unos, clients, employees, cars);
        app.startSearchMenu();

        /*System.out.println("Pretraživanje podataka prema zadanim kriterijima: ");
        System.out.println("Za pretraživanje  korisnika odaberite ----------------- 1: ");
        System.out.println("Za pretraživanje  zaposlenika odaberite --------------- 2: ");
        System.out.println("Za pretraživanje branda automobila odaberite ---------- 3: ");*/

        //selectSearchMenu(unos, clients, employees, cars);

    }
    /*private static void selectSearchMenu(Scanner scanner, Client[] clients, Employee[] employees, Car[] cars){
        boolean correctNumber = false;
        do{
            Integer selectedNumber = scanner.nextInt();
            scanner.nextLine();

            switch (selectedNumber){
                case 1 -> {
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
                default ->{
                    System.out.println("Uneseni broj je neispravan");
                }
            }
        }while (!correctNumber);
    }*/

    /*private static Booking inputBooking(Scanner scanner, Client client, Employee employee, Car car){
        System.out.println("Id: ");
        Integer id = scanner.nextInt();

        System.out.println("Unos datuma početka rezervacije: ");
        LocalDate dateStartBooking = inputLocalDate(scanner);

        System.out.println("Unos datuma završetka rezervacije: ");
        LocalDate dateEndBooking = inputLocalDate(scanner);

        long daysBetween = ChronoUnit.DAYS.between(dateStartBooking, dateEndBooking);
        BigDecimal daysBetweenParsed = new BigDecimal(daysBetween);//radi računanja sa price per day(BigDecimal i Integer ne mogu izvoditi zajedničke operacije)
        BigDecimal totalBookingPrice = daysBetweenParsed.multiply(car.getPricePerDay());

        return new Booking(client, employee, car, dateStartBooking, dateEndBooking, totalBookingPrice);
    }

    private static LocalDate inputLocalDate(Scanner scanner){
        LocalDate date = null;


        do{
            System.out.println("Unesite datum formata (DD.MM.YYYY): ");
            String dateString = scanner.nextLine();
            try{
                date = LocalDate.parse(dateString);
            }
            catch (DateTimeException e){
                System.out.println("Unijeli ste neispravan format datuma!");
            }
        }while(date==null);

        return date;
    }*/

    /*private static Car inputCarBooking(Scanner scanner, Car[] cars){
        boolean carIsFound = false;
        Car foundCar = null;

        do{
            System.out.println("Unesite ID automobila: ");
            Integer idCarBooking = scanner.nextInt();
            scanner.nextLine(); // Čišćenje /n iz buffera
            for(int j=0;j<5;j++){
                if (idCarBooking.equals(cars[j].getId())) {
                    //carIsFound = true;
                    foundCar = cars[j];
                    return foundCar;
                }
            }
            if (!carIsFound) System.out.println("Uneseni automobil ne postoji!");
        }while(true);

        //return foundCar;
    }*/

    /*private static Person inputPersonBooking(Scanner scanner, Person[] persons, String roleName){
        boolean personIsFound =false;
        Person foundPerson = null;
        do{
            System.out.println("Unesite ID" + roleName + ": ");
            Integer idPersonBooking = scanner.nextInt();
            scanner.nextLine(); // Čišćenje /n iz buffera
            for(int j=0;j<5;j++){
                if (idPersonBooking.equals(persons[j].getId())) {
                    //personIsFound = true;
                    foundPerson = persons[j];
                    return foundPerson;
                }
            }
            if (!personIsFound) System.out.println("Uneseni korisnik ne postoji!");
        }while(true);

        //return foundPerson;
    }*/

    /*private static Car inputCar(Scanner scanner){
        System.out.println("Id: ");
        Integer id = scanner.nextInt();

        System.out.println("Brand: ");
        String brand = scanner.nextLine();

        System.out.println("Model: ");
        String model = scanner.nextLine();

        System.out.println("Godina: ");
        Integer godina = scanner.nextInt();

        System.out.println("Cijena po danu: ");
        BigDecimal pricePerDay = scanner.nextBigDecimal();

        System.out.println("Status dostupnosti vozila: ");
        boolean available = scanner.hasNext();

        return new Car.Builder()
                .setBrand(brand)
                .setModel(model)
                .setYear(godina)
                .setPricePerDay(pricePerDay)
                .setAvailable(available)
                .build();
    }*/

    /*private static Client inputClient(Scanner scanner){
        System.out.println("Ime: ");
        String name = scanner.nextLine();

        System.out.println("Email: ");
        String email = scanner.nextLine();

        System.out.println("Unos datuma rođenja: ");
        LocalDate dateOfBirth = inputLocalDate(scanner);

        return new Client(name, email, dateOfBirth);
    }*/

    /*private static Employee inputEmployee(Scanner scanner){
        System.out.println("Name: ");
        String name = scanner.nextLine();

        System.out.println("Email: ");
        String email = scanner.nextLine();

        System.out.println("Unos datuma rođenja: ");
        LocalDate dateOfBirth = inputLocalDate(scanner);

        return new Employee(name, email, dateOfBirth);
    }*/
}