package testleetcode;

import java.util.ArrayList;
import java.util.List;

class FindAllDuplicatesInAnArray {

	public List<Integer> findDuplicates(int[] nums) {
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			int idx = Math.abs(nums[i])-1;
			if (nums[idx] < 0) res.add(Math.abs(nums[idx])+1);
			nums[idx] = -nums[idx];
		}
		return res;
	}

	public static void main(String[] args) {
		new FindAllDuplicatesInAnArray().findDuplicates(new int[]{4,3,2,7,8,2,3,1});
	}
}
