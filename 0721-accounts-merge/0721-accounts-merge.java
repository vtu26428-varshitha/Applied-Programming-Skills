class Solution {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        Map<String, List<String>> graph = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        // Build graph
        for (List<String> account : accounts) {

            String name = account.get(0);

            for (int i = 1; i < account.size(); i++) {

                String email = account.get(i);

                emailToName.put(email, name);

                graph.putIfAbsent(email, new ArrayList<>());

                if (i == 1) {
                    continue;
                }

                String prev = account.get(i - 1);

                graph.get(email).add(prev);
                graph.get(prev).add(email);
            }
        }

        Set<String> visited = new HashSet<>();
        List<List<String>> result = new ArrayList<>();

        // DFS for connected components
        for (String email : graph.keySet()) {

            if (!visited.contains(email)) {

                List<String> merged = new ArrayList<>();

                dfs(email, graph, visited, merged);

                Collections.sort(merged);

                merged.add(0, emailToName.get(email));

                result.add(merged);
            }
        }

        return result;
    }

    private void dfs(String email,
                     Map<String, List<String>> graph,
                     Set<String> visited,
                     List<String> merged) {

        visited.add(email);
        merged.add(email);

        for (String next : graph.get(email)) {

            if (!visited.contains(next)) {
                dfs(next, graph, visited, merged);
            }
        }
    }
}