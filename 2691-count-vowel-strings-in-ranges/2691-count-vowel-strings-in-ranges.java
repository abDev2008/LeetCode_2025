class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n=words.length;
        int m=queries.length;
        int[] ans=new int[m];
        Set<Character> set=new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));;

        int[] sum=new int[n];
        sum[0]=( set.contains(words[0].charAt(0)) && set.contains(words[0].charAt(words[0].length()-1)) )?1:0;
        for(int i=1; i<n; i++){
            if( set.contains(words[i].charAt(0)) && set.contains(words[i].charAt(words[i].length()-1)) ){
                sum[i]=sum[i-1]+1;
            }
            else{
                sum[i]=sum[i-1];
            }
        }
        int prev=0;
        for(int i=0; i<m; i++){
            prev=queries[i][0]-1<0?0:sum[queries[i][0]-1];
            ans[i]=sum[queries[i][1]] - prev;
        }
        return ans;
    }
}