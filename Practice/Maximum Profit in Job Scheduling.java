class Solution {

    // binary search to find last job which ends <= current job start
    private int binarySearch(int[][] jobs, int start, int left, int right) {
        
        // store index of valid job
        int result = -1;

        // standard binary search
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // if job end time <= current start, it's valid
            if (jobs[mid][1] <= start) {
                result = mid;      // store index
                left = mid + 1;   // try to find closer one on right
            } else {
                right = mid - 1;  // move left
            }
        }

        // return index of last non-overlapping job
        return result;
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        
        // number of jobs
        int n = startTime.length;

        // combine start, end, profit into one array
        int[][] jobs = new int[n][3];

        // fill jobs array
        for (int i = 0; i < n; i++) {
            jobs[i][0] = startTime[i]; // start time
            jobs[i][1] = endTime[i];   // end time
            jobs[i][2] = profit[i];    // profit
        }

        // sort jobs by end time
        Arrays.sort(jobs, Comparator.comparingInt(m -> m[1]));

        // dp[i] = max profit till job i
        int[] dp = new int[n];
        
        // base case: first job
        dp[0] = jobs[0][2];

        // process remaining jobs
        for (int i = 1; i < n; i++) {
            
            // profit if we take current job
            int prev = 0;

            // find last non-overlapping job
            int lastJobIndex = binarySearch(jobs, jobs[i][0], 0, i - 1);

            // if found, add its profit
            if (lastJobIndex != -1) {
                prev = dp[lastJobIndex];
            }

            // choose max of taking or skipping
            dp[i] = Math.max(prev + jobs[i][2], dp[i - 1]);
        }

        // final answer
        return dp[n - 1];
    }
}