package design;

import java.util.LinkedHashMap;
import java.util.Map;

class LRU_LinkedHashMap {

	private LinkedHashMap cache;

	public LRU_LinkedHashMap(int capcity) {
		this.cache = new LinkedHashMap<>(capcity, 0.75, true){
			@Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > cacheSize;
            }
		};
	}
}