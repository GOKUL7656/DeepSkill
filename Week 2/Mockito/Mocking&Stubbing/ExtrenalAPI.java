package com.example;

public interface ExternalApi {
    String getData();
    boolean sendData(String data);
    int getStatusCode();
}