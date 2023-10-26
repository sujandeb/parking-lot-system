package com.koder.parkinglotsystem;

import com.koder.parkinglotsystem.model.Invoice;
import com.koder.parkinglotsystem.model.ParkingLot;
import com.koder.parkinglotsystem.model.Ticket;
import com.koder.parkinglotsystem.model.Vehicle;
import com.koder.parkinglotsystem.model.Payment;
import com.koder.parkinglotsystem.service.DataGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ParkingLotSystem {

    ParkingLot parkingLot;
    @Autowired
    private DataGeneratorService dataGeneratorService;

    @EventListener(ApplicationReadyEvent.class)
    public void runningCarRentalSystem() {
        System.out.println("Hello world!");
        setupParkingLot();

        runSimulation();

    }

    public void setupParkingLot() {
        // create parking lot
        parkingLot = new ParkingLot(dataGeneratorService.generateAddress());
        // create 2 parking floors
        parkingLot.addFloor(dataGeneratorService.generateParkingFloor());
        parkingLot.addFloor(dataGeneratorService.generateParkingFloor());
        // create 3 entry gates
        parkingLot.addEntryGates(3);
        // create 3 exit gates
        parkingLot.addExitGates(3);
    }

    public void runSimulation() {
        List<Ticket> tickets = new ArrayList<>();
        // create many vehicles and assign parking spots
        // after the lot is full we should not be able to park any more vehicles
        for (int i = 0; i < 500; i++) {
            // get random vehicle type
            Vehicle vehicle = dataGeneratorService.generateVehicle();
            Ticket ticket = parkingLot.generateTicket(vehicle);
            if (ticket == null) {
                System.out.println(i + " Parking lot is full");
            } else {
                System.out.println(i + " Vehicle parked at spot: " + ticket.getSpot().getSpotId());
                tickets.add(ticket);
            }
        }
        // unpark all the vehicles
        for (Ticket ticket : tickets) {
            System.out.println("======== Unparking vehicle ========");
            System.out.println("Spot: " + ticket.getSpot());
            Invoice invoice = parkingLot.getPaymentCenter().generateInvoice(ticket);
            System.out.println("Ticket: " + ticket);
            System.out.println("Spot: " + ticket.getSpot());
            System.out.println("Invoice: " + invoice);
            Payment payment = parkingLot.getPaymentCenter().payInvoice(invoice,
                    dataGeneratorService.generatePaymentMode());
            System.out.println("Payment: " + payment);
        }
        // get the total revenue
        System.out.println("Total revenue: " + parkingLot.getPaymentCenter().getTotalRevenue() + " USD");
    }
}
