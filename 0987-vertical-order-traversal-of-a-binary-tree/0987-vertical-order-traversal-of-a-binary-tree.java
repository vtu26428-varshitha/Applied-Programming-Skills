import java.util.*;

class Solution {
    List<int[]> nodes = new ArrayList<>();

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        dfs(root, 0, 0);

        // Sort by x, then y, then value
        Collections.sort(nodes, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];       // x
            if (a[1] != b[1]) return a[1] - b[1];       // y
            return a[2] - b[2];                         // value
        });

        List<List<Integer>> result = new ArrayList<>();
        int prevX = Integer.MIN_VALUE;

        for (int[] node : nodes) {
            if (node[0] != prevX) {
                result.add(new ArrayList<>());
                prevX = node[0];
            }
            result.get(result.size() - 1).add(node[2]);
        }

        return result;
    }

    private void dfs(TreeNode root, int x, int y) {
        if (root == null) return;

        nodes.add(new int[]{x, y, root.val});
        dfs(root.left, x - 1, y + 1);
        dfs(root.right, x + 1, y + 1);
    }
}