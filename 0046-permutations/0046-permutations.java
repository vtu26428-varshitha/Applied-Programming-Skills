class Solution {

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        solve(nums, new ArrayList<>(),
              new boolean[nums.length], result);

        return result;
    }

    private void solve(int[] nums,
                       List<Integer> temp,
                       boolean[] visited,
                       List<List<Integer>> result) {

        if (temp.size() == nums.length) {

            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            temp.add(nums[i]);

            solve(nums, temp, visited, result);

            temp.remove(temp.size() - 1);
            visited[i] = false;
        }
    }
}