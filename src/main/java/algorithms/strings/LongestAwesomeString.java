package algorithms.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
Source: https://leetcode.com/problems/find-longest-awesome-substring/
Given a string s. An awesome substring is a non-empty substring of s such that we can make
any number of swaps in order to make it palindrome.

Return the length of the maximum length awesome substring of s.
 */
public class LongestAwesomeString {

    public int longestAwesome(String s) {
        int max = 1;
        byte[] countByDigit = new byte[10];
        Map<Integer, Integer> indexByCombination = new HashMap<>();
        Set<Integer> candidates = new HashSet<>(32);
        int[] shifts = new int[10];
        for (int i = 0, start = 1; i < 10; i++) {
            shifts[i] = start;
            start <<= 1;
        }
        Set<Integer> shiftSet = new HashSet<>(32);
        for (int i : shifts) {
            shiftSet.add(i);
        }
        for (int i = 0, length = s.length(); i < length; i++) {
            int currentDigit = s.charAt(i) & 0b1111;
            countByDigit[currentDigit] ^= 1;
            int currentCombination = 0;
            for (int j = 0; j < 10; j++) {
                if (countByDigit[j] != 0) {
                    currentCombination |= shifts[j];
                }
            }

            if (currentCombination == 0 || shiftSet.contains(currentCombination)) {
                max = i + 1;
            } else {
                findCandidates(currentCombination, candidates, shifts);
                for (Integer candidate : candidates) {
                    Integer leftIndex = indexByCombination.get(candidate);
                    if (leftIndex != null) {
                        int temp = i - leftIndex;
                        if (temp > max) {
                            max = temp;
                        }
                    }
                }
                candidates.clear();
            }

            if (!indexByCombination.containsKey(currentCombination)) {
                indexByCombination.put(currentCombination, i);
            }
        }
        return max;
    }

    private Set<Integer> findCandidates(int currentCombination, Set<Integer> candidates, int[] shifts) {
        candidates.add(currentCombination);
        for (int i = 0; i < 10; i++) {
            candidates.add(currentCombination ^ shifts[i]);
        }
        return candidates;
    }

}
