package algorithms.bitmanipulation;

/*
Calculate the sum of two integers a and b without using the operators + and -.

This solution was tested on https://leetcode.com/problems/sum-of-two-integers/
 */
public class SumOfTwoIntegers {

    public int getSum(int a, int b) {
        int carryBit = 0;
        int shift = 1;
        int result = 0;
        while (a != 0 || b != 0) {
            int lastA = a & 1;
            int lastB = b & 1;
            int lastC = lastA ^ lastB ^ carryBit;
            if (carryBit == 0) {
                if ((lastA & lastB) == 1) {
                    carryBit = 1;
                }
            } else {
                if ((lastA | lastB) == 0) {
                    carryBit = 0;
                }
            }
            if (lastC == 1) {
                result |= shift;
            }
            shift <<= 1;
            a >>>= 1;
            b >>>= 1;
        }
        if (carryBit == 1) {
            result |= shift;
        }
        return result;
    }

}
