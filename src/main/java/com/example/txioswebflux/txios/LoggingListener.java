package com.example.txioswebflux.txios;
/**
 * Txios 디버그 로깅 콜백 인터페이스
*/
@FunctionalInterface
public interface LoggingListener {
    public void onLog(String action, String message);
}
