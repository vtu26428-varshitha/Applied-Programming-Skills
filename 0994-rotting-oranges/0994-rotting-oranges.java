class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;
        int minutes = 0;

        int rows = grid.length;
        int cols = grid[0].length;

        // Count fresh oranges and store rotten ones
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        // BFS
        while (!q.isEmpty() && fresh > 0) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();

                for (int[] d : dirs) {
                    int nr = curr[0] + d[0];
                    int nc = curr[1] + d[1];

                    if (nr >= 0 && nc >= 0 && nr < rows && nc < cols
                            && grid[nr][nc] == 1) {

                        grid[nr][nc] = 2;
                        fresh--;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }

            minutes++;
        }

        return fresh == 0 ? minutes : -1;
    }
}