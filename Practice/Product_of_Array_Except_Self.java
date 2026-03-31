class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ans[0] = 1;
        for(int i=1; i<n; i++) {
            ans[i] = ans[i-1] * nums[i-1];
        }
        int suffix = 1;
        for(int j=n-1; j>=0; j--) {
            ans[j] = ans[j] * suffix;
            suffix = suffix * nums[j];
        }
        return ans;
    }
}




I’ll solve this without using division by using prefix and suffix products. 
First, I create an answer array where each position stores the product of all elements to the left of that index. 
Then I traverse from right to left and keep a running suffix product, 
which represents the product of elements to the right. At each index, I multiply the prefix (already stored) 
with the suffix to get the final answer.