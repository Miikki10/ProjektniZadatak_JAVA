package services;

import entities.exceptions.InvalidBookingDateException;

import java.io.IOException;

/**
 * The interface Booking system services.
 */
public interface BookingSystemServices {
    /**
     * Make booking.
     *
     * @throws InvalidBookingDateException the invalid booking date exception
     * @throws IOException                 the io exception
     */
    public void makeBooking() throws InvalidBookingDateException, IOException;

}
