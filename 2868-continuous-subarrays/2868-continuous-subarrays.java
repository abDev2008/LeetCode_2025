class Solution {
    public long continuousSubarrays(int[] array) {
        int length = array.length;
        int leftPointer = 0;
        long totalSubarrays = 0;

        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int rightPointer = 0; rightPointer < length; rightPointer++) {
            frequencyMap.put(array[rightPointer], frequencyMap.getOrDefault(array[rightPointer], 0) + 1);

            while ((rightPointer - leftPointer + 1) > getValidCount(array[rightPointer], frequencyMap)) {
                frequencyMap.put(array[leftPointer], frequencyMap.get(array[leftPointer]) - 1);

                leftPointer++;
            }

            totalSubarrays += (rightPointer - leftPointer + 1);
        }

        return totalSubarrays;
    }

    private int getValidCount(int num, Map<Integer, Integer> frequencyMap) {
        return frequencyMap.getOrDefault(num, 0) + frequencyMap.getOrDefault(num - 1, 0) +
               frequencyMap.getOrDefault(num + 1, 0) + frequencyMap.getOrDefault(num - 2, 0) +
               frequencyMap.getOrDefault(num + 2, 0);
    }
}
