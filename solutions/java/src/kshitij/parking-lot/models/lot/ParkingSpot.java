package parking-lot.models.lot;

public class ParkingSpot {
    private String spotId;
    private VehicleSize spotSize;
    private boolean isOccupied;
    private Vehicle parkedVehicleDetails;

    public String getSpotId() {
        return spotId;
    }

    public ParkingSpot(String spotId, VehicleSize spotSize) {
        this.spotId = spotId;
        this.spotSize = spotSize;
        this.isOccupied = false;
        this.parkedVehicleDetails = null;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }

    public VehicleSize getSpotSize() {
        return spotSize;
    }

    public void setSpotSize(VehicleSize spotSize) {
        this.spotSize = spotSize;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public synchronized boolean isAvailable() {
        return !isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Vehicle getParkedVehicleDetails() {
        return parkedVehicleDetails;
    }

    public synchronized void unparkVehicle() {
        this.parkedVehicle = null;
        this.isOccupied = false;
    }

    public void setParkedVehicleDetails(Vehicle parkedVehicleDetails) {
        this.parkedVehicleDetails = parkedVehicleDetails;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        if (isOccupied) return false;

        switch (vehicle.getSize()) {
            case SMALL:
                return spotSize == VehicleSize.SMALL || spotSize == VehicleSize.MEDIUM || spotSize == VehicleSize.LARGE;;
            case MEDIUM:
                return spotSize == VehicleSize.MEDIUM || spotSize == VehicleSize.LARGE;
            case LARGE:
                return spotSize == VehicleSize.LARGE;
            default:
                return false;
        }
    }

}