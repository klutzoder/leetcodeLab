import java.util.HashMap;

/*
 * @lc app=leetcode id=146 lang=java
 *
 * [146] LRU Cache
 */

// @lc code=start
class LRUCache {

	private class Node {
		protected int key;
		protected int val;
		protected Node next;
		protected Node prev;
		protected Node (int key, int val) {
			this.key = key;
			this.val = val;
		}
	}

	private Node head, tail;
	private int capacity;
	private Map<Integer, Node> map;

	private void addToHead(Node node) {
		node.next = head.next;
		node.prev = head;
		head.next.prev = node;
		head.next = node;
	}
 
	protected void removeNode(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node.next = null;
		node.prev = null;
	}

	protected void initHeadTail() {
		head = new Node(-1, -1);
		tail = new Node(-1, -1);
		head.next = tail;
		tail.prev = head;
	}

    public LRUCache(int capacity) {
		this.capacity = capacity;
		this.map = new HashMap<>(capacity);
		initHeadTail();
    }
    
    public int get(int key) {
        if (this.map.containsKey(key)) {
			Node node = this.map.get(key);
			removeNode(node);
			addToHead(node);
			return node.val;
		}
		return -1;
    }
    
    public void put(int key, int value) {
        if (this.map.containsKey(key)) {
			Node node = this.map.get(key);
			node.val = value;
			removeNode(node);
			addToHead(node);
		} else { // not containsKey
			Node node = new Node(key, value);
			if (this.map.size() >= this.capacity) {
				// remove
				Node last = tail.prev;
				this.map.remove(last.key);
				removeNode(last);
			}
			this.map.put(key, node);
			addToHead(node);
		}
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end

