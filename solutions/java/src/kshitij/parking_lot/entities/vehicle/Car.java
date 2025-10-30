package kshitij.parking_lot.entities.vehicle;

public class Car extends Vehicle{

    public Car(String licenseNumber) {
        super(licenseNumber, VehicleSize.MEDIUM);
    }
}
