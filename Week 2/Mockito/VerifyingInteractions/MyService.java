package com.example;

public class MyService {
    private External externalApi;

    public MyService(External externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public int sendAndCheckStatus(String data) {
        if (externalApi.sendData(data)) {
            return externalApi.getStatusCode();
        }
        return -1;
    }
}
