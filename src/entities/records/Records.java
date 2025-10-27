package entities.records;


import entities.vehicles.Booking;

import java.time.LocalDate;

public class Records {
    private static Integer nextId = 1;//static da pripada klasi a ne smao 1 instanci objekta

    private final Integer id;
    private Booking booking;
    private LocalDate recordDate;

    public Records(Booking booking, LocalDate recordDate) {
        this.id = nextId;
        nextId++;
        this.booking = booking;
        this.recordDate = recordDate;
    }

    public Integer getId() {
        return id;
    }
    public Booking getBooking() {
        return booking;
    }
    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    public LocalDate getRecordDate() {
        return recordDate;
    }
    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }
}
