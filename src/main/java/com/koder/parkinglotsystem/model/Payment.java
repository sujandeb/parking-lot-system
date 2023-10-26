package com.koder.parkinglotsystem.model;

import com.koder.parkinglotsystem.enums.PaymentMode;
import com.koder.parkinglotsystem.enums.PaymentStatus;
import lombok.Data;

import java.util.UUID;

@Data
public abstract class Payment {
    String paymentId;
    Invoice invoice;
    double amount;
    PaymentMode paymentMode;
    PaymentStatus paymentStatus;

    public Payment(Invoice invoice, PaymentMode paymentMode) {
        this.paymentId = "P-" + UUID.randomUUID();
        this.invoice = invoice;
        this.amount = invoice.getAmount();
        this.paymentMode = paymentMode;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public static Payment createPayment(Invoice invoice, PaymentMode paymentMode) {
        Payment payment = switch (paymentMode) {
            case CASH -> new PaymentCash(invoice, paymentMode);
            case CARD -> new PaymentCard(invoice, paymentMode);
            case UPI -> new PaymentUpi(invoice, paymentMode);
        };
        return payment;
    }

    public abstract void executePayment();

}
