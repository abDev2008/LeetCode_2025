import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class AllOne {

    private class Bucket {
        int count;             
        Set<String> keys;     
        Bucket prev;           
        Bucket next;            

        Bucket(int count) {
            this.count = count;
            keys = new HashSet<>();
        }
    }
    
    private Bucket head;
    private Bucket tail;
    
    private Map<String, Bucket> keyToBucket;
    private Map<Integer, Bucket> countToBucket;
    
    public AllOne() {
        head = new Bucket(Integer.MIN_VALUE);
        tail = new Bucket(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
        
        keyToBucket = new HashMap<>();
        countToBucket = new HashMap<>();
    }
    

    private void insertBucketAfter(Bucket newBucket, Bucket bucket) {
        newBucket.prev = bucket;
        newBucket.next = bucket.next;
        bucket.next.prev = newBucket;
        bucket.next = newBucket;
        countToBucket.put(newBucket.count, newBucket);
    }
    

    private void removeBucket(Bucket bucket) {
        bucket.prev.next = bucket.next;
        bucket.next.prev = bucket.prev;
        countToBucket.remove(bucket.count);
    }
    

    public void inc(String key) {
        if (keyToBucket.containsKey(key)) {
            Bucket curBucket = keyToBucket.get(key);
            int newCount = curBucket.count + 1;
            Bucket nextBucket = curBucket.next;
            Bucket newBucket;
            
            if (nextBucket == tail || nextBucket.count != newCount) {
                newBucket = new Bucket(newCount);
                insertBucketAfter(newBucket, curBucket);
            } else {
                newBucket = nextBucket;
            }
            
            newBucket.keys.add(key);
            keyToBucket.put(key, newBucket);
            curBucket.keys.remove(key);
            
            if (curBucket.keys.isEmpty()) {
                removeBucket(curBucket);
            }
        } else {
            int newCount = 1;
            Bucket bucket1;
            
            if (head.next == tail || head.next.count != newCount) {
                bucket1 = new Bucket(newCount);
                insertBucketAfter(bucket1, head);
            } else {
                bucket1 = head.next;
            }
            
            bucket1.keys.add(key);
            keyToBucket.put(key, bucket1);
        }
    }
    

    public void dec(String key) {
        Bucket curBucket = keyToBucket.get(key);
        int curCount = curBucket.count;
        
        if (curCount == 1) {
            keyToBucket.remove(key);
            curBucket.keys.remove(key);
            if (curBucket.keys.isEmpty()) {
                removeBucket(curBucket);
            }
        } else {
            int newCount = curCount - 1;
            Bucket prevBucket = curBucket.prev;
            Bucket newBucket;
            
            if (prevBucket == head || prevBucket.count != newCount) {
                newBucket = new Bucket(newCount);
                insertBucketAfter(newBucket, curBucket.prev);
            } else {
                newBucket = prevBucket;
            }
            
            newBucket.keys.add(key);
            keyToBucket.put(key, newBucket);
            curBucket.keys.remove(key);
            
            if (curBucket.keys.isEmpty()) {
                removeBucket(curBucket);
            }
        }
    }
    

    public String getMaxKey() {
        if (tail.prev == head) {
            return "";
        }
        return tail.prev.keys.iterator().next();
    }
    

    public String getMinKey() {
        if (head.next == tail) {
            return "";
        }
        return head.next.keys.iterator().next();
    }
}
