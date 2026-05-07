class Solution {

    public int[][] updateMatrix(int[][] mat) {

        int rows = mat.length;
        int cols = mat[0].length;

        int[][] dist = new int[rows][cols];

        Queue<int[]> q = new LinkedList<>();

        // Initialize
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (mat[i][j] == 0) {
                    q.offer(new int[]{i, j});
                } else {
                    dist[i][j] = -1;
                }
            }
        }

        int[][] dirs = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        // BFS
        while (!q.isEmpty()) {

            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];

            for (int[] d : dirs) {

                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nc >= 0 &&
                    nr < rows && nc < cols &&
                    dist[nr][nc] == -1) {

                    dist[nr][nc] = dist[r][c] + 1;

                    q.offer(new int[]{nr, nc});
                }
            }
        }

        return dist;
    }
}