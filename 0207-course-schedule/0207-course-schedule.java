class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]);
        }

        int[] visited = new int[numCourses]; // 0=unvisited,1=visiting,2=done

        for (int i = 0; i < numCourses; i++) {
            if (dfs(graph, visited, i)) return false;
        }

        return true;
    }

    private boolean dfs(List<List<Integer>> graph, int[] visited, int node) {
        if (visited[node] == 1) return true;  // cycle
        if (visited[node] == 2) return false;

        visited[node] = 1;

        for (int nei : graph.get(node)) {
            if (dfs(graph, visited, nei)) return true;
        }

        visited[node] = 2;
        return false;
    }
}