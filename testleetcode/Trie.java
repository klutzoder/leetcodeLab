package testleetcode;

class Trie {
    
    private class TrieNode {
        protected char val;
        protected TrieNode[] children;
        protected boolean isEnd;
        
        public TrieNode() {}
        public TrieNode(char ch) {
            this.val = ch;
        }
        
        public TrieNode insert(char ch) {
			if (children == null) children = new TrieNode[26];
			if (children[ch-'a'] != null) return children[ch-'a'];
            children[ch-'a'] = new TrieNode(ch);
            return children[ch-'a'];
        }
        
        public void setEnd() {
            this.isEnd = true;
        }
        
        public TrieNode search(char ch) {
            if (children == null) return null;
            return children[ch-'a'];
        }
        
        public boolean isEnd() {
            return isEnd;
        }
    }
    
    private TrieNode root;
    
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        char[] chs = word.toCharArray();
        TrieNode cur = root;
        for (int i = 0; i < chs.length; i++) {
            cur = cur.insert(chs[i]);
        }
        cur.setEnd();
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = searchTrieNode(word);
        return cur != null && cur.isEnd();
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchTrieNode(prefix) != null;
    }
    
    private TrieNode searchTrieNode(String prefix) {
        if (prefix == null || prefix.length() == 0) return null;
        char[] chs = prefix.toCharArray();
        TrieNode cur = root;
        for (int i = 0; i < chs.length; i++) {
            cur = cur.search(chs[i]);
            if (cur == null) return null;
        }
        return cur;
	}
	
	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("app");
		trie.insert("add");
		System.out.println(trie.search("app"));
		System.out.println(trie.startsWith("ap"));
	}
}
