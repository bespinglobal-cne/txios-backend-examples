package com.example.txioswebflux.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.txioswebflux.txios.Txios;
@Configuration
public class ApplicationConfig {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);
    @Bean
    public Txios txios() {
        return new Txios.Builder()
        .notifierUrl("wss://notifier.msamaker.bespinglobal.com/txresult")
        .runnerUrl("https://runner.msamaker.bespinglobal.com/runner/routeRequest")
        .logging((action, message) -> {
            logger.info("-----------------------------------------------");
            logger.info("action  : " + action);
            if(message!=null) logger.info("Message : " + message);
            logger.info("-----------------------------------------------");
        })
        .build();
    }
}
