package com.koder.parkinglotsystem.model;

import com.koder.parkinglotsystem.enums.PaymentMode;
import lombok.Data;

import java.util.UUID;

@Data
public class Invoice {
    String invoiceId;
    Ticket ticket;
    double amount;
    PaymentMode paymentMode;
    boolean isPaid;
    public Invoice(Ticket ticket){
        this.invoiceId = "I-" + UUID.randomUUID();
        this.isPaid = false;
        this.ticket = ticket;
    }
}
