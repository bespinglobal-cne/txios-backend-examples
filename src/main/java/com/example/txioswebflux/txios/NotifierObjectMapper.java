package com.example.txioswebflux.txios;
/**
 *   MsaMaker Notifier SocketMessage serialize/deserialize Mapper Interface
 *  @serialize : NotifierRequestDTO to Json
 *  @deserialize : Json To TxResponse
 */
public interface NotifierObjectMapper {
    /**
     * @param notifierRequestDTO
     * @return NotifierRequestDTO Json
     */
    public String serialize(NotifierRequestDTO notifierRequestDTO);

    /**
     * @param txId
     * @return NotifierRequestDTO Json
     */
    public String serialize(String txId);

    /**
     * @param notifierResult
     * @return TxResponse
     */
    public <R> TxResponse<R> deserialize(String notifierResult);
}
