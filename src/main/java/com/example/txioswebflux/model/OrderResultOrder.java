package com.example.txioswebflux.model;

public class OrderResultOrder {
    OrderResultCombinePayload combine;
    OrderResultOrderPayload Order;

    public OrderResultCombinePayload getCombine() {
        return this.combine;
    }

    public void setCombine(OrderResultCombinePayload combine) {
        this.combine = combine;
    }

    public OrderResultOrderPayload getOrder() {
        return this.Order;
    }

    public void setOrder(OrderResultOrderPayload Order) {
        this.Order = Order;
    }

}
