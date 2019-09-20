/*
 * @lc app=leetcode id=2 lang=java
 *
 * [2] Add Two Numbers
 */
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0), p1 = l1, p2 = l2;
		ListNode cur = dummy;
		
		int carry = 0;
		while (p1 != null || p2 != null) {
			int num1 = p1 == null ? 0 : p1.val;
			int num2 = p2 == null ? 0 : p2.val;
			int sum = num1 + num2 + carry;
			cur.next = new ListNode(sum % 10);
			carry = sum / 10;
			cur = cur.next;
			if (p1 != null) p1 = p1.next;
			if (p2 != null) p2 = p2.next;
		}
		if (carry != 0) cur.next = new ListNode(carry);
		return dummy.next;
	}
}
