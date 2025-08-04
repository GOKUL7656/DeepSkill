package com;
import static org.junit.Assert.assertTrue;
import org.junit.Test; 

class TestLogic {
    public String test(String x) {
        return x;
    }
}

public class AppTest {
    @Test
    public void testString() {

        TestLogic myTestObject = new TestLogic();
        String returnedString = myTestObject.test("Digital Nurture Deepskilling Program-Week2");
        System.out.println(returnedString);
        assertTrue(true);
    }
}
