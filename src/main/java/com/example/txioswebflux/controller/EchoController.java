package com.example.txioswebflux.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.txioswebflux.model.TxOrderDTO;
import com.example.txioswebflux.model.TxOrderResultDTO;
import com.example.txioswebflux.txios.TxResponse;
import com.example.txioswebflux.txios.Txios;

import reactor.core.publisher.Mono;
 
@RestController
class UserController {

    @Autowired
    Txios txios;

    @PostMapping("/routing-rules")
    public Mono<TxResponse<TxOrderResultDTO>> getOrder(@RequestBody TxOrderDTO payload) {
        return txios.<TxOrderDTO , TxOrderResultDTO>start(payload);
    }
    /** Bad */    
    // @PostMapping("/routing-rules/type-free")
    // public Mono<TxResponse<Object>> getOrderTypeFree(@RequestBody Object payload) {
    //     return txios.<Object,Object>start(payload);
    // }
}
