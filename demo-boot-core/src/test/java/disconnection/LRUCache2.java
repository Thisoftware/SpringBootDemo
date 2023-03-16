package disconnection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author kis
 * @since 2023/03/10 16:05:45
 */
public class LRUCache2<K, V> {

    private final int maxSize;

    private final ConcurrentHashMap<K, V> map;

    private final ConcurrentLinkedQueue<K> queue;

    public LRUCache2(final int maxSize) {
        this.maxSize = maxSize;
        map = new ConcurrentHashMap<K, V>(maxSize);
        queue = new ConcurrentLinkedQueue<K>();
    }

    public void put(final K key, final V value) {
        if (map.containsKey(key)) {
            // remove the key from the FIFO queue
            queue.remove(key);
        }

        while (queue.size() >= maxSize) {
            K oldestKey = queue.poll();
            if (null != oldestKey) {
                map.remove(oldestKey);
            }
        }
        queue.add(key);
        map.put(key, value);
    }

    public V get(final K key) {

        if (map.containsKey(key)) {
            // remove from queue and add it again in FIFO queue
            queue.remove(key);
            queue.add(key);
        }
        return map.get(key);
    }


}
