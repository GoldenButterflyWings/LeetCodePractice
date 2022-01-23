import java.time.Instant;
import java.util.*;

public class LRUCache
{
    //https://leetcode.com/problems/lru-cache/

    HashMap<Integer, LRUcacheValue> cacheMap = new HashMap<Integer, LRUcacheValue>();
    int cacheCapacity;

        public LRUCache(int capacity) {
            this.cacheCapacity = capacity;
        }

        public int get(int key) {
            if(cacheMap.containsKey(key))
            {
                cacheMap.put(key,new LRUcacheValue(cacheMap.get(key).getValue(),Instant.now()));
                return cacheMap.get(key).getValue();
            }
            else
                return -1;
        }

        public void put(int key, int value) {
            if(cacheCapacity > cacheMap.size()) //we are not at capacity, so can add more
            {//since we are using a hash map it will also replace the existing one with new value, timestamp
                cacheMap.put(key,new LRUcacheValue(value,Instant.now()));
            }
            else
            {
                if (cacheMap.containsKey(key))
                    cacheMap.put(key,new LRUcacheValue(value,Instant.now()));
                else
                {
                    //find the key with the leastaccessed time
                    cacheMap.remove(getLeastAccessedKey());
                    cacheMap.put(key,new LRUcacheValue(value,Instant.now()));
                }
            }
        }

        private int getLeastAccessedKey()
        {
            //convert hashMap to a list
            ArrayList mapArray = new ArrayList<Map.Entry<Integer, LRUcacheValue>>(cacheMap.entrySet());

            Collections.sort(mapArray, new Comparator<Map.Entry<Integer, LRUcacheValue>>() {
                //create a comparator to compare on LastAccessed time
                @Override
                public int compare(Map.Entry<Integer, LRUcacheValue> o1, Map.Entry<Integer, LRUcacheValue> o2) {
                    return (o1.getValue().getLeastAccessed()).compareTo(o2.getValue().getLeastAccessed());
                }
            });

            Map.Entry<Integer, LRUcacheValue> lastValue =
                    (Map.Entry<Integer, LRUcacheValue>) mapArray.get(0);

            return lastValue.getKey();
        }
}

 class LRUcacheValue {
        int value;
        Instant leastAccessed;

        //Instant instant = Instant.now();

        public LRUcacheValue(int value, Instant timestamp)
        {
            this.value = value;
            this.leastAccessed = timestamp;
        }

     public void setLeastAccessed(Instant leastAccessed) {
         this.leastAccessed = leastAccessed;
     }

     public void setValue(int value) {
         this.value = value;
     }

     public int getValue(){
            return this.value;
     }

     public Instant getLeastAccessed()
     {
         return this.leastAccessed;
     }
 }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

