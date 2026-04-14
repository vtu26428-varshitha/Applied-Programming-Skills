import java.util.*;

class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root != null) dfs(root, "", result);
        return result;
    }

    private void dfs(TreeNode node, String path, List<String> result) {
        if (node == null) return;

        // Build path
        if (path.isEmpty()) {
            path = "" + node.val;
        } else {
            path = path + "->" + node.val;
        }

        // Leaf node
        if (node.left == null && node.right == null) {
            result.add(path);
            return;
        }

        dfs(node.left, path, result);
        dfs(node.right, path, result);
    }
}