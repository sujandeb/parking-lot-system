package com.koder.parkinglotsystem.model;

import com.koder.parkinglotsystem.enums.PaymentMode;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentCenter {
    double totalRevenue;

    public Invoice generateInvoice(Ticket ticket) {
        Invoice invoice = new Invoice(ticket);
        ticket.exitTime = LocalDateTime.now();
//        long hours = ChronoUnit.HOURS.between(entryTime, exitTime);
        // hardcode this to 2 hours for now
        long hours = 2;
        double fee = 5 * hours;
        invoice.setAmount(fee);
        ticket.getSpot().setVehicle(null);
        ticket.getSpot().setFree(true);
        return invoice;
    }



    public Payment payInvoice(Invoice invoice, PaymentMode paymentMode) {
        invoice.paymentMode = paymentMode;
        Payment payment = Payment.createPayment(invoice, paymentMode);
        payment.executePayment();
        totalRevenue += payment.getAmount();
        return payment;
    }

}
