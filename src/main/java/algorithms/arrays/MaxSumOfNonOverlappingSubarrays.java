package algorithms.arrays;

/*
Source: https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/

Given an array A of non-negative integers, return the maximum sum of elements
in two non-overlapping (contiguous) subarrays, which have lengths L and M.

The time complexity of the solution below is O(L + M).
The space complexity is O(n), where n is the length of an initial array.
 */
public class MaxSumOfNonOverlappingSubarrays {

    public int maxSumTwoNoOverlap(int[] a, int L, int M) {
        int first = maxSumTwoNoOverlapHelp(a, L, M);
        int second = maxSumTwoNoOverlapHelp(a, M, L);
        return Math.max(first, second);
    }

    private int maxSumTwoNoOverlapHelp(int[] a, int leftLength, int rightLength) {
        int[] leftSums = new int[a.length - rightLength - leftLength + 1];
        int[] rightSums = new int[a.length];
        int initialSum = 0;
        for (int i = 0; i < leftLength; i++) {
            initialSum += a[i];
        }
        leftSums[0] = initialSum;
        int lastRightIndex = a.length - rightLength;
        for (int i = leftLength, currentSum = initialSum; i < lastRightIndex; i++) {
            currentSum += a[i];
            currentSum -= a[i - leftLength];
            if (currentSum >= initialSum) {
                initialSum = currentSum;
            }
            leftSums[i - leftLength + 1] = initialSum;
        }

        initialSum = 0;
        for (int i = a.length - 1; i >= lastRightIndex; i--) {
            initialSum += a[i];
        }
        rightSums[lastRightIndex] = initialSum;

        for (int i = a.length - rightLength - 1, currentSum = initialSum; i >= leftLength; i--) {
            currentSum += a[i];
            currentSum -= a[i + rightLength];
            if (currentSum >= initialSum) {
                initialSum = currentSum;
            }
            rightSums[i] = initialSum;
        }

        int max = 0;
        for (int i = 0; i < leftSums.length; i++) {
            int temp = leftSums[i] + rightSums[i + leftLength];
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

}
