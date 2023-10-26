package com.koder.parkinglotsystem.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParkingLot {
    String parkingLotId;
    Address address;
    PaymentCenter paymentCenter;
    ParkingRate parkingRate;
    List<ParkingFloor> parkingFloors = new ArrayList<>();
    List<EntryGate> entryGates = new ArrayList<>();
    List<ExitGate> exitGates = new ArrayList<>();
    List<Ticket> tickets = new ArrayList<>();

    public ParkingLot(Address address) {
        this.parkingLotId = "PL" + System.currentTimeMillis();
        this.address = address;
        this.paymentCenter = new PaymentCenter();
    }

    public Ticket generateTicket(Vehicle vehicle) {
        if (isParkingFull()) {
            return null;
        }
        for (ParkingFloor parkingFloor : parkingFloors) {
            if (parkingFloor.getAvailableParkingSpots() > 0) {
                Spot spot = parkingFloor.getAvailableSpotFor(vehicle);
                parkingFloor.parkVehicle(vehicle, spot);
                Ticket ticket = new Ticket(vehicle, spot);
                tickets.add(ticket);
                return ticket;
            }
        }
        return null;
    }

    public boolean isParkingFull() {
        for (ParkingFloor parkingFloor : parkingFloors) {
            if (parkingFloor.getAvailableParkingSpots() > 0) {
                return false;
            }
        }

        return true;
    }

    public void addFloors(int count) {
        for (int j = 0; j < count; j++) {
            // TBD: this can be parameterized later on
            parkingFloors.add(new ParkingFloor(100));
        }
    }

    public void addEntryGates(int count) {
        for (int j = 0; j < count; j++) {
            // TBD: this can be parameterized later on
            entryGates.add(new EntryGate());
        }
    }

    public void addExitGates(int count) {
        for (int j = 0; j < count; j++) {
            // TBD: this can be parameterized later on
            exitGates.add(new ExitGate());
        }
    }

    public void addFloor(ParkingFloor parkingFloor) {
        parkingFloors.add(parkingFloor);
    }
}
