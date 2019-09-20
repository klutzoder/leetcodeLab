package btreeTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class PostOrderTraversal {

	public List<Integer> postorderTraversal1(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		helper(root, res);
		return res;
	}

	private void helper(TreeNode root, List<Integer> res) {
		if (root == null)
			return;
		helper(root.left, res);
		helper(root.right, res);
		res.add(root.val);
	}


	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) { // left
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode temp = stack.peek().right;
                if (temp != null) cur = temp; // right have node
                else { // right don't have node
                    temp = stack.pop();
                    res.add(temp.val);
                    while (!stack.isEmpty() && temp == stack.peek().right) {
                        temp = stack.pop();
                        res.add(temp.val);
                    }
                }
            }
        }
		return res;
	}


	public static void main(String[] args) {
		TreeNode root= new ConvertSortedArrayToBTree().sortedArrayToBST(new int[] {1,2,3,4,5,6,7,8});
		List<Integer> res = new PostOrderTraversal().postorderTraversal(root);
		System.out.println(Arrays.toString(res.toArray()));
	
	}
}