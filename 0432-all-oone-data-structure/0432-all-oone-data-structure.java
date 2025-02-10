import java.util.*;

class AllOne {
    // Bucket class represents a node in the doubly linked list.
    private class Bucket {
        int count;
        LinkedHashSet<String> keys;
        Bucket prev, next;

        public Bucket(int count) {
            this.count = count;
            keys = new LinkedHashSet<>();
        }
    }

    // Dummy head and tail to avoid edge-case checks.
    private Bucket head, tail;
    // Map from key to its bucket.
    private Map<String, Bucket> keyBucketMap;
    // Map from count to the bucket with that count.
    private Map<Integer, Bucket> countBucketMap;

    /** Initializes the data structure. */
    public AllOne() {
        head = new Bucket(Integer.MIN_VALUE);
        tail = new Bucket(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
        keyBucketMap = new HashMap<>();
        countBucketMap = new HashMap<>();
    }

    /**
     * Inserts newBucket into the doubly linked list right after prevBucket.
     */
    private void addBucketAfter(Bucket newBucket, Bucket prevBucket) {
        newBucket.prev = prevBucket;
        newBucket.next = prevBucket.next;
        prevBucket.next.prev = newBucket;
        prevBucket.next = newBucket;
        countBucketMap.put(newBucket.count, newBucket);
    }

    /**
     * Removes bucket from the doubly linked list.
     */
    private void removeBucket(Bucket bucket) {
        bucket.prev.next = bucket.next;
        bucket.next.prev = bucket.prev;
        countBucketMap.remove(bucket.count);
    }

    /**
     * Increments the count of key by 1.
     * If key does not exist, insert it with count = 1.
     */
    public void inc(String key) {
        if (keyBucketMap.containsKey(key)) {
            Bucket curBucket = keyBucketMap.get(key);
            int newCount = curBucket.count + 1;
            Bucket nextBucket = curBucket.next;
            if (nextBucket == tail || nextBucket.count != newCount) {
                Bucket newBucket = new Bucket(newCount);
                addBucketAfter(newBucket, curBucket);
                nextBucket = newBucket;
            }
            nextBucket.keys.add(key);
            keyBucketMap.put(key, nextBucket);
            curBucket.keys.remove(key);
            if (curBucket.keys.isEmpty()) {
                removeBucket(curBucket);
            }
        } else {
            // New key: count will be 1.
            int newCount = 1;
            Bucket bucket1 = head.next;
            if (bucket1 == tail || bucket1.count != newCount) {
                bucket1 = new Bucket(newCount);
                addBucketAfter(bucket1, head);
            }
            bucket1.keys.add(key);
            keyBucketMap.put(key, bucket1);
        }
    }

    /**
     * Decrements the count of key by 1.
     * If the count becomes 0, remove key from the data structure.
     * It is guaranteed that key exists.
     */
    public void dec(String key) {
        Bucket curBucket = keyBucketMap.get(key);
        int curCount = curBucket.count;
        if (curCount == 1) {
            // Remove the key entirely.
            keyBucketMap.remove(key);
            curBucket.keys.remove(key);
            if (curBucket.keys.isEmpty()) {
                removeBucket(curBucket);
            }
        } else {
            int newCount = curCount - 1;
            Bucket prevBucket = curBucket.prev;
            if (prevBucket == head || prevBucket.count != newCount) {
                Bucket newBucket = new Bucket(newCount);
                addBucketAfter(newBucket, curBucket.prev);
                prevBucket = newBucket;
            }
            prevBucket.keys.add(key);
            keyBucketMap.put(key, prevBucket);
            curBucket.keys.remove(key);
            if (curBucket.keys.isEmpty()) {
                removeBucket(curBucket);
            }
        }
    }

    /**
     * Returns one of the keys with the maximal count.
     * If no key exists, returns an empty string.
     */
    public String getMaxKey() {
        if (tail.prev == head) return "";
        // LinkedHashSet guarantees fast access to an arbitrary (in this case, first) element.
        return tail.prev.keys.iterator().next();
    }

    /**
     * Returns one of the keys with the minimal count.
     * If no key exists, returns an empty string.
     */
    public String getMinKey() {
        if (head.next == tail) return "";
        return head.next.keys.iterator().next();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
