class Solution {
    
    // store original array
    private int[] nums;
    
    // random object for generating random indices
    private Random random;

    // constructor to initialize array and random generator
    public Solution(int[] nums) {
        this.nums = nums;
        this.random = new Random();
    }

    // return original array (reset state)
    public int[] reset() {
        return nums;
    }

    // shuffle array using Fisher-Yates algorithm
    // TC : O(n)
    public int[] shuffle() {
        
        // create a copy so original array is not modified
        int[] randomArray = nums.clone();

        // iterate from last index to first
        for (int i = nums.length - 1; i > 0; i--) {
            
            // pick random index from 0 to i
            int j = random.nextInt(i + 1);
            
            // swap elements at i and j
            int t = randomArray[i];
            randomArray[i] = randomArray[j];
            randomArray[j] = t;
        }

        // return shuffled array
        return randomArray;
    }
}