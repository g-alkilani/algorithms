package algorithms.arrays;

/*
Source: https://leetcode.com/problems/minimum-size-subarray-sum/

Given an array of n positive integers and a positive integer s,
find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 */
public class MinimumSizeSubArray {

    public int minSubArrayLen(int s, int[] nums) {
        int min = nums.length + 1;
        int sum = 0;
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum >= s) {
                while (sum >= s) {
                    sum -= nums[left];
                    left++;
                }
                left--;
                sum += nums[left];
                int newPossibleMin = i - left + 1;
                if (newPossibleMin < min) {
                    min = newPossibleMin;
                }
            }
        }
        return min == nums.length + 1 ? 0 : min;
    }

}
