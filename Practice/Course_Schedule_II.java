class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
        // create adjacency list to store graph
        ArrayList<Integer>[] adj = new ArrayList[numCourses];
        
        // initialize each list
        for (int i = 0; i < numCourses; i++) adj[i] = new ArrayList<>();

        // array to store number of prerequisites
        int[] indegree = new int[numCourses];

        // build graph and fill indegree
        for (int[] p : prerequisites) {
            int a = p[0];
            int b = p[1];
            
            // edge from b -> a
            adj[b].add(a);
            
            // increase indegree of a
            indegree[a]++;
        }

        // queue for BFS
        Queue<Integer> q = new LinkedList<>();
        
        // result array to store order
        int[] result = new int[numCourses];
        
        // pointer for result index
        int idx = 0;

        // add all courses with no prerequisites
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        // process using BFS
        while (!q.isEmpty()) {
            
            // take one course
            int u = q.poll();
            
            // store in result
            result[idx++] = u;

            // visit its neighbors
            for (int v : adj[u]) {
                
                // reduce dependency
                indegree[v]--;
                
                // add if no prerequisite left
                if (indegree[v] == 0) {
                    q.offer(v);
                }
            }
        }

        // if not all courses processed, return empty
        if (idx != numCourses) return new int[0];
        
        // otherwise return valid order
        return result;
    }
}





I use BFS (topological sort) for this problem. I treat courses as a graph and prerequisites as directed edges. First, 
I build the graph and store how many prerequisites each course has using an indegree array. Then I add all courses with no 
prerequisites into a queue. I start processing them one by one, and whenever I complete a course, I reduce the indegree of 
its dependent courses. If any course becomes free, I add it to the queue. While processing, I store the order in a result array. 
At the end, if I processed all courses, I return the order; otherwise, it means there is a cycle, so I return an empty array.