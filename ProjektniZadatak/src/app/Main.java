package app;


import core.RentACarSystem;
import entities.exceptions.InvalidBookingDateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * The type Main.
 */
public class Main{

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Main.
     *
     * @param args the args
     */
    static void main(String[] args){

        logger.trace("Entering main method.");

        RentACarSystem app = new RentACarSystem(5);
        logger.debug("RentACarSystem initialized with capacity 5.");
        app.initializeData(5);
        logger.debug("Initialized data with 5 vehicles.");

        boolean bookingSuccessful = false;
        while(!bookingSuccessful){
            try{
                logger.info("Starting booking process...");
                app.startBooking();
                bookingSuccessful = true;
                logger.info("Bookings created successfully!");
            } catch (InvalidBookingDateException e){

                logger.warn("Error during date entry: {}", e.getMessage());
                logger.info("Please, try entering the booking information again.");

            }catch (IOException e){
                logger.error("Critical I/O error: {}", e.getMessage(), e);
                logger.error("The application will shut down due to an input error.");
                break;
            }catch (Exception e){
                logger.error("An unexpected error occurred: {}", e.getMessage(), e);
                break;
            }
        }

        logger.info("Starting search menu...");
        app.startSearchMenu();
        logger.info("Exiting default search menu");
        logger.info("Starting print of all available cars");
        app.startAvailableCarsMenu();
        logger.info("Starting print of all cars by brand");
        app.startCarBrandMenu();
        logger.info("Starting print of pairs of cars for inspection");
        app.startInspectionPairs();
        logger.info("Starting print of first and last added car");
        app.firstLastAddedCar();
        logger.trace("Exiting main method.");
    }
}