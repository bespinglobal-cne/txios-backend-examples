package com.example.txioswebflux.model;

public class TxOrderDTO {
   String route_id;
   OrderRoutePayload route_payload;

   public String getRoute_id() {
    return this.route_id;
   }

  public void setRoute_id(String route_id) {
    this.route_id = route_id;
  }
   public void setRoute_payload(OrderRoutePayload route_payload ){
    this.route_payload = route_payload; 
   }

   public OrderRoutePayload getRoute_payload(){
    return this.route_payload;
   }
}
