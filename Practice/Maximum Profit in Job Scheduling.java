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





So the problem is saying we have multiple jobs with start time, end time, and profit, 
and we need to pick some jobs such that they don’t overlap and the total profit is maximum.

So I’m thinking this looks like a decision problem — for every job I can either take it or skip it. 
That usually hints towards DP.

Now if I take a job, I can’t take any overlapping job, so I need to find the next job 
which starts after this one ends. To make this efficient, I’ll sort all jobs based on end time. 
That way, I can use binary search to quickly find the last non-overlapping job.

Then I’ll use a dp array where dp[i] means the maximum profit I can get till the i-th job. 
For each job, I have two choices — either skip it, so profit stays dp[i-1], or take it, 
so I add its profit plus the best profit from the last non-overlapping job.

I take the maximum of these two and store it in dp[i]. Finally, dp[n-1] will give me the answer.