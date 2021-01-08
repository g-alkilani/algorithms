package algorithms.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

/*
Source: https://leetcode.com/problems/minimum-window-substring/

Given a string S and a string T, find the minimum window
in S which will contain all the characters in T in complexity O(n).

The time complexity of this solution is O(n).
 */
public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        BiFunction<Character, Integer, Integer> increment = (k, v) -> v == null ? 1 : v + 1;
        Map<Character, Integer> countByContainedStringChar = new HashMap<>(t.length());
        for (int i = 0, length = t.length(); i < length; i++) {
            countByContainedStringChar.compute(t.charAt(i), increment);
        }
        int start = 0;
        int end = -1;
        int min = s.length() + 1;
        Map<Character, Integer> countBySymbol = new HashMap<>(3 * countByContainedStringChar.size() / 2);
        BiFunction<Character, Integer, Integer> decrement = (k, v) -> v != null && v > 1 ? v - 1 : null;
        Set<Character> containedStringChars = new HashSet<>(countByContainedStringChar.keySet());
        for (int i = 0, j = 0, length = s.length(); j < length; j++) {
            char c = s.charAt(j);
            boolean contains = containedStringChars.contains(c);
            if (contains) {
                if (!countByContainedStringChar.containsKey(c)) {
                    countBySymbol.compute(c, increment);
                } else {
                    countByContainedStringChar.compute(c, decrement);
                }
            }
            if (countByContainedStringChar.isEmpty() && contains) {
                while (i < length) {
                    char left = s.charAt(i);
                    if (!containedStringChars.contains(left)) {
                        i++;
                    } else {
                        Integer val = countBySymbol.get(left);
                        i++;
                        if (val == null || val == 0) {
                            break;
                        } else {
                            countBySymbol.put(left, val - 1);
                        }
                    }
                }
                i--;
                int newPossibleMin = j - i + 1;
                if (newPossibleMin < min) {
                    start = i;
                    end = j;
                    min = newPossibleMin;
                }
            }
        }
        return s.substring(start, end + 1);
    }

}
