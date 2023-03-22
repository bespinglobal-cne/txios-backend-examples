package com.example.txioswebflux.txios;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Notifier응답 인터페이스 계약명세 DTO
 */

public class TxRequest<P> {
    @JsonProperty("route_id")
    String route_id;

    @JsonProperty("route_payload")
    P route_payload;

    public String getRoute_id() {
		return this.route_id;
	}

    public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

    public P getRoute_payload() {
        return this.route_payload;
    }

    public void setRoute_payload(P route_payload) {
        this.route_payload = route_payload;
    }

}
