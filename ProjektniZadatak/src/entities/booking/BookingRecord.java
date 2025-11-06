package entities.booking;

import java.time.LocalDate;

/**
 * The type Booking record.
 */
public record BookingRecord(
        Integer recordId,
        Booking booking,
        LocalDate creationDate
    ) {
}
