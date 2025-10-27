package entities.vehicles;

public sealed interface Vehicles permits Car, Motorcycle {
    String getRegistration();
}
