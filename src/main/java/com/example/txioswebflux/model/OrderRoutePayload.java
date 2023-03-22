package com.example.txioswebflux.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRoutePayload {
    @JsonProperty("service_name")
    String service_name;

    @JsonProperty("payload")
    OrderPayload payload;

    public void setService_name(String service_name){
        this.service_name = service_name;
    }

    public String getService_name(){
        return service_name;
    }
    public OrderPayload getPayload() {
        return this.payload;
    }

    public void setPayload(OrderPayload payload) {
        this.payload = payload;
    }
}
