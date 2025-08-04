package com.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

public class Assertions{

    @Test
    public void testAssertions() {
        System.out.println("--- Running Assertions Test ---");

        assertEquals(5, 2 + 3);
        System.out.println("Assertion: assertEquals(5, 2 + 3) - Passed");

        assertTrue(5 > 3);
        System.out.println("Assertion: assertTrue(5 > 3) - Passed");

        assertFalse(5 < 3);
        System.out.println("Assertion: assertFalse(5 < 3) - Passed");

        assertNull(null);
        System.out.println("Assertion: assertNull(null) - Passed");

        assertNotNull(new Object());
        System.out.println("Assertion: assertNotNull(new Object()) - Passed");

        System.out.println("--- All Assertions in testAssertions() Passed! ---");
    }
}
