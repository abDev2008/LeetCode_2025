class Solution {
    public long helper(int index, int[][] questions, long[]dp){
        if(index == questions.length-1){
            return questions[index][0];
        }
        if(dp[index] != -1)
            return dp[index];
        long pos1 = questions[index][0];
        if(index+questions[index][1]+1 < questions.length)
            pos1 += helper(index+questions[index][1]+1, questions,dp);
        long pos2 = helper(index+1,questions,dp);

        return dp[index] = Math.max(pos1,pos2);
    }
    public long mostPoints(int[][] questions) {
        long[] dp = new long[questions.length+1];
        Arrays.fill(dp,-1);
        return helper(0,questions,dp);
    }
}