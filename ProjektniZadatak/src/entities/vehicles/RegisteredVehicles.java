package entities.vehicles;

public sealed interface RegisteredVehicles permits Car {
    String getRegistration();
    String getCarBrandModel();

    default String getFullDescription(){
        return getCarBrandModel() + " - " + getRegistration();
    }
}
