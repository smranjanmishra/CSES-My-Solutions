class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        // create adjacency list to store graph
        ArrayList<Integer>[] adj = new ArrayList[numCourses];
        
        // initialize each list
        for (int i = 0; i < numCourses; i++) adj[i] = new ArrayList<>();

        // array to store number of prerequisites
        int[] indegree = new int[numCourses];

        // build graph and fill indegree
        for (int[] p : prerequisites) {
            int a = p[0], b = p[1];
            
            // edge from b -> a
            adj[b].add(a);
            
            // increase indegree of a
            indegree[a]++;
        }

        // queue for BFS
        Queue<Integer> q = new LinkedList<>();
        
        // count processed courses
        int count = 0;

        // add all courses with no prerequisites
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                count++;
            }
        }

        // process using BFS
        while (!q.isEmpty()) {
            
            // take one course
            int u = q.poll();
            
            // visit its neighbors
            for (int v : adj[u]) {
                
                // reduce dependency
                if (--indegree[v] == 0) {
                    
                    // add if no prerequisite left
                    q.offer(v);
                    count++;
                }
            }
        }

        // if all courses processed, return true
        return count == numCourses;
    }
}





I’ll use BFS for this problem. I treat courses like a graph, where if one course depends on another, 
I create a direction between them. Then I count how many prerequisites each course has using an indegree 
array. First, I put all courses with no prerequisites into a queue. Then I start processing them one by one 
using BFS. Whenever I finish a course, I reduce the dependency of the next courses. If any course 
becomes free (indegree becomes 0), I add it to the queue. At the end, 
if I’m able to process all courses, that means there is no cycle and we can finish everything. 
If some courses are left, it means there is a cycle, so it’s not possible.