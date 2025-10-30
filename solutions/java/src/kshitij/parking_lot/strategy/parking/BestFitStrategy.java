package kshitij.parking_lot.strategy.parking;

import java.util.List;
import java.util.Optional;
import kshitij.parking_lot.entities.parking.ParkingFloor;
import kshitij.parking_lot.entities.parking.ParkingSpot;
import kshitij.parking_lot.entities.vehicle.Vehicle;

public class BestFitStrategy implements ParkingStrategy{

    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle) {
        Optional<ParkingSpot> bestSpot = Optional.empty();
        for (ParkingFloor floor : floors) {
            Optional<ParkingSpot> floorBest = floor.findAvailableSpot(vehicle);
            if (floorBest.isPresent()) {
                if (bestSpot.isEmpty() ||
                    floorBest.get().getSpotSize().compareTo(bestSpot.get().getSpotSize()) < 0) {
                    bestSpot = floorBest;
                }
            }
        }
        return bestSpot;
    }
}
