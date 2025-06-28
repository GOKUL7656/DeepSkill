package com.example;

public class StringUtility {

    public String reverseString(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }

    public boolean isPalindrome(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        String cleanedStr = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversedStr = new StringBuilder(cleanedStr).reverse().toString();
        return cleanedStr.equals(reversedStr);
    }

    public int countVowels(String str) {
        if (str == null) {
            return 0;
        }
        int count = 0;
        String lowerCaseStr = str.toLowerCase();
        for (char ch : lowerCaseStr.toCharArray()) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }
}
