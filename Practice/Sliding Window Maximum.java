class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        // deque to store elements in decreasing order
        ArrayDeque<Integer> q = new ArrayDeque<>();
        
        // window pointers
        int i = 0, j = 0;
        
        // pointer for result array
        int ptr = 0;
        
        int n = nums.length;
        
        // result array
        int[] res = new int[n - k + 1];

        // traverse array
        while (j < n) {
            
            // remove all smaller elements from back
            while (!q.isEmpty() && q.peekLast() < nums[j]) {
                q.pollLast();
            }

            // add current element
            q.add(nums[j]);

            // window size not reached
            if (j - i + 1 < k) {
                j++;
            }
            // window size reached
            else if (j - i + 1 == k) {

                // front of deque is maximum
                res[ptr++] = q.peek();

                // remove element going out of window
                if (nums[i] == q.peek()) {
                    q.pollFirst();
                }

                // slide window
                i++;
                j++;
            }
        }

        return res;
    }
}






So the problem is saying we have a sliding window of size k, and for every window we need to find the maximum.

First I use a deque.

The idea is to keep elements in decreasing order in the deque. 
So whenever a new element comes, I remove all smaller elements from the back, 
because they will never be useful.

The front of the deque will always store the maximum element of the current window.

When the window moves, if the element going out is equal to the front, I remove it.