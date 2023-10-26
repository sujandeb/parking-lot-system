package com.koder.parkinglotsystem.model;

import com.koder.parkinglotsystem.enums.PaymentMode;
import com.koder.parkinglotsystem.enums.PaymentStatus;

public class PaymentCard extends Payment {


    public PaymentCard(Invoice invoice, PaymentMode paymentMode) {
        super(invoice, paymentMode);
    }

    @Override
    public void executePayment() {
        System.out.println("*** Payment through Card ***");
        this.paymentStatus = PaymentStatus.SUCCESS;
        this.invoice.setPaid(true);
    }
}
