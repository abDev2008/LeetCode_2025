import java.util.*;

public class Solution {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int ans : answers) {
            freq.put(ans, freq.getOrDefault(ans, 0) + 1);
        }

        int result = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int k = entry.getKey();
            int count = entry.getValue();
            int groupSize = k + 1;
            int numGroups = (int) Math.ceil((double) count / groupSize);
            result += numGroups * groupSize;
        }

        return result;
    }
}