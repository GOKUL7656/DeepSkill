package com.example;

public class MyService {
    private ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }


      @return 
    public String fetchData() {
        return externalApi.getData();
    }


     @param data 
      @return 
    public int sendAndCheckStatus(String data) {
        if (externalApi.sendData(data)) {
            return externalApi.getStatusCode();
        }
        return -1; 
    }
}
