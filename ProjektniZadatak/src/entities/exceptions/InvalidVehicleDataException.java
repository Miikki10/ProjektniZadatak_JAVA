package entities.exceptions;

/**
 * The type Invalid vehicle data exception.
 */
public class InvalidVehicleDataException extends IllegalArgumentException{
    /**
     * Instantiates a new Invalid vehicle data exception.
     *
     * @param message the message
     */
    public InvalidVehicleDataException(String message){
        super(message);
    }
}
