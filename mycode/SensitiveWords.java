package mycode;

public class SensitiveWords {

	private class TrieNode {
		public TrieNode[] children;
		public boolean isEnd;

		public boolean contains(char c) {
			return children != null && children[c-'a'] != null;
		}

		public TrieNode next(char c) {
			return children[c-'a'];
		}
	}

	private TrieNode root;

	public SensitiveWords(String[] sWords) {
		root = new TrieNode();
		for (String w : sWords) {
			this.addWord(w);
		}
	}

	private void addWord(String w) {
		TrieNode cur = root;
		for (char c : w.toCharArray()) {
			int index = c - 'a';
			if (cur.children == null) cur.children = new TrieNode[26];
			if (cur.children[index] == null) cur.children[index] = new TrieNode();
			cur = cur.children[index];
		}
		cur.isEnd = true;
	}

	public String replace(String w) {
		char[] chs = w.toCharArray();
		TrieNode cur = root;
		for (int i = 0; i < chs.length; i++) {
			if (!cur.contains(chs[i])) continue;
			int j = i;
			while (j < chs.length && cur.contains(chs[j])) {
				cur = cur.next(chs[j++]);
			}
			if (cur != null && cur.isEnd) {
				for (; i < j; i++) chs[i] = '*';
			}
			cur = root;
		}
		return new String(chs);
	}

	public static void main(String[] args) {
		String word = "aerfafscklgsdfui";
		SensitiveWords s = new SensitiveWords(new String[]{"fa", "gsdfui"});
		word = s.replace(word);
		System.out.println(word);
	}
}