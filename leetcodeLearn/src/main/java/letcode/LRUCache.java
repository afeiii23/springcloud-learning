package letcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package com.curry.warrior.letcode
 * @author: xule
 * @date: 2021/3/1 09:06
 */
public class LRUCache {

    private int capacity;
    private DoubleList doubleList;
    private Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.doubleList = new DoubleList();
        this.map = new HashMap<>(capacity);
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        doubleList.moveToFirst(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        if (map.containsKey(key)) {
            doubleList.remove(map.get(key));
            map.put(key, node);
            doubleList.addFirst(node);
        }else {
            // 容量已经满了
            if (doubleList.getSize() == capacity) {
                map.remove(doubleList.removeLast().key);
            }
            map.put(key, node);
            doubleList.addFirst(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// 双向链表
class DoubleList {
    private Node head;
    private Node tail;
    private int size = 0;

    public DoubleList() {
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        head.pre = null;
        tail.pre = head;
        tail.next = null;
    }

    public void addFirst(Node node) {
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
        size++;
    }

    public void remove(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        size--;
    }

    public Node removeLast() {
        Node last = tail.pre;
        last.pre.next = tail;
        tail.pre = last.pre;
        size--;
        return last;
    }

    public void moveToFirst(Node node) {
        remove(node);
        addFirst(node);
    }

    public int getSize() {
        return size;
    }
}

class Node {

    public int key;
    public int value;
    public Node pre;
    public Node next;

    public Node() {

    }

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

}