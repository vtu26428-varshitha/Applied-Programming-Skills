class Solution {
    public int[] sortedSquares(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];

        int l = 0, r = n - 1, k = n - 1;

        while (l <= r) {
            if (arr[l] * arr[l] > arr[r] * arr[r]) {
                res[k--] = arr[l] * arr[l];
                l++;
            } else {
                res[k--] = arr[r] * arr[r];
                r--;
            }
        }
        return res;
    }
}
