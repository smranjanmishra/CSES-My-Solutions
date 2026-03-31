public class Solution {
    public int subarraySum(int[] nums, int k) {
        int result = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            sum += nums[i];

            if (map.containsKey(sum - k))
                result += map.get(sum - k);

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return result;
    }
}




I’ll use prefix sum with a HashMap. I keep a running sum while traversing the array. 
At each index, I check if there was a previous prefix sum equal to (currentSum - k). 
If yes, that means the subarray between those two indices has sum k. 
I store the frequency of prefix sums in a map so I can count how many such subarrays exist. 
Now I can count all subarrays with sum k in one pass.