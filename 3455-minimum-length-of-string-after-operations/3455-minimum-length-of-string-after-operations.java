class Solution {
    public int minimumLength(String s) {
        
        int[] charFreq = new int[26];
        
        for (char ch : s.toCharArray()) {
            charFreq[ch - 'a']++;
        }
        
        int res = 0;
        for (int freq : charFreq) {
            if (freq > 0) {
                res += 1 + ((freq % 2 == 0) ? 1 : 0);
            }
        }
        
        return res;
    }
}