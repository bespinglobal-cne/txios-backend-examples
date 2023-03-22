package com.example.txioswebflux.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderPayload {
    @JsonProperty("Combine")
    OrderCombine Combine;

    public void setCombine(OrderCombine combine){
        this.Combine = combine; 
    }

    public OrderCombine getCombine(){
    return this.Combine;
    }
}
