class Solution {
    public List<Integer> partitionLabels(String s) {

        Map<Character, Integer> lastOccurrence = new HashMap<>();
        for (int index = 0; index < s.length(); index++) {
            lastOccurrence.put(s.charAt(index), index);
        }

        List<Integer> partitionSizes = new ArrayList<>();
        int partitionStart = 0, partitionEnd = 0;

        for (int index = 0; index < s.length(); index++) {
            
            partitionEnd = Math.max(partitionEnd, lastOccurrence.get(s.charAt(index)));
            if (index == partitionEnd) {
                partitionSizes.add(partitionEnd - partitionStart + 1);
                partitionStart = index + 1;
            }
        }
        return partitionSizes;
    }
}