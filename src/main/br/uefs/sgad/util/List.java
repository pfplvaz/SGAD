package main.br.uefs.sgad.util;

import java.util.Iterator;

public class List implements Iterable<Object>{
	
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
		size++;
	}
	
	public Object get(int index) {
		Node n = getNode(index);
		if (n != null) {
			return n.getData();
		}
		return null;
	}
	
	public void remove(int index) {
		if(index == 0) {
			head = head.getNext();
		} else if(index > 0 && index <= (getSize() - 1)) {
			Node before = getNode(index - 1);
			Node n = before.getNext();
			before.setNext(n.getNext());
		}
		size--;
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
		size--;
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
	
	public Iterator<Object> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Object>{
		private int cur = 0;
		
		public boolean hasNext() {
			return getNode(cur) != null;
		}

		public Object next() {
			Object data = get(cur);
			cur++;
			return data;
		}
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
