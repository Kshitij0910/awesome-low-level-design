package kshitij.parking_lot;

import kshitij.parking_lot.entities.parking.ParkingFloor;
import kshitij.parking_lot.entities.parking.ParkingSpot;
import kshitij.parking_lot.entities.parking.ParkingTicket;
import kshitij.parking_lot.entities.vehicle.*;
import kshitij.parking_lot.strategy.fee.FlatRateFeeStrategy;
import kshitij.parking_lot.strategy.fee.VehicleBasedFeeStrategy;
import kshitij.parking_lot.strategy.parking.NearestFitStrategy;
import kshitij.parking_lot.strategy.parking.BestFitStrategy;

import java.util.Optional;

public class ParkingLotDemo {
    public static void main(String[] args) {
        System.out.println("=== PARKING LOT SYSTEM DEMO ===\n");
        
        // Initialize parking lot
        ParkingLot parkingLot = ParkingLot.getInstance();
        
        // Setup floors and spots
        setupParkingLot(parkingLot);
        
        // Demo 1: Basic parking with BestFit strategy
        System.out.println("🚗 DEMO 1: Basic Parking (BestFit Strategy)");
        System.out.println("=" .repeat(50));
        demoBasicParking(parkingLot);
        
        // Demo 2: Strategy pattern - switching to NearestFit
        System.out.println("\n🎯 DEMO 2: Strategy Pattern (NearestFit Strategy)");
        System.out.println("=" .repeat(50));
        demoStrategyPattern(parkingLot);
        
        // Demo 3: Fee calculation strategies
        System.out.println("\n💰 DEMO 3: Fee Strategy Pattern");
        System.out.println("=" .repeat(50));
        demoFeeStrategies(parkingLot);
        
        // Demo 4: Edge cases and error handling
        System.out.println("\n⚠️  DEMO 4: Edge Cases & Error Handling");
        System.out.println("=" .repeat(50));
        demoEdgeCases(parkingLot);
        
        System.out.println("\n✅ DEMO COMPLETED SUCCESSFULLY!");
    }
    
    private static void setupParkingLot(ParkingLot parkingLot) {
        System.out.println("🏗️  Setting up parking lot...");
        
        // Floor 1: Mixed spots
        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.addSpot(new ParkingSpot("F1-S1", VehicleSize.SMALL));
        floor1.addSpot(new ParkingSpot("F1-S2", VehicleSize.SMALL));
        floor1.addSpot(new ParkingSpot("F1-M1", VehicleSize.MEDIUM));
        floor1.addSpot(new ParkingSpot("F1-L1", VehicleSize.LARGE));
        
        // Floor 2: More medium and large spots
        ParkingFloor floor2 = new ParkingFloor(2);
        floor2.addSpot(new ParkingSpot("F2-M1", VehicleSize.MEDIUM));
        floor2.addSpot(new ParkingSpot("F2-M2", VehicleSize.MEDIUM));
        floor2.addSpot(new ParkingSpot("F2-L1", VehicleSize.LARGE));
        
        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);
        
        System.out.println("✅ Parking lot setup complete!\n");
    }
    
    private static void demoBasicParking(ParkingLot parkingLot) {
        // Create vehicles
        Vehicle bike1 = new Bike("B-001");
        Vehicle car1 = new Car("C-001");
        Vehicle truck1 = new Truck("T-001");
        
        // Park vehicles
        System.out.println("Parking vehicles...");
        Optional<ParkingTicket> bikeTicket = parkingLot.parkVehicle(bike1);
        Optional<ParkingTicket> carTicket = parkingLot.parkVehicle(car1);
        Optional<ParkingTicket> truckTicket = parkingLot.parkVehicle(truck1);
        
        // Simulate some time passing
        try {
            Thread.sleep(2000); // 2 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Unpark one vehicle
        System.out.println("\nUnparking car...");
        Optional<Double> fee = parkingLot.unparkVehicle(car1);
        fee.ifPresent(f -> System.out.printf("💰 Parking fee: $%.2f\n", f));
    }
    
    private static void demoStrategyPattern(ParkingLot parkingLot) {
        // Switch to NearestFit strategy
        parkingLot.setParkingStrategy(new NearestFitStrategy());
        System.out.println("🔄 Switched to NearestFit strategy");
        
        // Park more vehicles
        Vehicle bike2 = new Bike("B-002");
        Vehicle car2 = new Car("C-002");
        
        System.out.println("\nParking with NearestFit strategy...");
        parkingLot.parkVehicle(bike2);
        parkingLot.parkVehicle(car2);
        
        // Switch back to BestFit
        parkingLot.setParkingStrategy(new BestFitStrategy());
        System.out.println("\n🔄 Switched back to BestFit strategy");
    }
    
    private static void demoFeeStrategies(ParkingLot parkingLot) {
        Vehicle testCar = new Car("C-FEE-TEST");
        
        // Test with VehicleBasedFeeStrategy (current)
        System.out.println("Testing VehicleBasedFeeStrategy...");
        Optional<ParkingTicket> ticket1 = parkingLot.parkVehicle(testCar);
        
        try {
            Thread.sleep(1500); // 1.5 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        Optional<Double> fee1 = parkingLot.unparkVehicle(testCar);
        fee1.ifPresent(f -> System.out.printf("💰 VehicleBased fee: $%.2f\n", f));
        
        // Switch to FlatRateFeeStrategy
        parkingLot.setFeeStrategy(new FlatRateFeeStrategy());
        System.out.println("\n🔄 Switched to FlatRateFeeStrategy");
        
        Vehicle testCar2 = new Car("C-FEE-TEST-2");
        Optional<ParkingTicket> ticket2 = parkingLot.parkVehicle(testCar2);
        
        try {
            Thread.sleep(1500); // 1.5 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        Optional<Double> fee2 = parkingLot.unparkVehicle(testCar2);
        fee2.ifPresent(f -> System.out.printf("💰 FlatRate fee: $%.2f\n", f));
        
        // Switch back to VehicleBasedFeeStrategy
        parkingLot.setFeeStrategy(new VehicleBasedFeeStrategy());
    }
    
    private static void demoEdgeCases(ParkingLot parkingLot) {
        // Try to park when lot might be full
        System.out.println("Testing edge cases...");
        
        // Fill up remaining spots
        Vehicle bike3 = new Bike("B-003");
        Vehicle bike4 = new Bike("B-004");
        Vehicle car3 = new Car("C-003");
        Vehicle car4 = new Car("C-004");
        
        parkingLot.parkVehicle(bike3);
        parkingLot.parkVehicle(bike4);
        parkingLot.parkVehicle(car3);
        parkingLot.parkVehicle(car4);
        
        // Try to park when no spots available
        Vehicle extraCar = new Car("C-EXTRA");
        System.out.println("\nTrying to park when lot is full...");
        Optional<ParkingTicket> failedTicket = parkingLot.parkVehicle(extraCar);
        if (failedTicket.isEmpty()) {
            System.out.println("✅ Correctly handled full parking lot");
        }
        
        // Try to unpark non-existent vehicle
        Vehicle nonExistentCar = new Car("C-NOT-EXIST");
        System.out.println("\nTrying to unpark non-existent vehicle...");
        Optional<Double> failedUnpark = parkingLot.unparkVehicle(nonExistentCar);
        if (failedUnpark.isEmpty()) {
            System.out.println("✅ Correctly handled non-existent vehicle");
        }
        
        // Try to unpark already unparked vehicle
        System.out.println("\nTrying to unpark already unparked vehicle...");
        Vehicle alreadyUnparked = new Car("C-FEE-TEST");
        Optional<Double> doubleUnpark = parkingLot.unparkVehicle(alreadyUnparked);
        if (doubleUnpark.isEmpty()) {
            System.out.println("✅ Correctly handled double unpark attempt");
        }
    }
}
