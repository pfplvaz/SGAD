package main.br.uefs.sgad.util;

import java.util.Iterator;

public class GenericTree {
	private Node root;
	
	public void addRoot(Object root){
		if(root == null)
			this.root = new Node(null, root);
	}
	
	public Element getRoot(){
		return root;
	}
	
	public void set(Element e, Object data){
		((Node)e).setData(data);
	}
	
	public void addSon(Element e, Object son){
		((Node)e).addSon(son);
	}
	
	public Iterator<Object> getSons(Element e){
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
		
		public boolean hasNext() {
			return !queue.isEmpty();
		}

		public Object next(){
			Node n = (Node)queue.remove();
			if(n.getSons() != null){
				Iterator<Object> sonsIterator= getSons(n);
				while(sonsIterator.hasNext()){
					queue.add(sonsIterator.next());
				}	
			}
			return n.getData();
		}
	}
	
	private class Node implements Element{
		private Object data;
		private Node parent;
		private List sons;
		
		public Node(Node parent, Object data){
			this.parent = parent;
			this.data = data;
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
			return sons;
		}

		public void addSon(Object son) {
			sons.add(son);
		}
	}
}
