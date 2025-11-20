package services;

import core.booking.InvalidBookingDateException;

import java.io.IOException;

/**
 * The interface Booking system services.
 */
public interface BookingService {
    /**
     * Make booking.
     *
     * @throws InvalidBookingDateException the invalid booking date exception
     * @throws IOException                 the io exception
     */
    public void makeBooking() throws InvalidBookingDateException, IOException;

}
