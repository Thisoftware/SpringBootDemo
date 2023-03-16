package disconnection;

import java.util.Hashtable;

/**
 * @author kis
 * @since 2023/03/10 16:15:45
 */
public class LRUCache3 {

    private final Hashtable<Integer, LinkedNode> cache = new Hashtable<>();
    private int count;
    private final int capacity;
    private final LinkedNode head, tail;

    public LRUCache3(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        head = new LinkedNode();
        head.pre = null;

        tail = new LinkedNode();
        tail.next = null;

        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        LinkedNode node = cache.get(key);
        if(node == null){
            return -1; // should raise exception here.
        }

        // move the accessed node to the head;
        this.moveToHead(node);

        return node.value;
    }


    public void put(int key, int value) {
        LinkedNode node = cache.get(key);

        if(node == null){
            LinkedNode newNode = new LinkedNode();
            newNode.key = key;
            newNode.value = value;

            this.cache.put(key, newNode);
            this.addNode(newNode);

            ++count;

            if(count > capacity){
                // pop the tail
                LinkedNode tail = this.popTail();
                this.cache.remove(tail.key);
                --count;
            }
        }else{
            // update the value.
            node.value = value;
            this.moveToHead(node);
        }
    }
    /**
     * Always add the new node right after head;
     */
    private void addNode(LinkedNode node){
        node.pre = head;
        node.next = head.next;

        head.next.pre = node;
        head.next = node;
    }

    /**
     * Remove an existing node from the linked list.
     */
    private void removeNode(LinkedNode node){
        LinkedNode pre = node.pre;
        LinkedNode next = node.next;

        pre.next = next;
        next.pre = pre;
    }

    /**
     * Move certain node in between to the head.
     */
    private void moveToHead(LinkedNode node){
        this.removeNode(node);
        this.addNode(node);
    }

    // pop the current tail.
    private LinkedNode popTail(){
        LinkedNode res = tail.pre;
        this.removeNode(res);
        return res;
    }

    static class LinkedNode {
        int key;
        int value;
        LinkedNode pre;
        LinkedNode next;
    }

}
