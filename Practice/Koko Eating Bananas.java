class Solution {

    // helper function to check if Koko can eat all bananas at given speed
    private boolean canEatAll(int[] piles, int speed, int h) {
        
        // total hours needed
        int actualHour = 0;

        // iterate over each pile
        for (int x : piles) {
            
            // add full hours
            actualHour += x / speed;

            // if some bananas left, take one extra hour
            if (x % speed != 0) {
                actualHour++;
            }
        }

        // return true if within allowed hours
        return actualHour <= h;
    }

    public int minEatingSpeed(int[] piles, int h) {
        
        // minimum possible speed
        int l = 1;
        
        // maximum pile size → max possible speed
        int r = 0;
        for (int x : piles) {
            r = Math.max(r, x);
        }

        // binary search to find minimum valid speed
        while (l < r) {
            
            // mid speed
            int mid = l + (r - l) / 2;

            // check if mid speed works
            if (canEatAll(piles, mid, h)) {
                
                // try smaller speed
                r = mid;
            } else {
                
                // need higher speed
                l = mid + 1;
            }
        }

        // answer is minimum valid speed
        return l;
    }
}




I’ll solve this using binary search on the answer. The eating speed k lies between 1 and the maximum pile size. For any given speed, 
I can check if Koko can finish all bananas within h hours. To do that, I go through each pile and calculate how many hours 
it will take using that speed. If the total hours is less than or equal to h, then that speed works, so I try to minimize it by searching 
on the left side. Otherwise, I increase the speed. At the end, I get the minimum speed that allows Koko to finish all bananas.