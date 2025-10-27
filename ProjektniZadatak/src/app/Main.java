package app;


import core.RentACarSystem;

public class Main{

    static void main(String[] args){

        RentACarSystem app = new RentACarSystem(5);
        app.initializeData(5);

        app.startBooking();

        app.startSearchMenu();
    }
}