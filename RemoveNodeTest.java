package com.gac.oj;

import java.util.HashSet;

/**
 * 原链：1 -> 2 -> 3 -> 3 -> 5 -> 5 -> 5 -> 5 
 * 
 * 删除重复后：1 -> 2 -> 3 -> 5
 * 
 * @author gz04766
 *
 */
public class RemoveNodeTest {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Node head = new Node(1);
	     Node head2 = new Node(2);
	     Node head3 = new Node(3);
	     Node head4 = new Node(3);
	     Node head5 = new Node(5);
	     Node head6 = new Node(5);
	     Node head7 = new Node(5);
	     Node head8 = new Node(5);
	     head.next = head2;
	     head2.next = head3;
	     head3.next = head4;
	     head4.next = head5;
	     head5.next = head6;
	     head6.next = head7;
	     head7.next = head8;
	     removeRep1(head);
	     print(head);
	}
	
	public static class Node{
		public int value;
		public Node next; 
		public Node(int value) {
			this.value = value;
		}
	}

	public static void removeRep1(Node head) {
		if (head == null) {
			return;
		}
		HashSet<Integer> set = new HashSet<Integer>();
		Node pre = head;
		Node cur = head.next;
		set.add(head.value);
		
		while (cur != null) {
			if(set.contains(cur.value)) {
				pre.next = cur.next;
			} else {
				set.add(cur.value);
				pre = cur;
			}
			cur = cur.next;
		}
	}
	
	public static void print(Node head) {
		if(head == null) {
			return;
		}
		Node cur = head;
		while (cur != null) {
			System.out.print(cur.value + "->");
			cur = cur.next;
		}
		System.out.println();
	}
}
