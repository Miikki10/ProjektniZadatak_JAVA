package entities.records;

import entities.vehicles.Booking;

import java.time.LocalDate;

public record BookingRecord(
        Integer recordId,
        Booking booking,
        LocalDate creationDate
    ) {
}
