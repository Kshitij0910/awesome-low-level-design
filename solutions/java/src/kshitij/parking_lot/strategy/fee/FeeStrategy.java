package kshitij.parking_lot.strategy.fee;


import kshitij.parking_lot.entities.parking.ParkingTicket;

public interface FeeStrategy {
    double calculateFee(ParkingTicket parkingTicket);
}
