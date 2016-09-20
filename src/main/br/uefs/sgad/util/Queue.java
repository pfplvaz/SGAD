package main.br.uefs.sgad.util;

public class Queue{
	
	private Node head;
	private Node tail;
	private int size;

	public boolean isEmpty() {
		return head == null;
	}

	public int getSize() {
		return size;
	}

	public void add(Object o) {
		if(isEmpty()){
			head = tail = new Node(o);
		} else{
			Node aux = tail;
			tail = new Node(o);
			aux.setNext(tail);
		}
		size++;
	}

	public Object remove() {
		Object aux = null;

		if(!isEmpty()){
			aux = head.getData();
			if (head == tail) {
				head = tail = null;
			} else{
				head = head.getNext();
			}
		size--;
		}
		return aux;
	}

	public Object peek() {
		if(!isEmpty())
			return head.getData();
		return null;
	}
	
	private class Node{
		private Object data;
		private Node next;
		
		public Node(Object data){
			this.data = data;
		}
		
		public Node getNext(){
			return next;
		}
		
		public void setNext(Node n){
			this.next = n;
		}
		
		public Object getData(){
			return data;
		}
	}
}
