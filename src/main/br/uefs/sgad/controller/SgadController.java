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
		mountTree(tree.getElement(o), f, sons);
	}
	
	private void mountTree(Element parent, File fa, String[] sons){
		String[] aux;
		Object o;
		File fb;
		
		if(sons == null){
			return;
		}
		
		for(int i = 0; i < sons.length; i++){
			fb = new File(fa.getPath() + "/" + sons[i]);
			o = (fb.isDirectory()) ? sons[i] + "/" : sons[i];
			tree.add(parent, o);
			aux = fb.list();
			mountTree(tree.getElement(o), fb, aux);
		}
	}
}
