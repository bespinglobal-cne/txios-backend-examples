package com.example.txioswebflux.txios;

import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 *  Implement MsaMaker Notifier SocketMessage serialize/deserialize Mapper
 *  @serialize : NotifierRequestDTO to Json
 *  @deserialize : Json To TxResponse
 *  @implSpec NotifierObjectMapper
 */
public class NotifierJacksonObjectMapper implements NotifierObjectMapper {
    ObjectMapper jacksonObjectMapper = new ObjectMapper();

    /**
     *  txId -> notifierRequestDTO Json
     * 
     *  @txId msaMaker transsaction Id
     *  @return NotifierRequestDTO JsonString
     */
    @Override
    public String serialize(String txId){
        NotifierRequestDTO notifierRequestDTO = new NotifierRequestDTO();
        notifierRequestDTO.setTxIdList(Arrays.asList(txId));
        return this.serialize(notifierRequestDTO);
    }
    /**
     * notifierRequestDTO Object -> notifierRequestDTO Json
     * 
     * @notifierRequestDTO notifier 요청 규격 DTO
     * @return NotifierRequestDTO JsonString
     */
    @Override
    public String serialize(NotifierRequestDTO notifierRequestDTO){
        String parsedData = null;
        try {
            parsedData = this.jacksonObjectMapper.writeValueAsString(notifierRequestDTO);
        }catch(JsonProcessingException exception) {
            exception.printStackTrace();
        }
        return parsedData;
    }

  
    /**
     * TxResponse Json to TxResponse Object
     * 
     * @param notifierResult notifier 응답
     * <R> : route_payload Type
     * @return TxResponse
     */
    @SuppressWarnings("unchecked")
    @Override
    public <R> TxResponse<R> deserialize(String notifierResult){
        TxResponse<R> txResponse = new  TxResponse<R>();
        try{
            txResponse = this.jacksonObjectMapper.readValue(notifierResult,  TxResponse.class);
            return txResponse;
        }catch(Exception e){
            e.printStackTrace();
        }
        return txResponse;
      
    }

    
}
