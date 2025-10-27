package entities.vehicles;

import java.math.BigDecimal;
import java.util.Scanner;

public class Car {
    private static Integer nextId = 1;

    private final Integer id;
    private Integer year;
    private String brand, model;
    private BigDecimal pricePerDay;
    private boolean available;

    private Car(Builder builder){
        this.id = builder.id;
        this.brand = builder.brand;
        this.model = builder.model;
        this.year = builder.year;
        this.pricePerDay = builder.pricePerDay;
        this.available = builder.available;
    }

    public static class Builder{
        private final Integer id;
        private Integer year;
        private String brand, model;
        private BigDecimal pricePerDay;
        private boolean available = true;//default vrijednost kada se instancira objekt

        public Builder(){
            this.id = nextId;
            nextId++;
        }
        /*public Builder setId(Integer id) {
            this.id = nextId;
            nextId++;
            return this;
        }*/
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

    /*public static Car inputCar(Scanner scanner){

        System.out.println("Brand: ");
        String brand = scanner.nextLine();

        System.out.println("Model: ");
        String model = scanner.nextLine();

        System.out.println("Godina: ");
        Integer godina = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Cijena po danu: ");
        BigDecimal pricePerDay = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.println("Status dostupnosti vozila (true/false): ");
        boolean available = scanner.nextBoolean();
        scanner.nextLine();

        return new Car.Builder()
                .setBrand(brand)
                .setModel(model)
                .setYear(godina)
                .setPricePerDay(pricePerDay)
                .setAvailable(available)
                .build();
    }*/
}
