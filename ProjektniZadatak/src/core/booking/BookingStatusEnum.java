package core.booking;

/**
 * The type Booking status enum.
 */
public class BookingStatusEnum {
    /**
     * The enum Booking status.
     */
    public enum BookingStatus{
        /**
         * Pending booking status.
         */
        PENDING("Pending"),
        /**
         * Confirmed booking status.
         */
        CONFIRMED("Confirmed"),
        /**
         * Cancelled booking status.
         */
        CANCELLED("Cancelled"),
        /**
         * Completed booking status.
         */
        COMPLETED("Completed");

        private final String displayValue;

        BookingStatus(String displayValue){
            this.displayValue = displayValue;
        }

        /**
         * Gets display value.
         *
         * @return the display value
         */
        public String getDisplayValue() {
            return displayValue;
        }
        //dodati override za toString
    }
}
