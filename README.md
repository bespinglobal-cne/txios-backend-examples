# Txios-java-examples

**목적** 
spring서비스(spring ^5)에서 msaMaker로 요청시 httpConnection을 유지한채 요청/응답 받을 수 있는 라이브러리 Txios예제



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
  "tx_id": "163345953768120320",
  "result": true,
  "route_payload": {
    "delivery": {
      "Delivery": {
        "index": 5
      }
    },
    "payment": {
      "Payment": {
        "index": 3
      }
    },
    "stock": {
      "Stock": {
        "index": 4
      }
    },
    "order": {
      "Combine": {
        "index": 1
      },
      "Order": {
        "index": 2
      }
    }
  },
  "create_time": 1679907510374
}

```



**if PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException**

   **step1 : 소스 다운**
   ```bash
   curl -O https://gist.githubusercontent.com/lesstif/cd26f57b7cfd2cd55241b20e05b5cd93/raw/InstallCert.java
   ```

   **step2 : 컴파일**
   ```bash
   javac InstallCert.java
   ```

   **step3 : installCert 사이트 이름과 함께 실행**
   ```bash
   java -cp ./ InstallCert dev-runner.msamaker.bespinglobal.com
   ```

   **step4 : 생성된 keystore파일 jssecacerts 에 있는 인증서를 output.cert 파일로 저장 (alias 옵션 뒤에는 위에서 표시한 alias 입력)**
   ```bash
    keytool -exportcert -keystore jssecacerts -storepass changeit -file output.cert -alias dev-runner.msamaker.bespinglobal.com-1
   ```
   **step5 : JVM 의 keystore 에 CA 인증서를 추가**
    
   ```bash
    sudo  keytool -importcert -keystore ${JAVA_HOME}/lib/security/cacerts -storepass changeit -file output.cert -alias letsencrypt
    //  sudo keytool -importcert -keystore /Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/lib/security/cacerts -storepass changeit -file output.cert -alias letsencrypt 
   ```

- keytool error: java.lang.Exception: Certificate not imported, alias <letsencrypt> already exists 에러 발생 시
- 기존 label 을 삭제
    ```bash
    sudo keytool -delete  -keystore ${JAVA_HOME}/lib/security/cacerts -storepass changeit  -alias letsencrypt
    // sudo keytool -delete  -keystore /Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/lib/security/cacerts -storepass changeit  -alias letsencrypt
    ```
- 기존 label 삭제 후 재실행
  ```bash
    sudo  keytool -importcert -keystore ${JAVA_HOME}/lib/security/cacerts -storepass changeit -file output.cert -alias letsencrypt
    //  sudo keytool -importcert -keystore /Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/lib/security/cacerts -storepass changeit -file output.cert -alias letsencrypt 
   ```

    ref  : https://www.lesstif.com/java/java-pkix-path-building-failed-98926844.html

 