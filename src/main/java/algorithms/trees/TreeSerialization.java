package algorithms.trees;

import java.util.*;

/*
Source: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 */
public class TreeSerialization {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        List<Integer> levelOrderTraversal = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        levelOrderTraversal.add(root.val);
        while (!deque.isEmpty()) {
            TreeNode treeNode = deque.pollFirst();
            if (treeNode.left != null) {
                deque.addLast(treeNode.left);
                levelOrderTraversal.add(treeNode.left.val);
            } else {
                levelOrderTraversal.add(null);
            }
            if (treeNode.right != null) {
                deque.addLast(treeNode.right);
                levelOrderTraversal.add(treeNode.right.val);
            } else {
                levelOrderTraversal.add(null);
            }
        }
        for (int i = levelOrderTraversal.size() - 1; i >= 0 && levelOrderTraversal.get(i) == null; i--) {
            levelOrderTraversal.remove(i);
        }
        return convertToString(levelOrderTraversal);
    }

    public static String convertToString(List<Integer> levelOrderTraversal) {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        for (Integer i : levelOrderTraversal) {
            stringJoiner.add(String.valueOf(i));
        }
        return stringJoiner.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.equals("[]")) {
            return null;
        }
        String[] elements = data.substring(1, data.length() - 1).split(",");
        List<TreeNode> prevLevelNodes = new ArrayList<>();
        TreeNode root = new TreeNode(Integer.parseInt(elements[0]));
        prevLevelNodes.add(root);

        int i = 1;
        int startIndex = 1;
        while (!prevLevelNodes.isEmpty()) {
            List<TreeNode> newList = new ArrayList<>();
            int number = prevLevelNodes.size() * 2;
            int lastIndex = Math.min(elements.length, startIndex + number);
            while (i < lastIndex) {
                String element = elements[i];
                if (!"null".equals(element)) {
                    int num = i - startIndex;
                    int indexInList = num / 2;
                    TreeNode newNode = new TreeNode(Integer.parseInt(element));
                    if ((num & 1) == 1) {
                        prevLevelNodes.get(indexInList).right = newNode;
                    } else {
                        prevLevelNodes.get(indexInList).left = newNode;
                    }
                    newList.add(newNode);
                }
                i++;
            }
            prevLevelNodes = newList;
            startIndex = i;
        }
        return root;
    }

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
