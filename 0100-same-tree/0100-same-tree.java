class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Both null → same
        if (p == null && q == null) return true;
        
        // One null → not same
        if (p == null || q == null) return false;
        
        // Values different → not same
        if (p.val != q.val) return false;
        
        // Check left and right
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}