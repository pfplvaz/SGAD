package main.br.uefs.sgad.util;

public class GenericTree {
	private Node root;
	private int size;
	
	private class Node implements Element{
		private Object data;
		private Node parent;
		//private List filhos;
		
		public Node(Node parent, Object data){
			this.parent = parent;
			this.data = data;
		}
		
		public Node getParent(){
			return parent;
		}
		
		public void setParente(Node parent){
			this.parent = parent;
		}
		
		public Object getData() {
			return data;
		}
		public void setData(Object data){
			this.data = data;
		}
		
	}
}
