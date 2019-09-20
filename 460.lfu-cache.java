import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/*
 * @lc app=leetcode id=460 lang=java
 *
 * [460] LFU Cache
 */
class LFUCache {

	private Map<Integer, Integer> values;
	private Map<Integer, Integer> counts;
	private Map<Integer, LinkedHashSet<Integer>> list;
	private int cap;
	private int min = -1;

	public LFUCache(int capacity) {
		this.cap = capacity;
		this.values = new HashMap<>();
		this.counts = new HashMap<>();
		this.list = new HashMap<>();
		this.list.put(1, new LinkedHashSet<>());
	}

	public int get(int key) {
		if (!values.containsKey(key)) return -1;
		int count = counts.get(key);
		counts.put(key, count+1);
		list.get(count).remove(key);
		if (count==min && list.get(count).size()==0) min++;
		if (!list.containsKey(count+1)) list.put(count+1, new LinkedHashSet<>());
		list.get(count+1).add(key);
		return values.get(key);
	}

	public void put(int key, int value) {
		if (this.cap <= 0) return;
		if (values.containsKey(key)) {
			values.put(key, value);
			get(key);
			return;
		}
		// not contains
		if (values.size() >= this.cap) {
			int oldKey = this.list.get(min).iterator().next();
			this.list.get(min).remove(oldKey);
            values.remove(oldKey);
		} 
		values.put(key, value);
		counts.put(key, 1);
		min = 1;
		list.get(1).add(key);
	}
}

/**
 * Your LFUCache object will be instantiated and called as such: LFUCache obj =
 * new LFUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */
