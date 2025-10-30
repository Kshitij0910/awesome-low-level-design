package kshitij.parking_lot.strategy.parking;

import java.util.List;
import java.util.Optional;
import kshitij.parking_lot.entities.parking.ParkingFloor;
import kshitij.parking_lot.entities.parking.ParkingSpot;
import kshitij.parking_lot.entities.vehicle.Vehicle;

public class NearestFitStrategy implements ParkingStrategy{

    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle) {
        for(ParkingFloor floor : floors) {
            Optional<ParkingSpot> spot = floor.findAvailableSpot(vehicle);
            if (spot.isPresent()) {
                return spot;
            }
        }
        return Optional.empty();
    }
}
