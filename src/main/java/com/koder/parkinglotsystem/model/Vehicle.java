package com.koder.parkinglotsystem.model;

import com.koder.parkinglotsystem.enums.VehicleType;
import lombok.Data;

import java.util.UUID;

@Data
public class Vehicle {
    String vehicleId;
    VehicleType vehicleType;
    String vehicleNumber;

    public Vehicle(VehicleType vehicleType, String vehicleNumber) {
        this.vehicleId = "V-" + UUID.randomUUID();
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
    }
}
