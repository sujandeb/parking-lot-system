package com.koder.parkinglotsystem.model;

import com.koder.parkinglotsystem.enums.PaymentMode;
import com.koder.parkinglotsystem.enums.PaymentStatus;

public class PaymentCash extends Payment {


    public PaymentCash(Invoice invoice, PaymentMode paymentMode) {
        super(invoice, paymentMode);
    }

    @Override
    public void executePayment() {
        System.out.println("*** Payment through Cash ***");
        this.paymentStatus = PaymentStatus.SUCCESS;
    }
}
