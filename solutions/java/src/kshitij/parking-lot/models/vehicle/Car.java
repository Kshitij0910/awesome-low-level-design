package parking-lot.models.vehicle;

public class Car extends Vehicle {
    public Car(String licenseNumber) {
        super(licenseNumber, VehicleSize.MEDIUM);
    }
}
