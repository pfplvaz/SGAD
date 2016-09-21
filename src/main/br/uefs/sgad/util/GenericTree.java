package main.br.uefs.sgad.util;

import java.util.Iterator;

public class GenericTree {
	private Node root; 
	
	public void add(Element dad, Object son){
		if(this.root == null){
			root = new Node(null, son, 0);
		} else{
			Node parent = (Node)dad;
			Node aux = new Node(parent, son, parent.getDepth() + 1);		
			parent.addSon(aux);
		}
	}
	
	public String getPath(Element e){
		Iterator<Object> i = elementIterator();
		Node aux;
		Node parent;
		String[] path;
		Object a = e.getData();
		Object b;
		int index;
		
		
		while(i.hasNext()){
			aux = (Node)i.next();
			b = aux.getData();
			if(a == b){
				if(aux.getDepth() > 1){
					parent = aux.getParent();
					index = parent.getDepth() + 1;
					path = new String[index];
					path[index - 1] = (String)parent.getData();
					index--;
					while(parent.getParent() != null){
						parent = parent.getParent();
						path[index - 1] = (String)parent.getData();
						index--;
					}
					String ret = "";
					for(int x = 0; x < path.length; x++)
						ret = ret + path[x];
					return ret;
				}
				return (String)aux.getParent().getData();	
			}
		}
		return null;
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
	
	public Iterator<Object> elementIterator(){
		return new GenericTreeElementIterator();
	}
	
	public Iterator<Object> elementIterator(Element e){
		return new GenericTreeElementIterator((Node)e);
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
	
	public class GenericTreeElementIterator implements Iterator<Object>{
		private Queue queue = new Queue();
		
		public GenericTreeElementIterator(){
			queue.add(root);
		}
		
		public GenericTreeElementIterator(Node n){
			queue.add(n);
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
		private int depth;
		
		public Node(Node parent, Object data, int depth){
			this.parent = parent;
			this.data = data;
			this.depth = depth;
			this.sons = new List();
		}
		
		public Node getParent(){
			return parent;
		}
		
		public Object getData() {
			return data;
		}

		public List getSons() {
			return (sons != null) ? sons :  null;
		}

		public void addSon(Object son) {
			sons.add(son);
		}

		public int getDepth() {
			return depth;
		}
		
	}
}
