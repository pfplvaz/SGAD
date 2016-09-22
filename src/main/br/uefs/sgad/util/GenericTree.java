package main.br.uefs.sgad.util;

import java.util.Iterator;

/**
 * Classe que representa uma árvore  generica, podendo ter n nós com n filhos.
 * Para a implementação dessa estrutura, foi utilizado o conseito de um nó
 * contendo o pai, o dado e uma lista e filhos.
 * 
 * @author pfplvaz
 */

public class GenericTree {
	
	/**
	 * Atributo que armazena a raiz da árvore.
	 */
	
	private Node root; 
	
	/**
	 * Método para adicionar um objeto (son) na árvore, tendo
	 * como referência o objeto pai (dad).
	 * Verifica se a árvore está vazia antes de adicionar.
	 * No caso de ela estar vazia, o elemento passado como parâmetro
	 * será a raiz.
	 * 
	 * @param dad
	 * @param son
	 */
	
	public void add(Element dad, Object son){
		
		if(this.root == null){
			root = new Node(null, son, 0);
		} else{
			Node parent = (Node)dad;
			Node aux = new Node(parent, son, parent.getDepth() + 1);		
			parent.addSon(aux);
		}
	}
	
	/**
	 * Método responsável por receber um elemento e retornar
	 * o seu caminho completo, desde a raiz.
	 * 
	 * @param e
	 * @return
	 */
	
	public String getPath(Element e){
		Iterator<Object> i = elementIterator();
		Node aux;
		Node parent;
		String[] path;
		Object a = e.getData();
		Object b;
		int index;
		
		// Percorre a árvore para que se encontre o elemento.
		
		while(i.hasNext()){
			aux = (Node)i.next();
			b = aux.getData();
			
			// Verifica se o elemento atual é o que se procura.
			
			if(a == b){
				
				// Verificação para garantir que o elemento não esteja na raiz.
				
				if(aux.getDepth() > 1){

					/* O caminho será todo armazenado em um vetor do tamanho da profundiade
					 * do pai do elemento encontrado.
					 * O vetor será preenchido de trás para frente.
					 */
					
					parent = aux.getParent();
					index = parent.getDepth() + 1;
					path = new String[index];
					
					// Primeiro nome é inserido na ultima posição do vetor.
					
					path[index - 1] = (String)parent.getData();
					index--;
					
					/* O vetor será preenchido de trás para frente até que
					 * não haja mais pais a serem adicionados.
					*/
					
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
