package entities.vehicles;

import java.math.BigDecimal;

public final class Car implements RegisteredVehicles {
    private static Integer nextId = 1;

    private final Integer id;
    private Integer year;
    private String registration;
    private String brand, model;
    private BigDecimal pricePerDay;
    private boolean available;

    private String color = null;
    private Integer passengerCapacity = null;

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

    public static class Builder{
        private final Integer id;
        private Integer year;
        private String registration;
        private String brand, model;
        private BigDecimal pricePerDay;
        private boolean available = true;

        private String color = null;
        private Integer passengerCapacity = null;//default vrijednost kada se instancira objekt

        public Builder(){
            this.id = nextId;
            nextId++;
        }
        public Builder setRegistration(String registration) {
            this.registration = registration;
            return this;
        }
        public Builder setYear(Integer year) {
            this.year = year;
            return this;
        }
        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }
        public Builder setModel(String model) {
            this.model = model;
            return this;
        }
        public Builder setPricePerDay(BigDecimal pricePerDay) {
            this.pricePerDay = pricePerDay;
            return this;
        }
        public Builder setAvailable(boolean available) {
            this.available = available;
            return this;
        }
        public Builder setColor(String color){
            this.color = color;
            return this;
        }

        public Builder setPassengerCapacity(Integer passengerCapacity) {
            this.passengerCapacity = passengerCapacity;
            return this;
        }

        public Car build(){
            return new Car(this);
        }
    }

    public Integer getId() {
        return id;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }
    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String getRegistration() {
        return registration;
    }

    @Override
    public String getCarBrandModel(){
        return brand+" "+ model;
    }
}
