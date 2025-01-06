class Solution {
    public int[] minOperations(String boxes) {
        int n=boxes.length();
        
        int[] arrBox=new int[n];
        for(int i=0; i<n; i++){
            arrBox[i]=boxes.charAt(i)-'0';
        }

        int[] res=new int[n]; 

        int oneCntL=0, cumSumL=0;
        int oneCntR=0, cumSumR=0;
        int i=0, j=n-1;
        while(i<n && j>=0){
            // Left to right pass moving count
            res[i]+=cumSumL;
            oneCntL+=arrBox[i];
            cumSumL+=oneCntL;
            i++;

            // Right to left pass moving count    
            res[j]+=cumSumR;
            oneCntR+=arrBox[j];
            cumSumR+=oneCntR;
            j--;
        }


        return res;
    }
}