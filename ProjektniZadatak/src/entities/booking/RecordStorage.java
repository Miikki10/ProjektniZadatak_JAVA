package entities.booking;


import java.time.LocalDate;

public class RecordStorage {
    private static final int MAX_CAPACITY = 100;
    private static Integer nextId = 1;//static da pripada klasi a ne smao 1 instanci objekta

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
}
