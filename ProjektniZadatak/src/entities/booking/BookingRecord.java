package entities.booking;

import java.time.LocalDate;

public record BookingRecord(
        Integer recordId,
        Booking booking,
        LocalDate creationDate
    ) {
}
