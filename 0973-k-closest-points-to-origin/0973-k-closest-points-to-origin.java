class Solution {

    public int[][] kClosest(int[][] points, int k) {

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> distance(a) - distance(b)
        );

        for (int[] point : points) {
            pq.offer(point);
        }

        int[][] result = new int[k][2];

        for (int i = 0; i < k; i++) {
            result[i] = pq.poll();
        }

        return result;
    }

    private int distance(int[] point) {

        return point[0] * point[0]
             + point[1] * point[1];
    }
}