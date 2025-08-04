package com.example;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyServiceTest {

    @Test
    public void testVerifyInteraction() {
        External mockApi = mock(External.class);

        when(mockApi.getData()).thenReturn("Some data");

        MyService service = new MyService(mockApi);

        service.fetchData();

        verify(mockApi).getData();

        String result = service.fetchData();
        assertEquals("Some data", result, "The service should return the stubbed data.");
    }

    @Test
    public void testSendAndCheckStatus_verifyInteraction() {
        External mockApi = mock(External.class);
        MyService service = new MyService(mockApi);

        String testData = "Hello Mockito";
        int expectedStatusCode = 200;

        when(mockApi.sendData(testData)).thenReturn(true);
        when(mockApi.getStatusCode()).thenReturn(expectedStatusCode);

        service.sendAndCheckStatus(testData);

        verify(mockApi).sendData(testData);

        verify(mockApi).getStatusCode();
    }
}
