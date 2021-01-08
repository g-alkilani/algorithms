package algorithms.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Source: https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character
in this substring is greater than or equal to k.

The complexity of this solution is O(n), where n is the length of a string.
 */

class LongestSubstringWithRepeatingChars {

    public int longestSubstring(String s, int k) {
        Map<Character, List<Integer>> indexesByChar = new HashMap<>(64);
        for (char c = 'a'; c <= 'z'; c++) {
            indexesByChar.put(c, new ArrayList<>());
        }
        int max = 0;
        int[] freqByChar = new int['a' + 26];
        boolean[] excludedChars = new boolean['a' + 26];

        for (int i = 0, length = s.length(); i < length; i++) {
            char c = s.charAt(i);
            freqByChar[c]++;
            indexesByChar.get(c).add(i);
            boolean flag = true;
            for (char character = 'a'; character <= 'z'; character++) {
                int freq = freqByChar[character];
                if (freq != 0 && freq < k) {
                    excludedChars[character] = true;
                    flag = false;
                }
            }

            if (flag) {
                max = i + 1;
                continue;
            }

            int minIndex = 0;
            for (char character = 'a'; character <= 'z'; character++) {
                if (excludedChars[character]) {
                    List<Integer> integers = indexesByChar.get(character);
                    int last = integers.get(integers.size() - 1);
                    if (last + 1 > minIndex) {
                        minIndex = last + 1;
                    }
                }
            }
            for (char character = 'a'; character <= 'z'; character++) {
                if (!excludedChars[character] && freqByChar[character] >= k) {
                    List<Integer> integers = indexesByChar.get(character);
                    int size = integers.size();
                    if (integers.get(size - k) < minIndex) {
                        excludedChars[character] = true;
                        int temp = integers.get(size - 1) + 1;
                        if (temp > minIndex) {
                            minIndex = temp;
                            character = (char) ('a' - 1);
                        }
                    }
                }
            }
            int temp = i - minIndex + 1;
            if (temp > max) {
                max = temp;
            }
            for (char character = 'a'; character <= 'z'; character++) {
                excludedChars[character] = false;
            }
        }
        return max;
    }
}
