package com.example.txioswebflux.txios;

/**
 * Notifier응답 인터페이스 계약명세 DTO
 */

public class TxResponse<R> {
     String tx_id; // Contract
     Boolean result; // Contract
     R route_payload;
     Double create_time; // Contract

    public String getTx_id() {
        return this.tx_id;
    }

    public void setTx_id(String tx_id) {
        this.tx_id = tx_id;
    }

    public Boolean getResult() {
        return this.result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
    
    public R getRoute_payload() {
        return this.route_payload;
    }

    public void setRoute_payload(R route_payload) {
        this.route_payload = route_payload;
    }

    public Double getCreate_time() {
        return this.create_time;
    }

    public void setCreate_time(Double create_time) {
        this.create_time = create_time;
    }



}
