class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int prevIndex = map.get(nums[i]);
                
                if (i - prevIndex <= k) {
                    return true;
                }
            }
            map.put(nums[i], i); // update latest index
        }
        
        return false;
    }
}
