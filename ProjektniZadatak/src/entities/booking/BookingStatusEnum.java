package entities.booking;

public class BookingStatusEnum {
    public enum BookingStatus{
        PENDING("Pending"),
        CONFIRMED("Confirmed"),
        CANCELLED("Cancelled"),
        COMPLETED("Completed");

        private final String displayValue;

        BookingStatus(String displayValue){
            this.displayValue = displayValue;
        }

        public String getDisplayValue() {
            return displayValue;
        }
        //dodati override za toString
    }
}
