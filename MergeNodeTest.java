package com.gac.oj;

import com.gac.oj.RemoveNodeTest.Node;

/**
 * 重复数据会被合并掉
 *  1 -> 2 -> 4 -> 6
 *  1 -> 2 -> 3 -> 7
 * 不删除重复：1 -> 1 -> 2 -> 2 -> 3 -> 4 -> 6 -> 7
 * 不删除重复的实现，移除画***处判断即可
 *  删除重复：1 -> 2 -> 3 -> 4 -> 6 -> 7
 * 
 * @author gz04766 JackOu
 *
 */


public class MergeNodeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node node10 = new Node(2); 
		Node node11 = new Node(3); 
		Node node12 = new Node(4); 
		Node node13 = new Node(6); 
		node10.next = node11;
		node11.next = node12;
		node12.next = node13;
		
		Node node20 = new Node(1); 
		Node node21 = new Node(2);
		Node node22 = new Node(5);
		Node node23 = new Node(7);
		node20.next = node21;
		node21.next = node22;
		node22.next = node23;
		
		Node node = mergeList2(node10, node20);
		while (node != null) {
		   System.out.print(node.value+ " -> ");
		   node = node.next;
		}
	}

	public static class Node{
		public int value;
		public Node next; 
		public Node(int value) {
			this.value = value;
		}
	}
	
	//递归
	public static Node mergeList1(Node node1,Node node2) {
		if(node1 == null && node2 != null) {
			return node2;
		} else if (node1 != null && node2 == null) {
			return node1;
		} else if (node1 == null && node2 == null) {
			return node1;
		}
		Node head = null;
		if (node1.value >= node2.value) {
			//把值小的节点往head链接
			head = node2;
			/*****修复后的链表重复*****/
			if (node1.value == node2.value) {
				node1 = node1.next;
			}
			/**************************/
			head.next = mergeList1(node1,node2.next);
		} else {
			head = node1;
			head.next = mergeList1(node1.next,node2);
		}
		return head;
	}
	
	//非递归
	public static Node mergeList2(Node node1,Node node2) {
		if (node1 == null || node2 == null){
			return node1 != null ? node1 :node2;
		}	
		//合并后单链表头结点赋值
        Node head = node1.value < node2.value ? node1 : node2;
        
        //cur1前一个元素
		Node cur1 = head == node1 ? node1 : node2;
		//cur2的后一个元素
		Node cur2 = head == node1 ? node2 : node1;

		//pre控制第一条链位置
		//next控制第二条链位置
		Node pre = null;
		Node next = null;

		while (cur1 != null && cur2 != null) {
		    	//根据Cur1和Cur2的创建过程，保证cur1第一轮遍历一定比cur2小。第一次进来肯定走这里
			 	if (cur1.value </*不删除方案，"<" 变成"<="  */ cur2.value) {
			 		pre = cur1;
			 		cur1 = cur1.next;
		 		} /****不删除方案：不判断相等的情况****/else if(cur1.value == cur2.value){
		 			//如果相等第二条链往后移动一个位置
		 			next = cur2;
		 			cur2 = cur2.next;
		 		} else {
		 			//首先将next位置往后移一个
		 			next = cur2.next;
		 			//第二条链的元素接在pre后
		 			pre.next = cur2;
		 			//第一条链的元素接在第二条链后，这样pre->cur2->cur1
		 			cur2.next = cur1;
		 			//pre向后移动一位
		 			pre = cur2;
		 			//cur2移动到下一次遍历的初始位置
		 			cur2 = next;
		 		}
	        }
			//遍历到最后，一定会剩下一个元素没有遍历到，如果是cur1为空则是第二条链上剩一个元素，反之亦然
		    pre.next = cur1 == null ? cur2 : cur1;
		    return head;
	}
}
