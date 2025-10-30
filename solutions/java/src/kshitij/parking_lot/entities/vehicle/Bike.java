package kshitij.parking_lot.entities.vehicle;

public class Bike extends Vehicle{

    public Bike(String licenseNumber) {
        super(licenseNumber, VehicleSize.SMALL);
    }
}
