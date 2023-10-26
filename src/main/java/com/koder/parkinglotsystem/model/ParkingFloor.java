package com.koder.parkinglotsystem.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.koder.parkinglotsystem.util.ParkingLotUtils.isSpotCompatible;

@Data
public class ParkingFloor {
    int floorId;
    int totalParkingSpots;
    int availableParkingSpots;
    int occupiedParkingSpots;
    List<Spot> spots = new ArrayList<>();

    public ParkingFloor(int totalParkingSpots) {
        this.totalParkingSpots = totalParkingSpots;
        this.availableParkingSpots = totalParkingSpots;
        this.occupiedParkingSpots = 0;
    }

    public Spot getAvailableSpotFor(Vehicle vehicle) {
        for (Spot spot : spots) {
            if (spot.isFree() && isSpotCompatible(spot, vehicle)) {
                return spot;
            }
        }
        return null;
    }

    public void parkVehicle(Vehicle vehicle, Spot spot) {
        spot.setVehicle(vehicle);
        spot.setFree(false);
        this.availableParkingSpots--;
        this.occupiedParkingSpots++;
    }

    public void addSpot(Spot spot) {
        spots.add(spot);
    }


}
