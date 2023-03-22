package com.example.txioswebflux.model;

public class TxOrderResultDTO {
    OrderResultDelivery delivery;
    OrderResultOrder order;
    OrderResultPayment payment;
    OrderResultStock Stock;

    public OrderResultDelivery getDelivery() {
		return this.delivery;
	}

    public void setDelivery(OrderResultDelivery delivery) {
		this.delivery = delivery;
	}

    public OrderResultOrder getOrder() {
        return this.order;
    }

    public void setOrder(OrderResultOrder order) {
        this.order = order;
    }

    public OrderResultPayment getPayment() {
		return this.payment;
	}

    public void set(OrderResultPayment payment ) {
		this.payment = payment ;
	}

    public OrderResultStock getStock() {
        return this.Stock;
    }

    public void setStock(OrderResultStock Stock) {
        this.Stock = Stock;
    }
 
}
