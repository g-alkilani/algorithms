package algorithms.bitmanipulation;

import java.util.*;

/*
Source: https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/

 */
public class NumberOfValidWords {

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        int[] numByChar = new int[123];
        int k = 1;
        for (char c = 'a'; c <= 'z'; c++) {
            numByChar[c] = k;
            k <<= 1;
        }

        Map<Integer, Integer> countByNumber = new HashMap<>(words.length / 2);
        for (String s : words) {
            int num = 0;
            for (int i = 0, length = s.length(); i < length; i++) {
                char c = s.charAt(i);
                num |= numByChar[c];
            }
            Integer curr = countByNumber.get(num);
            if (curr == null) {
                countByNumber.put(num, 1);
            } else {
                countByNumber.put(num, curr + 1);
            }
        }

        List<Integer> result = new ArrayList<>(puzzles.length);
        for (String s : puzzles) {
            int sumForPuzzle = 0;
            Deque<Integer> generatedSet = generatePossibleCombinations(s, numByChar);
            for (Integer integer : generatedSet) {
                Integer value = countByNumber.get(integer);
                if (value != null) {
                    sumForPuzzle += value;
                }
            }
            result.add(sumForPuzzle);
        }
        return result;
    }

    private Deque<Integer> generatePossibleCombinations(String puzzle, int[] numByChar) {
        int num = 0;
        char firstDigit = puzzle.charAt(0);
        num |= numByChar[firstDigit];

        Deque<Integer> deque = new ArrayDeque<>(64);
        deque.add(num);
        int k = 1;
        while (deque.size() != 64) {
            int size = deque.size();
            int numWithOneSetBit = numByChar[puzzle.charAt(k)];
            for (int i = 0; i < size; i++) {
                int first = deque.pollFirst();
                deque.addLast(first);
                deque.addLast(first | numWithOneSetBit);
            }
            k++;
        }
        return deque;
    }

}
