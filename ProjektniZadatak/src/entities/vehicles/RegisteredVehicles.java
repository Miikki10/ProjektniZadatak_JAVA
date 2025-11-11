package entities.vehicles;

/**
 * The interface Registered vehicles.
 */
public sealed interface RegisteredVehicles permits Car {
    /**
     * Gets registration.
     *
     * @return the registration
     */
    String getRegistration();

    /**
     * Gets car brand model.
     *
     * @return the car brand model
     */
    String getCarBrandModel();
    String getFullDescription();
    /**
     * Get full description string.
     *
     * @return the string
     */
    default String getSemiDescription(){
        return getCarBrandModel() + " - " + getRegistration();
    }
}
