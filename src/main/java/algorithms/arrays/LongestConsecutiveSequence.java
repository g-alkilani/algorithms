package algorithms.arrays;

import java.util.HashSet;
import java.util.Set;

/*
Source: https://leetcode.com/problems/longest-consecutive-sequence/

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 */
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        Set<Integer> numbers = new HashSet<>(nums.length);
        for (int i : nums) {
            numbers.add(i);
        }
        int max = 0;
        for (int i : nums) {
            if (numbers.contains(i)) {
                int k = i + 1;
                int length = 0;
                while (numbers.remove(k)) {
                    k++;
                    length++;
                }
                k = i - 1;
                while (numbers.remove(k)) {
                    k--;
                    length++;
                }
                numbers.remove(i);
                length++;
                if (length > max) {
                    max = length;
                }
            }
        }
        return max;
    }

}
