class Solution {

    public int[] shortestAlternatingPaths(int n,
                                          int[][] redEdges,
                                          int[][] blueEdges) {

        List<Integer>[] redGraph = new ArrayList[n];
        List<Integer>[] blueGraph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            redGraph[i] = new ArrayList<>();
            blueGraph[i] = new ArrayList<>();
        }

        // Build graphs
        for (int[] e : redEdges) {
            redGraph[e[0]].add(e[1]);
        }

        for (int[] e : blueEdges) {
            blueGraph[e[0]].add(e[1]);
        }

        int[] result = new int[n];
        Arrays.fill(result, -1);

        boolean[][] visited = new boolean[n][2];

        Queue<int[]> q = new LinkedList<>();

        // node, color
        // 0 = red, 1 = blue
        q.offer(new int[]{0, 0});
        q.offer(new int[]{0, 1});

        visited[0][0] = true;
        visited[0][1] = true;

        int distance = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {

                int[] curr = q.poll();
                int node = curr[0];
                int color = curr[1];

                if (result[node] == -1) {
                    result[node] = distance;
                }

                // Alternate color
                if (color == 0) {

                    for (int next : blueGraph[node]) {

                        if (!visited[next][1]) {
                            visited[next][1] = true;
                            q.offer(new int[]{next, 1});
                        }
                    }

                } else {

                    for (int next : redGraph[node]) {

                        if (!visited[next][0]) {
                            visited[next][0] = true;
                            q.offer(new int[]{next, 0});
                        }
                    }
                }
            }

            distance++;
        }

        return result;
    }
}