import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=820 lang=java
 *
 * [820] Short Encoding of Words
 *
 * https://leetcode.com/problems/short-encoding-of-words/description/
 *
 * algorithms
 * Medium (48.87%)
 * Likes:    190
 * Dislikes: 50
 * Total Accepted:    10.7K
 * Total Submissions: 22K
 * Testcase Example:  '["time", "me", "bell"]'
 *
 * Given a list of words, we may encode it by writing a reference string S and
 * a list of indexes A.
 * 
 * For example, if the list of words is ["time", "me", "bell"], we can write it
 * as S = "time#bell#" and indexes = [0, 2, 5].
 * 
 * Then for each index, we will recover the word by reading from the reference
 * string from that index until we reach a "#" character.
 * 
 * What is the length of the shortest reference string S possible that encodes
 * the given words?
 * 
 * Example:
 * 
 * 
 * Input: words = ["time", "me", "bell"]
 * Output: 10
 * Explanation: S = "time#bell#" and indexes = [0, 2, 5].
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= words.length <= 2000.
 * 1 <= words[i].length <= 7.
 * Each word has only lowercase letters.
 * 
 * 
 */

// @lc code=start
class Solution1 {

	private class TrieNode {
		TrieNode[] children;
		int count;

		public TrieNode() {
			children = new TrieNode[26];
			count = 0;
		}

		public TrieNode get(char c) {
			if (children[c - 'a'] == null) {
				children[c - 'a'] = new TrieNode();
				count++;
			}
			return children[c - 'a'];
		}
	}

	public int minimumLengthEncoding(String[] words) {
		TrieNode trie = new TrieNode();
		Map<TrieNode, Integer> nodes = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			TrieNode cur = trie;
			for (int j = word.length() - 1; j >= 0; j--) {
				cur = cur.get(word.charAt(j));
			}
			nodes.put(cur, i);
		}
		int ans = 0;
		for (TrieNode node : nodes.keySet()) {
			if (node.count == 0) {
				ans += words[nodes.get(node)].length() + 1;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		new Solution1().minimumLengthEncoding(new String[] { "time", "me", "bell" });
	}
}
// @lc code=end
