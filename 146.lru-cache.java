import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=146 lang=java
 *
 * [146] LRU Cache
 */
class LRUCache {

	private class Node {
		protected int key;
		protected int val;
		protected Node prev;
		protected Node next;

		public Node(int key, int val) {
			this.key = key;
			this.val = val;
		}
	}

	private Map<Integer, Node> map;
	private Node head;
	private Node tail;
	private int capacity;

	private void addHead(Node node) {
		node.next = this.head.next;
		this.head.next.prev = node;
		this.head.next = node;
		node.prev = this.head;
	}

	private void removeNode(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node.prev = null;
		node.next = null;
	}

	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.map = new HashMap<>(capacity);
		this.head = new Node(-1, -1);
		this.tail = new Node(-1, -1);
		this.head.next = this.tail;
		this.tail.prev = this.head;
	}

	public int get(int key) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			removeNode(node);
			addHead(node);
			return node.val;
		}
		return -1;
	}

	public void put(int key, int value) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			node.val = value;
			removeNode(node);
			addHead(node);
		} else {
			Node node = new Node(key, value);
			if (map.size() >= this.capacity) {
				map.remove(this.tail.prev.key);
				removeNode(this.tail.prev);
			}
			addHead(node);
			map.put(key, node);
		}
	}
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */
