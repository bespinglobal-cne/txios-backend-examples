package com.example.txioswebflux.txios;

import java.util.ArrayList;
import java.util.List;
/**
 * Notifier요청 인터페이스 계약명세 DTO
 */
public class NotifierRequestDTO {
    List<String> txIdList = new ArrayList<String>();  // Contract
    
    public void setTxIdList(List<String> txIdList){
        this.txIdList = txIdList;
    }
    public List<String> getTxIdList(){
        return this.txIdList;
    }
}
