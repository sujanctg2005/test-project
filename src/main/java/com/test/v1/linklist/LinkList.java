package com.test.v1.linklist;

public class LinkList {
	private Node top;

	public void add(int data) {
		if (top == null) {
			top = new Node(data);

		} else {
			Node current = top;
			while (current.getNext() != null) {
				current = current.getNext();
			}

			Node newNode = new Node(data);
			current.setNext(newNode);

		}
	}

	public Node getTop() {
		return top;
	}

	public void setTop(Node top) {
		this.top = top;
	}

	public void delete(int index) {

		if (index == 0) {
			top = top.next;
		} else {
			Node current = top;
			for (int i = 0; i < (index - 1); i++) {
				current = current.getNext();
			}
			current.next = current.next.next;
		}

	}

	public void printList() {
		Node current = top;
		System.out.print("\n");
		while (current != null) {
			System.out.print(current.getData() + ", ");
			current = current.getNext();
		}
		System.out.print("\n");
	}

	public int length() {
		int length = 0;
		if (top == null) {
			return 0;
		} else {
			length++;
			Node current = top;
			while (current.getNext() != null) {
				current = current.getNext();
				length++;
			}

			return length;
		}
	}

	public void sortLinkListZeroToTwo() {
		int[] counter = { 0, 0, 0 };
		Node current = top;
		while (current != null) {
			counter[current.getData()] += 1;
			current = current.getNext();
		}
		current = top;
		int i = 0;
		while (current != null) {
			if (counter[i] == 0) {
				i++;
			}
			counter[i] -= 1;
			current.setData(i);
			current = current.getNext();
		}
	}

	public void reversListV1() {
		Node current = top;
		Node nextNode = current.getNext();
		current.setNext(null);
		reversListAlg1(current, nextNode);

	}

	private void reversListAlg1(Node current, Node nextNode) {

		if (nextNode == null) {
			top = current;
			return;
		}
		reversListAlg1(nextNode, nextNode.getNext());
		nextNode.setNext(current);
	}

	public void reversListV2() {
		Node previousNode = null;
		Node current = top;
		Node nextNode;

		while (current != null) {
			nextNode = current.getNext();
			current.setNext(previousNode);
			previousNode = current;
			current = nextNode;

		}

		top = previousNode;

	}

	public void reversListV3() {
		reversListAlg2(top);

	}

	private void reversListAlg2(Node head) {
		if (head == null) {
			return;
		}

		Node first = head;
		head = first.getNext();

		if (head == null) {
			top = first;
			return;
		}
		reversListAlg2(head);
		first.getNext().setNext(first);
		first.setNext(null);
	}

	public void reverseAndSkip() {
		top = reverseAndSkipAlg(top, 2);

	}

	private Node reverseAndSkipAlg(Node head, int k) {

		Node previous = null;
		Node current = head;
		Node next = null;
		int count = 0;

		while (current != null && count < k) {
			next = current.getNext();
			current.setNext(previous);
			previous = current;
			current = next;
			count++;
		}
		if (current != null) {
			head.setNext(reverseAndSkipAlg(current, k));
		}

		return previous;
	}

	/* Compare from top to bottom with complexity O(n^2) */

	public void removeDuplicate() {
		Node current = top;
		if (current == null)
			return;
		if (current.getNext() == null) {
			return;
		}
		Node next;
		Node previous = top;
		while (current != null) {
			next = current.getNext();
			while (next != null) {
				if (next.getData() == current.getData()) {
					previous.setNext(next.getNext());
					next = next.getNext();
				} else {
					previous = next;
					next = next.getNext();
				}

			}
			current = current.getNext();
			previous = current;

		}
	}

	public void skipAndRemove(int m, int n) {
		Node current = top;
		while (current != null) {
			for (int i = 0; i < (m - i); i++) {
				current = current.getNext();
				if (current == null) {
					break;
				}
			}
			if (current != null) {
				for (int i = 0; i < n; i++) {
					if (current.getNext() == null) {
						break;
					} else {
						current.setNext(current.getNext().getNext());
					}
				}
				current = current.getNext();
			}

		}

	}

	public void deletLoop() {

		Node fastNode = top;
		Node slowNode = top;
		if (fastNode == null) {
			return;
		}
		if (slowNode.getNext() == null || fastNode.getNext().getNext() == null) {
			return;
		} else {
			while (fastNode.getNext().getNext() != slowNode.getNext()) {
				fastNode = fastNode.getNext().getNext();
				slowNode = slowNode.getNext();
				if (fastNode == null || fastNode.getNext() == null) {
					return;
				}
			}

			// fastNode is not null then there is a loop

			slowNode = top;

			if (fastNode.getNext().getNext() == top) {
				fastNode.getNext().setNext(null);
			} else {
				fastNode = fastNode.getNext().getNext();
				while (slowNode.getNext() != fastNode.getNext()) {
					slowNode = slowNode.getNext();
					fastNode = fastNode.getNext();
					System.out.println("..fastNode :" + fastNode.getData() + " slowNode:  " + slowNode.getData());
				}
				fastNode.setNext(null);
			}

			System.out.println("fastNode :" + fastNode.getData() + " slowNode:  " + slowNode.getData());

		}

	}

	public void createLoop() {
		top = null;
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		top = n1;

		n1.setNext(n2);
		n2.setNext(n3);
		n3.setNext(n4);
		n4.setNext(n5);
		n5.setNext(n6);
		n6.setNext(n4);
	}

	public void moveLastAsFirst() {
		Node prevNode = null;
		Node currentNode = top;
		while (currentNode.getNext() != null) {
			prevNode = currentNode;
			currentNode = currentNode.getNext();
		}

		prevNode.setNext(null);
		currentNode.setNext(top);
		top = currentNode;
	}

	public void swap() {
		if (top == null || top.getNext() == null) {
			return;
		}

		Node current = top;
		top = current.getNext();
		Node prevNode = null;
		while (current != null && current.getNext() != null) {
			System.out.println("current: " + current.getData());
			Node temp = current.getNext();
			current.setNext(temp.getNext());
			temp.setNext(current);
			if (prevNode != null) {
				prevNode.setNext(temp);
			}
			prevNode = current;
			current = current.getNext();
		}

	}

	public void rotate(int k) {
		if (top == null || k <= 0) {
			return;
		} else {
			Node current = top;
			int counter = 1;
			while (counter < k && current != null) {
				current = current.getNext();
				counter++;
			}
			if (current == null) {
				return;
			}
			Node kthNode = current;
			while (current.getNext() != null) {
				current = current.getNext();
			}

			current.setNext(top);
			top = kthNode.getNext();
			kthNode.setNext(null);

		}
	}

	public void insertSortedData(int data) {
		Node newNode = new Node(data);
		if (top == null || top.getData() > data) {
			newNode.setNext(top);
			top = newNode;
			System.out.println("data: " + data);
		} else {
			Node current = top;
			Node prevNode = null;
			System.out.println("data: " + data + " top: " + top.getData() + " current: " + current.getData());
			while (current != null && current.getData() < data) {
				prevNode = current;
				current = current.getNext();
			}
			if (prevNode != null) {
				prevNode.setNext(newNode);
			}
			newNode.setNext(current);

		}
	}

	public static void main(String[] args) {

		/********* Example 1 *******************/

		/*
		 * LinkList linkList = new LinkList(); int []
		 * dataList={10,11,12,13,14,15,16,17,18,19,20}; for(int data:dataList){
		 * linkList.add(data); } System.out.println("LinkList size:"+linkList.length());
		 * linkList.printList(); linkList.delete(0); linkList.delete(0);
		 * System.out.println("\nAfter deleting:"); linkList.printList();
		 */

		/********* Example 2( sort 0's 1's and 2's) *******************/
		/*
		 * LinkList linkList = new LinkList(); int [] dataList={1,2,2,1,1,0,2,0,0,2,1};
		 * for(int data:dataList){ linkList.add(data); }
		 * System.out.println("LinkList size:"+linkList.length()); linkList.printList();
		 * linkList.sortLinkListZeroToTwo(); linkList.printList();
		 */
		/********* Example 3(skip M and delete N) *******************/
		/*
		 * LinkList linkList = new LinkList(); int[] dataList = {
		 * 10,11,12,13,14,15,16,17,18,19,20}; for (int data : dataList) {
		 * linkList.add(data); } linkList.printList(); linkList.skipAndRemove(2, 2);
		 * linkList.printList();
		 */
		/********* Example 4(Reverse linked list) *******************/

		/*
		 * LinkList linkList = new LinkList(); int[] dataList = {
		 * 10,11,12,13,14,15,16,17,18,19,20}; for (int data : dataList) {
		 * linkList.add(data); } linkList.printList(); linkList.reversListV1();
		 * linkList.printList();
		 */

		/********* Example 5(Reverse linked list) *******************/

		/*
		 * LinkList linkList = new LinkList(); int[] dataList = { 10, 11, 12, 13, 14,
		 * 15, 16, 17, 18, 19, 20 }; for (int data : dataList) { linkList.add(data); }
		 * 
		 * linkList.printList(); linkList.reversListV2(); linkList.printList();
		 */

		/********* Example 6(Reverse linked list) *******************/

		/*
		 * LinkList linkList = new LinkList(); int[] dataList = { 10, 11, 12, 13, 14,
		 * 15, 16, 17, 18, 19, 20 }; for (int data : dataList) { linkList.add(data); }
		 * linkList.printList(); linkList.reversListV3(); linkList.printList();
		 */

		/*********
		 * Example 7(remove duplicate from linked list)
		 *******************/
		/*
		 * LinkList linkList = new LinkList(); int[] dataList = {
		 * 10,11,11,13,13,15,16,16,16,19,20}; for (int data : dataList) {
		 * linkList.add(data); } linkList.printList(); linkList.removeDuplicate();
		 * linkList.printList();
		 */

		/*********
		 * Example 8(Reverse and skip the linked list)
		 *******************/

		/*
		 * LinkList linkList = new LinkList(); int[] dataList = { 10, 11, 12, 13, 14,
		 * 15, 16, 17, 18, 19, 20 }; for (int data : dataList) { linkList.add(data); }
		 * linkList.printList(); linkList.reverseAndSkip(); linkList.printList();
		 */
		/*********
		 * Example 9(Loop detection and removed loop)
		 *******************/
		/*
		 * LinkList linkList = new LinkList(); linkList.createLoop();
		 * linkList.deletLoop(); linkList.printList();
		 */

		/********* Example 10(merge linked list) *******************/
		/*
		 * LinkList firstLinkList = new LinkList(); int[] dataList1 = { 10, 11, 12, 13,
		 * 14}; for (int data : dataList1) { firstLinkList.add(data); }
		 * firstLinkList.printList(); LinkList secondLinkList = new LinkList(); int[]
		 * dataList2 = { 1,2, 3, 4, 5, 6, 7, 8, 18, 19, 20 }; for (int data : dataList2)
		 * { secondLinkList.add(data); } secondLinkList.printList();
		 * 
		 * mergeLinkedList(firstLinkList,secondLinkList);
		 * 
		 * firstLinkList.printList();
		 * 
		 * 
		 * secondLinkList.printList();
		 */
		/********* Example 11(moveLastAsFirst list) *******************/
		/*
		 * 
		 * LinkList linkList = new LinkList(); int[] dataList = { 10, 11, 12, 13, 14,
		 * 15, 16, 17, 18, 19, 20 }; for (int data : dataList) { linkList.add(data); }
		 * linkList.printList(); linkList.moveLastAsFirst(); linkList.printList();
		 */

		/********* Example 11(moveLastAsFirst list) *******************/

		/*
		 * LinkList linkList = new LinkList(); int[] dataList = { 10, 11, 12, 13, 14,
		 * 15, 16, 17, 18, 19, 20 }; for (int data : dataList) { linkList.add(data); }
		 * linkList.printList(); linkList.swap(); linkList.printList();
		 */

		/********* Example 12(rotate list) *******************/

		LinkList linkList = new LinkList();
		int[] dataList = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
		for (int data : dataList) {
			linkList.add(data);
		}
		linkList.printList();
		linkList.rotate(4);
		linkList.printList();

		/********* Example 13(insert sorted data) *******************/

		//
		// LinkList linkList = new LinkList();
		// int[] dataList = { 20,30,10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
		// for (int data : dataList) {
		// linkList.insertSortedData(data);
		//
		// }
		// linkList.printList();

	}

	
	public void rotate1(int pos) {
		Node tempTop = top;
		Node current=top;
		while((--pos)<0) {
			current =current.getNext();
		}
		
		if(current!=null &&  current.getNext()!=null) {
			
			top=current.getNext();
			current.setNext(null);
		}
		current=top;
		while(current.getNext()!=null) {
			current=current.getNext();
		}
		current.setNext(tempTop);
	}
	
	public static void mergeLinkedList(LinkList firstLinkList, LinkList secondLinkList) {

		Node firstLinkCurrentNode = firstLinkList.getTop();
		Node secondLinkCurrentNode = secondLinkList.getTop();
		while (firstLinkCurrentNode != null && secondLinkCurrentNode != null) {

			Node tempNode = secondLinkCurrentNode;
			secondLinkCurrentNode = secondLinkCurrentNode.getNext();
			tempNode.setNext(firstLinkCurrentNode.getNext());
			firstLinkCurrentNode.setNext(tempNode);
			firstLinkCurrentNode = tempNode.getNext();
		}

		secondLinkList.setTop(secondLinkCurrentNode);

	}

}
