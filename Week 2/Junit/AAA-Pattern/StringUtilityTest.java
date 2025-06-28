package com.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class StringUtilityTest {

    private StringUtility stringUtility = new StringUtility();

    @Test
    public void testReverseString_Normal() {
        String original = "hello";
        String expected = "olleh";
        String actual = stringUtility.reverseString(original);
        assertEquals(expected, actual);
    }

    @Test
    public void testReverseString_Empty() {
        assertEquals("", stringUtility.reverseString(""));
    }

    @Test
    public void testReverseString_Null() {
        assertNull(stringUtility.reverseString(null));
    }

    @Test
    public void testReverseString_WithSpaces() {
        String original = "h e l l o";
        String expected = "o l l e h";
        String actual = stringUtility.reverseString(original);
        assertEquals(expected, actual);
    }

    @Test
    public void testIsPalindrome_True() {
        assertTrue(stringUtility.isPalindrome("madam"));
    }

    @Test
    public void testIsPalindrome_TrueWithSpacesAndPunctuation() {
        assertTrue(stringUtility.isPalindrome("A man, a plan, a canal: Panama"));
    }

    @Test
    public void testIsPalindrome_False() {
        assertFalse(stringUtility.isPalindrome("hello"));
    }

    @Test
    public void testIsPalindrome_Empty() {
        assertFalse(stringUtility.isPalindrome(""));
    }

    @Test
    public void testIsPalindrome_Null() {
        assertFalse(stringUtility.isPalindrome(null));
    }

    @Test
    public void testCountVowels_Normal() {
        assertEquals(2, stringUtility.countVowels("hello"));
    }

    @Test
    public void testCountVowels_UpperCase() {
        assertEquals(3, stringUtility.countVowels("PROGRAMMING"));
    }

    @Test
    public void testCountVowels_NoVowels() {
        assertEquals(0, stringUtility.countVowels("rhythm"));
    }

    @Test
    public void testCountVowels_Empty() {
        assertEquals(0, stringUtility.countVowels(""));
    }

    @Test
    public void testCountVowels_Null() {
        assertEquals(0, stringUtility.countVowels(null));
    }
}
