package com.example.txioswebflux.txios;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

/**
 * 
 * @Dependencys
 *  - org.java_websocket
 *  - com.fasterxml.jackson
 */
public class Txios {
    private String runnerUrl;
    private String notifierUrl;
    private NotifierObjectMapper socketMapper;
    private LoggingListener loggingListener;
 
    private Txios(Builder builder){
        this.runnerUrl = builder.runnerUrl;
        this.notifierUrl = builder.notifierUrl;
        this.socketMapper = builder.socketMapper;
        this.loggingListener = builder.loggingListener;
    }
    /**
     * 
     * @Types
     <pre>
     *P : 요청 데이터 타입
     *R : 응답 데이터 타입(route_payload)
     <pre>
     */
    public <P,R> Mono<TxResponse<R>> start(P payload){
        return Mono.create((consumer) -> {
            try {
                WebSocketClient webSocketClient = new WebSocketClient(new URI(this.notifierUrl)) {
                    @Override
                    public void onOpen(ServerHandshake serverHandshake) {
                        loging("onOpen Socket");
                        String txid = null;
                        try {
                    
                            txid = getTxId(payload);
                        } catch (IOException e) {
                            e.printStackTrace();
                            consumer.error(e);
                        }
                        String jsonSerialized = socketMapper.serialize(txid);
                        loging("Request Notifier", jsonSerialized);
                        this.send(jsonSerialized);
                    }

                    @Override
                    public void onMessage(String message) {
                        TxResponse<R> txResponse = socketMapper.deserialize(message);
                        this.close();
                        loging("onMessage", message);
                        consumer.success(txResponse);
                    }
            
                    @Override
                    public void onClose(int code, String reason, boolean remote) {
                        loging("onClose");
                    }
                
                    @Override
                    public void onError(Exception ex) {
                        loging("onError");
                        ex.printStackTrace();
                        consumer.error(ex);
                    }
                };
                webSocketClient.connect();
                loging("Socket connection to Notifier", this.notifierUrl);
            }catch(Exception ex){
                ex.printStackTrace();
                consumer.error(ex);
            }
        });
    }
    /**
     * gettxId  By rtid
     * @param payload
     * @return
     * @throws IOException
     * @throws MalformedURLException
     */
    private <P> String getTxId(Object payload) throws IOException   {
        String txId = null;
        StringBuffer response = new StringBuffer();
        ObjectMapper om = new ObjectMapper();
    
            URL url = new URL(this.runnerUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
    
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            String payloadJson = om.writeValueAsString(payload);
            loging("Request Runner", payloadJson);

            OutputStream os = con.getOutputStream();          
            byte request_data[] = payloadJson.getBytes("utf-8");
            os.write(request_data);
            os.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;


            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
		    in.close();
       
            JsonNode responseJsonNode = om.readTree(response.toString());
            JsonNode resultJsonNode = responseJsonNode.get("result");
            txId = resultJsonNode.get("tx_id").asText();
        return txId;
    }
    private void loging(String action){
        if(this.loggingListener !=null){
            this.loggingListener.onLog(action, null);
        }
    }
    private void loging(String action, String message){
        if(this.loggingListener !=null){
            this.loggingListener.onLog(action, message);
        }
    }   
    public static class Builder {
        private String runnerUrl = null;
        private String notifierUrl = null;
        NotifierObjectMapper socketMapper = null;
        private LoggingListener loggingListener = null;

      

        public Builder runnerUrl(String runnerUrl) {
            this.runnerUrl = runnerUrl;
            return this;
        }

        public Builder notifierUrl(String notifierUrl) {
            this.notifierUrl = notifierUrl;
            return this;
        }
        public Builder socketMapper(NotifierObjectMapper socketMapper) {
            this.socketMapper = socketMapper;
            return this;
        }
      

        public Builder logging(LoggingListener loggingListener) {
            this.loggingListener = loggingListener;
            return this;
        }

        public Txios build(){
            if(this.runnerUrl == null || notifierUrl == null){
                throw new NullPointerException("runnerUrl, notifierUrl must be non-null.");
             } 
             if(this.socketMapper == null){
                this.socketMapper = new NotifierJacksonObjectMapper();
             }
            return new Txios(this);
        }

    }
   
}
