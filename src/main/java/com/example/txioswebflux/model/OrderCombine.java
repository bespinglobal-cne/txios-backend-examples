package com.example.txioswebflux.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderCombine {
    @JsonProperty("index")
    Integer index;

    public void setIndex(Integer index){
     this.index = index; 
    }
    public Integer getIndex(){
     return this.index;
    }
}
