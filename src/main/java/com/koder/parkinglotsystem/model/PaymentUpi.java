package com.koder.parkinglotsystem.model;


import com.koder.parkinglotsystem.enums.PaymentMode;
import com.koder.parkinglotsystem.enums.PaymentStatus;

public class PaymentUpi extends Payment {


    public PaymentUpi(Invoice invoice, PaymentMode paymentMode) {
        super(invoice, paymentMode);
    }

    @Override
    public void executePayment() {
        System.out.println("*** Payment through UPI ***");
        this.paymentStatus = PaymentStatus.SUCCESS;
    }
}
