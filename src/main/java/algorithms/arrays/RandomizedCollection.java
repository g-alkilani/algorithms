package algorithms.arrays;

import java.util.*;

/*
Source: https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/

Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements.
The probability of each element being returned is linearly related to the number of same value the collection contains.
 */
class RandomizedCollection {
    private int size;
    private final Random random = new Random();
    private final Map<Integer, Set<Integer>> indexesByNumber = new HashMap<>(2 << 5);
    private final List<Integer> numbers = new ArrayList<>(2 << 5);

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollection() {

    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        Set<Integer> indexes = indexesByNumber.computeIfAbsent(val, k -> new HashSet<>(2 << 5));
        indexes.add(size);
        numbers.add(val);
        size++;
        return indexes.size() == 1;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        Set<Integer> indexes = indexesByNumber.get(val);
        if (indexes == null || indexes.size() == 0) {
            return false;
        }
        int indexToRemove = indexes.iterator().next();
        indexes.remove(indexToRemove);
        int lastIndex = size - 1;
        if (indexToRemove == lastIndex) {
            numbers.remove(indexToRemove);
        } else {
            int lastNumber = numbers.get(lastIndex);
            numbers.remove(lastIndex);
            numbers.set(indexToRemove, lastNumber);
            Set<Integer> indexesForLastNumber = indexesByNumber.get(lastNumber);
            indexesForLastNumber.remove(lastIndex);
            indexesForLastNumber.add(indexToRemove);
        }
        size--;
        return true;
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        int index = random.nextInt(size);
        return numbers.get(index);
    }

}
