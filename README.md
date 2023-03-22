# Txios-java-examples

**목적** 
고객의 spring서비스(spring ^5)에서 msaMaker로 요청시 httpConnection을 유지한채 응답이 받을 수 있는지 검증 pilot



- **Bean등록(ApplicationConfig.java)**
```java
    @Bean
    public Txios txios() {
        return new Txios.Builder()
        .notifierUrl("wss://notifier.msamaker.bespinglobal.com/txresult")
        .runnerUrl("https://runner.msamaker.bespinglobal.com/runner/routeRequest")
        .build();
    }
```

- **사용(EchoController.java)**
```java
    @Autowired
    Txios txios;

    @PostMapping("/routing-rules")
    public Mono<TxResponse<TxOrderResultDTO>> order(@RequestBody TxOrderDTO payload) {
        return txios.<TxOrderDTO, TxOrderResultDTO>start(payload);
    }
```

- **요청 payload**(TxOrderDTO.java)
```json
{
  "route_id": "rt_20230224064821",
  "route_payload": {
    "service_name": "order",
    "payload": {
      "Combine": {
        "index": 1
      }
    }
  }
}
```

- **응답 TxResponse**(OrderResultDTO.java)
```json
{
    "tx_id": "157185859261865984",
    "result": true,
    "route_payload": {
        "service_name": "delivery",
        "payload": {
            "Order": {
                "index": 2
            },
            "Payment": {
                "index": 3
            },
            "Combine": "{index=1}",
            "Delivery": {
                "index": 5
            },
            "Stock": {
                "index": 4
            },
            "combine": "{index=1}"
        }
    },
    "create_time": 1.678438829456E12
}

```

**TODO**
 - 예외처리 
 - 메모리 누수 확인


**if**
-  error : PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException
   solution : https://www.lesstif.com/java/java-pkix-path-building-failed-98926844.html

 