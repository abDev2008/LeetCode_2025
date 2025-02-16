class Solution {
    public int[] constructDistancedSequence(int n) {
        int len = 2 * n - 1;
        int[] result = new int[len];
        boolean[] used = new boolean[n + 1];
        dfs(result, used, 0, n);
        return result;
    }

    private boolean dfs(int[] result, boolean[] used, int index, int n) {
        if (index == result.length) {
            return true;
        }
        if (result[index] != 0) {
            return dfs(result, used, index + 1, n);
        }
        for (int i = n; i >= 1; i--) {
            if (used[i]) continue;
            if (i == 1) {
                result[index] = 1;
                used[i] = true;
                if (dfs(result, used, index + 1, n)) return true;
                result[index] = 0;
                used[i] = false;
            } else {
                int secondIndex = index + i;
                if (secondIndex < result.length && result[secondIndex] == 0) {
                    result[index] = result[secondIndex] = i;
                    used[i] = true;
                    if (dfs(result, used, index + 1, n)) return true;
                    result[index] = result[secondIndex] = 0;
                    used[i] = false;
                }
            }
        }
        return false;
    }
}
