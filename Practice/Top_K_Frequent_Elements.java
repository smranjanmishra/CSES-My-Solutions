class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        
        // size of array
        int n = nums.length;
        
        // map to store frequency of each element
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // count frequency
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // bucket where index = frequency, value = list of elements
        ArrayList<Integer>[] bucket = new ArrayList[n + 1];
        
        // initialize each bucket
        for (int i = 0; i <= n; i++) bucket[i] = new ArrayList<>();

        // fill bucket based on frequency
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int element = entry.getKey();
            int freq = entry.getValue();
            
            // put element in its frequency bucket
            bucket[freq].add(element);
        }

        // result array to store top k elements
        int[] result = new int[k];
        
        // pointer for result
        int idx = 0;

        // traverse bucket from high freq to low
        for (int i = n; i >= 0 && idx < k; i--) {
            
            // if bucket is empty skip
            if (bucket[i].size() == 0) continue;

            // add elements from bucket
            for (int num : bucket[i]) {
                result[idx++] = num;
                
                // stop when k elements found
                if (idx == k) break;
            }
        }

        // return final result
        return result;
    }
}




I’ll solve this using bucket sort. First, I count the frequency of each element using a HashMap. 
Then I create buckets where the index represents the frequency, 
and each bucket stores the elements having that frequency. 
Since the maximum frequency can be n, I create n+1 buckets. 
After that, I iterate from highest frequency to lowest and collect elements until I get k elements.