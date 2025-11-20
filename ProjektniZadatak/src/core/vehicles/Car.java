package core.vehicles;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * The type Car.
 */
public final class Car implements RegisteredVehicles {
    private static Integer nextId = 1;

    private final Integer id;
    private final Integer year;
    private final String registration;
    private final String brand, model;
    private final BigDecimal pricePerDay;
    private boolean available;

    private final String color;
    private final Integer passengerCapacity;

    private Car(Builder builder){
        this.id = builder.id;
        this.registration = builder.registration;
        this.brand = builder.brand;
        this.model = builder.model;
        this.year = builder.year;
        this.pricePerDay = builder.pricePerDay;
        this.available = builder.available;

        this.color = builder.color;
        this.passengerCapacity= builder.passengerCapacity;
    }

    /**
     * The type Builder.
     */
    public static class Builder{
        private final Integer id;
        private Integer year;
        private String registration;
        private String brand, model;
        private BigDecimal pricePerDay;
        private boolean available = true;

        private String color = null;
        private Integer passengerCapacity = null;//default vrijednost kada se instancira objekt

        /**
         * Instantiates a new Builder.
         */
        public Builder(){
            this.id = nextId;
            nextId++;
        }

        /**
         * Sets registration.
         *
         * @param registration the registration
         * @return the registration
         */
        public Builder setRegistration(String registration) {
            this.registration = registration;
            return this;
        }

        /**
         * Sets year.
         *
         * @param year the year
         * @return the year
         */
        public Builder setYear(Integer year) {
            this.year = year;
            return this;
        }

        /**
         * Sets brand.
         *
         * @param brand the brand
         * @return the brand
         */
        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        /**
         * Sets model.
         *
         * @param model the model
         * @return the model
         */
        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        /**
         * Sets price per day.
         *
         * @param pricePerDay the price per day
         * @return the price per day
         */
        public Builder setPricePerDay(BigDecimal pricePerDay) {
            this.pricePerDay = pricePerDay;
            return this;
        }

        /**
         * Sets available.
         *
         * @param available the available
         * @return the available
         */
        public Builder setAvailable(boolean available) {
            this.available = available;
            return this;
        }

        /**
         * Set color builder.
         *
         * @param color the color
         * @return the builder
         */
        public Builder setColor(String color){
            this.color = color;
            return this;
        }

        /**
         * Sets passenger capacity.
         *
         * @param passengerCapacity the passenger capacity
         * @return the passenger capacity
         */
        public Builder setPassengerCapacity(Integer passengerCapacity) {
            this.passengerCapacity = passengerCapacity;
            return this;
        }

        /**
         * Build car.
         *
         * @return the car
         */
        public Car build(){
            if(brand == null || brand.isBlank()){
                throw new InvalidVehicleDataException("Marka automobila ne može biti prazna.");
            }
            if(model == null || model.isBlank()){
                throw new InvalidVehicleDataException("Model automobila ne može biti prazan.");
            }
            if(pricePerDay == null || pricePerDay.compareTo(BigDecimal.ZERO) < 0){
                throw new InvalidVehicleDataException("Cijena po danu ne može biti negativna.");
            }
            if(year == null || year < 1886 || year > java.time.Year.now().getValue() + 1){
                throw new InvalidVehicleDataException("Neispravna godina proizvodnje: " + year);
            }
            return new Car(this);
        }
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Gets brand.
     *
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Gets model.
     *
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets price per day.
     *
     * @return the price per day
     */
    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    /**
     * Is available boolean.
     *
     * @return the boolean
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets available.
     *
     * @param available the available
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Car car)) return false;
        return Objects.equals(registration, car.registration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registration);
    }

    @Override
    public String getRegistration() {
        return registration;
    }

    @Override
    public String getCarBrandModel(){
        return brand+" "+ model;
    }

    @Override
    public String getFullDescription(){
        return String.format(
                "ID: %d | Reg: %s | Marka/Model: %s | Godina: %d | Cijena/dan: %.2f HRK | Dostupan: %s",
                id, registration, getCarBrandModel(), year, pricePerDay, available ? "DA" : "NE"
        );
    }
}
