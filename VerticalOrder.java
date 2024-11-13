// Problem 314. Binary Tree Vertical Order Traversal
// Time Complexity : O(nlogn)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        Map<Integer, List<int[]>> columnTable = new TreeMap<>();
        dfs(root, 0, 0, columnTable);
        List<List<Integer>> result = new ArrayList<>();
        for (List<int[]> columnNodes : columnTable.values()) {
            columnNodes.sort((a, b) -> Integer.compare(a[0], b[0]));
            List<Integer> sortedColumn = new ArrayList<>();
            for (int[] nodeInfo : columnNodes) {
                sortedColumn.add(nodeInfo[1]);
            }
            result.add(sortedColumn);
        }
        return result;
    }

    private void dfs(TreeNode node, int row, int column, Map<Integer, List<int[]>> columnTable) {
        if (node == null) {
            return;
        }
        columnTable.computeIfAbsent(column, k -> new ArrayList<>()).add(new int[]{row, node.val});
        dfs(node.left, row + 1, column - 1, columnTable);
        dfs(node.right, row + 1, column + 1, columnTable);
    }
}