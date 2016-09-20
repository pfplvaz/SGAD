package main.br.uefs.sgad.util;

import java.util.Iterator;

public class GenericTree {
	private Node root; 
	
	public void add(Element dad, Object son){
		if(this.root == null){
			root = new Node(null, son, 0);
		} else{
			Node parent = (Node)dad;
			Node aux = new Node(parent, son, parent.getHeight() + 1);		
			parent.addSon(aux);
		}
	}
	
	public Element getElement(Object o){
		Iterator<Object> i = new GenericTreeElementIterator();
		Element aux;
		String a = (String)o;
		while(i.hasNext()){
			aux = (Node)i.next();
			String b = (String)aux.getData();
			if(b.equals(a)){
				return aux;
			}
		}
		return null;
	}
	
	private Iterator<Object> getSons(Element e){
		List sons = ((Node)e).getSons();
		if(sons != null)
			return sons.iterator();
		return null;
	}
	
	public Iterator<Object> iterator(){
		return new GenericTreeIterator();
	}
	
	public class GenericTreeIterator implements Iterator<Object>{
		private Queue queue = new Queue();
		
		public GenericTreeIterator(){
			queue.add(root);
		}
		
		public boolean hasNext(){
			return !queue.isEmpty();
		}

		public Object next(){
			Node n = (Node)queue.remove();
			if(n != null && n.getSons() != null){
				Iterator<Object> sonsIterator = getSons(n);
				while(sonsIterator.hasNext()){
					queue.add(sonsIterator.next());
				}	
			}
			return n.getData();
		}
	}
	
	private class GenericTreeElementIterator implements Iterator<Object>{
		private Queue queue = new Queue();
		
		public GenericTreeElementIterator(){
			queue.add(root);
		}
		
		public boolean hasNext(){
			return !queue.isEmpty();
		}

		public Object next(){
			Node n = (Node)queue.remove();
			if(n != null && n.getSons() != null){
				Iterator<Object> sonsIterator = getSons(n);
				while(sonsIterator.hasNext()){
					queue.add(sonsIterator.next());
				}	
			}
			return n;
		}
	}
	
	private class Node implements Element{
		private Object data;
		private Node parent;
		private List sons;
		private int height;
		
		public Node(Node parent, Object data, int height){
			this.parent = parent;
			this.data = data;
			this.height = height;
			this.sons = new List();
		}
		
		public Node getParent(){
			return parent;
		}
		
		public void setParent(Node parent){
			this.parent = parent;
		}
		
		public Object getData() {
			return data;
		}
		public void setData(Object data){
			this.data = data;
		}

		public List getSons() {
			return (sons != null) ? sons :  null;
		}

		public void addSon(Object son) {
			sons.add(son);
		}

		public int getHeight() {
			return height;
		}
		
	}
}
