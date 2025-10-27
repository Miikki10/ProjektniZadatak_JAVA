package entities.records;


import entities.vehicles.Booking;

import java.time.LocalDate;

public class RecordStorage {
    private static final int MAX_CAPACITY = 100;
    private static Integer nextId = 1;//static da pripada klasi a ne smao 1 instanci objekta

    /*private final Integer id;
    private Booking booking;
    private LocalDate recordDate;*/
    private BookingRecord[] permanentBookingRecords = new BookingRecord[MAX_CAPACITY];
    private int bookingRecordCounter = 0;


    public BookingRecord addPermanentRecord(Booking booking) {

        BookingRecord newRecord = new BookingRecord(
                nextId,
                booking,
                LocalDate.now()
        );

        permanentBookingRecords[bookingRecordCounter] = newRecord;

        nextId++;
        bookingRecordCounter++;

        return newRecord;
    }
    /*public Records(Booking booking, LocalDate recordDate) {
        this.id = nextId;
        nextId++;
        this.booking = booking;
        this.recordDate = recordDate;
        permanentBookingRecords[bookingRecordCounter] = new BookingRecord(id, booking, recordDate);
        bookingRecordCounter++;
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
    }*/
}
