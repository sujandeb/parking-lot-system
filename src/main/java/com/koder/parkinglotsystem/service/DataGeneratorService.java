package com.koder.parkinglotsystem.service;

import com.koder.parkinglotsystem.enums.PaymentMode;
import com.koder.parkinglotsystem.enums.SpotType;
import com.koder.parkinglotsystem.enums.VehicleType;
import com.koder.parkinglotsystem.model.Address;
import com.koder.parkinglotsystem.model.ParkingFloor;
import com.koder.parkinglotsystem.model.Spot;
import com.koder.parkinglotsystem.model.Vehicle;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class DataGeneratorService {
    Faker faker = new Faker();

    public Address generateAddress() {
        return new Address(
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().state(),
                faker.address().country(),
                faker.address().zipCode()
        );
    }

    public Vehicle generateVehicle() {
        int pick = new Random().nextInt(VehicleType.values().length);
        VehicleType vehicleType = VehicleType.values()[pick];
        String vehicleNumber = faker.vehicle().licensePlate().toUpperCase();
        return new Vehicle(
                vehicleType,
                vehicleNumber
        );
    }

    public ParkingFloor generateParkingFloor() {
        int spotCount = faker.number().numberBetween(100, 200);
        ParkingFloor parkingFloor = new ParkingFloor(spotCount);
        for (int i = 0; i < spotCount; i++) {
            parkingFloor.addSpot(generateSpot());
        }
        return parkingFloor;
    }

    private Spot generateSpot() {
        int pick = new Random().nextInt(SpotType.values().length);
        SpotType spotType = SpotType.values()[pick];
        return new Spot(spotType);
    }

    public PaymentMode generatePaymentMode() {
        int pick = new Random().nextInt(PaymentMode.values().length);
        return PaymentMode.values()[pick];
    }
}
