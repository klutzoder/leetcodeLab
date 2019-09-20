/*
 * @lc app=leetcode id=206 lang=java
 *
 * [206] Reverse Linked List
 */
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
	public ListNode reverseList(ListNode head) {
		ListNode dummy = new ListNode(0), cur = head, pre = dummy;
		dummy.next = head;
		while (cur != null && cur.next != null) {
			ListNode next = cur.next;
			cur.next = next.next;
			next.next = pre.next;
			pre.next = next;
		}
		return dummy.next;
	}
}
