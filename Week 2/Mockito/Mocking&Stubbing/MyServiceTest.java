package com.example;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyServiceTest {

    @Test
    public void testFetchDataWithMockApi() {
        ExternalApi mockApi = mock(ExternalApi.class);

        when(mockApi.getData()).thenReturn("Hello from Mock!");

        MyService service = new MyService(mockApi);

        String result = service.fetchData();

        assertEquals("Hello from Mock!", result, "The data fetched should be the mocked data.");

        verify(mockApi).getData();

        System.out.println("Test `testFetchDataWithMockApi` completed successfully.");
    }
}
