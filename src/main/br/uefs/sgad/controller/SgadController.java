package main.br.uefs.sgad.controller;

import java.io.File;

import main.br.uefs.sgad.util.Element;
import main.br.uefs.sgad.util.GenericTree;

public class SgadController{
	
	GenericTree tree;
	
	public SgadController(){
		this.tree = new GenericTree();
	}
	
	public void mountPath(String path) throws PastaNaoEncontradaException, NaoEhPastaException{
		
		File f = new File(path);
		String[] sons;
		
		if(!f.exists()){
			throw new PastaNaoEncontradaException(path);
		} else if(!f.isDirectory()){
			throw new NaoEhPastaException(path);
		}
		
		Object o = f.getName();
		tree.add(null, o);
		sons = f.list();
		mountTree(tree.getElement(o), sons, f);

		
		
	}
	
	private void mountTree(Element parent, String[] sons, File f){
		String[] aux;
		
		if(sons[0] != null){
			return;
		}
		Object o;
		for(int i = 0; i < sons.length; i++){
			o = sons[i];
			tree.add(parent, o);
			f = new File(sons[i]);
			aux = f.list();
			mountTree(tree.getElement(sons[i]), aux, f);
		}
		
	}

}
