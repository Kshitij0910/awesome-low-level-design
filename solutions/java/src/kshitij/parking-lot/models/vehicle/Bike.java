package parking-lot.models.vehicle;

public class Bike extends Vehicle {
    public Bike(String licenseNumber) {
        super(licenseNumber, VehicleSize.SMALL);
    }
}