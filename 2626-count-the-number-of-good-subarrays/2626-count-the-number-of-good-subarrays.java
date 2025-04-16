class Solution {
    public long countGood(int[] nums, int k) {
        int len = nums.length;
        HashMap<Integer, Integer> collect = new HashMap<>();
        int cnt = 0;
        int ind = 0;
        long ans = 0;
        for(int i = 0;i < len;i++){
            if(collect.containsKey(nums[i])){
                cnt += collect.get(nums[i]);
                collect.put(nums[i], collect.getOrDefault(nums[i], 0)+1);
            }
            else{
                collect.put(nums[i], 1);
            }
            if(cnt >= k){
                int left = len-i;
                while(ind < len && cnt >= k){
                    ans += left;
                    cnt -= collect.get(nums[ind])-1;
                    collect.put(nums[ind], collect.getOrDefault(nums[ind], 0)-1);
                    ind++;
                }
            }
        }
        return ans;
    }
}