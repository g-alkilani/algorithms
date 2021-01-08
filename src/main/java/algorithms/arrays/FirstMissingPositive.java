package algorithms.arrays;

/*
Source: https://leetcode.com/problems/first-missing-positive/

Given an unsorted integer array nums, find the smallest missing positive integer.

 */

public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        int min = -1;
        int minPositive = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            if (current > 0 && current < minPositive) {
                minPositive = current;
            }
            if (current != i) {
                if (current >= nums.length) {
                    min = current;
                } else if (current >= 0) {
                    if (nums[current] != current) {
                        int temp = nums[current];
                        nums[current] = current;
                        nums[i] = temp;
                        i--;
                    }
                }
            }
        }
        if (minPositive >= 2) {
            return 1;
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return min == nums.length ? nums.length + 1 : nums.length;
    }

}
