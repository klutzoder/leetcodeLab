package design;

import java.util.LinkedHashMap;
import java.util.Map;

class LRU_LinkedHashMap {

	private LinkedHashMap<Integer, Integer> cache;
	

	public LRU_LinkedHashMap(final int capcity) {
		this.cache = new LinkedHashMap<Integer, Integer>(capcity) {
			@Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capcity;
            }
		};
	}

	public void put(int key, int val) {
		this.cache.put(key, val);
	}

	public int get(int key) {
		if (!this.cache.containsKey(key)) return -1;
		return this.cache.get(key);
	}

	public static void main(String[] args) {
		LRU_LinkedHashMap lru = new LRU_LinkedHashMap(10);
		for (int i = 0; i < 100; i++) {
			lru.put(i, i);
		}

		for (int i = 0; i < 100; i++) {
			int res = lru.get(i);
			if (res == -1) continue;
			System.out.println(res);
		}
	}
}