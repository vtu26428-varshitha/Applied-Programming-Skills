class Solution {

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        // Assign unique groups to ungrouped items
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }

        // Item graph
        List<List<Integer>> itemGraph = new ArrayList<>();
        int[] itemIndegree = new int[n];

        // Group graph
        List<List<Integer>> groupGraph = new ArrayList<>();
        int[] groupIndegree = new int[m];

        for (int i = 0; i < n; i++) {
            itemGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            groupGraph.add(new ArrayList<>());
        }

        // Build graphs
        for (int item = 0; item < n; item++) {

            for (int prev : beforeItems.get(item)) {

                itemGraph.get(prev).add(item);
                itemIndegree[item]++;

                // Different groups
                if (group[item] != group[prev]) {
                    groupGraph.get(group[prev]).add(group[item]);
                    groupIndegree[group[item]]++;
                }
            }
        }

        // Topological sort groups
        List<Integer> groupOrder = topoSort(groupGraph, groupIndegree, m);

        // Topological sort items
        List<Integer> itemOrder = topoSort(itemGraph, itemIndegree, n);

        if (groupOrder.isEmpty() || itemOrder.isEmpty()) {
            return new int[0];
        }

        // Group items based on topological item order
        Map<Integer, List<Integer>> groupedItems = new HashMap<>();

        for (int item : itemOrder) {
            groupedItems
                .computeIfAbsent(group[item], k -> new ArrayList<>())
                .add(item);
        }

        // Build final answer
        List<Integer> result = new ArrayList<>();

        for (int g : groupOrder) {
            result.addAll(groupedItems.getOrDefault(g, new ArrayList<>()));
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    private List<Integer> topoSort(List<List<Integer>> graph,
                                   int[] indegree,
                                   int size) {

        Queue<Integer> q = new LinkedList<>();
        List<Integer> order = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int curr = q.poll();
            order.add(curr);

            for (int next : graph.get(curr)) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        return order.size() == size ? order : new ArrayList<>();
    }
}