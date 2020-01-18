import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=1032 lang=java
 *
 * [1032] Stream of Characters
 */

// @lc code=start
class StreamChecker {

    private class Node {
        Node[] children = new Node[26]; // 26 lowercase
        boolean isWord;
        
        public Node insert(char c) {
            if (children[c-'a'] == null) {
                children[c-'a'] = new Node();
            }
            return children[c-'a'];
        }
        
        public void setIsWord() {
            this.isWord = true;
        }
        
        public Node next(char c) {
            if (children[c-'a'] == null) return null;
            return children[c-'a'];
        }
    }
    
    private Node prefix;
    private List<Character> str;
    
    private void buildPrefix(String w) {
        char[] chs = w.toCharArray();
        Node cur = prefix;
        for (int i = chs.length-1; i >= 0; i--) {
            cur = cur.insert(chs[i]);
        }
        cur.setIsWord();
    }
    
    public StreamChecker(String[] words) {
        prefix = new Node();
        str = new ArrayList<>();
        for (String w : words) {
            buildPrefix(w);
        }
    }
    
    public boolean query(char letter) {
        Node cur = prefix;
        str.add(letter);
        for (int i = str.size()-1; i >= 0; i--) {
            cur = cur.next(str.get(i));
            if (cur == null) return false;
            if (cur.isWord) return true;
        }
        return false;
    }
}
// @lc code=end

