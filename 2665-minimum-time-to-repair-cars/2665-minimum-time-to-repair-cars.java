class Solution {
    public long repairCars(int[] ranks, int cars) {
        long minVal = 10000000;
        
        for (int rank : ranks) {
            minVal = Math.min(minVal, rank);
        }
        
        long l = minVal, r = minVal * (long) cars * cars, mid, ans = 0;
        
        while (l <= r) {
            mid = (l + r) / 2;
            if (isPossible(ranks, cars, mid)) {
                ans = mid;  
                r = mid - 1; 
            } else {
                l = mid + 1;
            }
        }
        
        return ans;
    }
    
    public boolean isPossible(int[] ranks, int cars, long minutes) {
        for (int rank : ranks) {
            cars -= (int) Math.sqrt(minutes / rank);
            if (cars <= 0) {
                return true;  
            }
        }
        
        return false; 
    }
}