package kshitij.parking_lot.entities.parking;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import kshitij.parking_lot.entities.vehicle.Vehicle;

public class ParkingFloor {
    private final int floorNumber;
    private final Map<String, ParkingSpot> spots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.spots = new ConcurrentHashMap<>();
    }

    public void addSpot(ParkingSpot spot) {
        spots.put(spot.getSpotId(), spot);
    }

    public synchronized Optional<ParkingSpot> findAvailableSpot(Vehicle vehicle) {
        return spots.values().stream()
            .filter(spot -> spot.isAvailable() && spot.canFitVehicle(vehicle))
            .min(Comparator.comparing(ParkingSpot::getSpotSize));
    }

}
