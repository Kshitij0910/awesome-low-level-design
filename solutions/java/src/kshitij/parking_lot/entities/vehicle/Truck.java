package kshitij.parking_lot.entities.vehicle;

public class Truck extends Vehicle{

    public Truck(String licenseNumber) {
        super(licenseNumber, VehicleSize.LARGE);
    }
}
