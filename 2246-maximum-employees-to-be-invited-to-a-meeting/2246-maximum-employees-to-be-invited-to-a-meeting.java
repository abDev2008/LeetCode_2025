class Solution {
    public static int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] inDegree = new int[n];
        int[] depth = new int[n];
        int[] queue = new int[n];

        for (int fav : favorite) {
            inDegree[fav]++;
        }

        int queueStart = 0, queueEnd = 0;
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue[queueEnd++] = i;
            }
        }

        while (queueStart < queueEnd) {
            int current = queue[queueStart++];
            int next = favorite[current];
            depth[next] = Math.max(depth[next], depth[current] + 1);
            if (--inDegree[next] == 0) {
                queue[queueEnd++] = next;
            }
        }

        int smallCircleSum = 0, maxCycleLength = 0;

        for (int i = 0; i < n; i++) {
            if (inDegree[i] > 0) {
                inDegree[i] = 0;
                int cycleSize = 1;

                for (int j = favorite[i]; j != i; j = favorite[j]) {
                    cycleSize++;
                    inDegree[j] = 0;
                }

                if (cycleSize == 2) {
                    smallCircleSum += depth[i] + depth[favorite[i]] + 2;
                } else {
                    maxCycleLength = Math.max(maxCycleLength, cycleSize);
                }
            }
        }

        return Math.max(maxCycleLength, smallCircleSum);
    }
}
