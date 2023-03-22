package com.example.txioswebflux.model;

public class OrderResultPayment {
    OrderResultPaymentPayload Payment;

    public OrderResultPaymentPayload getPayment() {
        return this.Payment;
    }

    public void setPayment(OrderResultPaymentPayload Payment) {
        this.Payment = Payment;
    }

}
