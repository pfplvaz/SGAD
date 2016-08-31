package main.br.uefs.sgad.util;

public class List{
	
	private Node head;
	private int size;
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int getSize(){
		return size;
	}

	public void add(Object data) {
		if(isEmpty()){
			head = new Node(data);
		} else{
			Node n = getNode(getSize() - 1);
			n.setNext(new Node(data));
		}
	}
	
	public void addBeginning(Object data) {
		 Node temp = head;
		 head = new Node(data);
		 head.setNext(temp);
	}
	
	public void set(int index, Object data) {
		Node n = getNode(index);
		if(n != null) {
			n.setData(data);
		}
	}
	
	public Object get(int index) {
		Node n = getNode(index);
		if (n != null) {
			return n.getData();
		}
		return null;
	}
	
	public boolean contains(Object data) {
		for(Node n = head; n != null; n = n.getNext()){
			if (n.getData() != null && n.getData().equals(data)) {
				return true;
			}
		}
		return false;
	} 
	
	public void remove(int index) {
		if(index == 0) {
			head = head.getNext();
		} else if(index > 0 && index <= (getSize() - 1)) {
			Node before = getNode(index - 1);
			Node n = before.getNext();
			before.setNext(n.getNext());
		}
	}
	
	public void remove(Object data) {
		Node n = head;
		for(int i = 0; n != null; i++) {
			if(n.getData() != null && n.getData().equals(data)){
				remove(i);
				return;
			}
			n = n.getNext();
		}
	}
	
	private Node getNode(int index) {
		if (index >= 0 && index < getSize()){
			Node n = head;
			for (int i = 0; i < index; i++) {
				n = n.getNext();
			}
			return n;
		}
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
		
		public void setData(Object o){
			this.data = o;
		}
		
	}

}
