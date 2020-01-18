package mycode;

import java.util.HashMap;
import java.util.Map;

public class ThrowEggs {

	public static void main(String[] args) {
		int n = 100; // 100 层
		int k = 2; // 2 鸡蛋
		int minNum = new ThrowEggs().eggsThrow(n, k, new HashMap<>());
		System.out.println(minNum);
	}

	private int eggsThrow(int n, int k, Map<Integer, Map<Integer, Integer>> map) {
		if (k == 1) return n;
		if (n == 0) return 0;
		if (map.containsKey(n) && map.get(n).containsKey(k)) return map.get(n).get(k);
		int res = Integer.MAX_VALUE;
		for (int i = 1; i <= n; i++) {
			res = Math.min(Math.max(eggsThrow(n-i, k, map), eggsThrow(i-1, k-1, map)) + 1, res);
		}
		map.putIfAbsent(n, new HashMap<>());
		map.get(n).put(k, res);
		return res;
	}
}