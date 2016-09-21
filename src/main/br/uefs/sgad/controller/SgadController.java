package main.br.uefs.sgad.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import main.br.uefs.sgad.util.*;

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
		
		Object o = "/" + f.getName() + "/";
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
	
	public Iterator<Object> seachByName(String name, int depth) throws ArquivoNaoEncontradoException{
		
		Iterator<Object> i = tree.elementIterator();
		Element e;
		String aux;
		List found = new List();
		
		while(i.hasNext()){
			e = (Element)i.next();
			if(e.getDepth() <= depth || depth == 0){
				aux = (String)e.getData();
				if(aux.startsWith(name) && !aux.endsWith("/")){
					String path = tree.getPath(e) + aux;
					found.add(path);
				}
			}
		}
		if(!found.isEmpty())
			return found.iterator();
		throw new ArquivoNaoEncontradoException(name);
	}
	
<<<<<<< HEAD
public Iterator<Object> seachByFolder(String folder, int depth) throws ArquivoNaoEncontradoException{
		
		Iterator<Object> i = tree.elementIterator();
		Element e;
		String aux;
		List found = new List();
		
		while(i.hasNext()){
			e = (Element)i.next();
			if(e.getDepth() <= depth || depth == 0){
				aux = (String)e.getData();
				if(aux.equals(folder + "/")){
					String path = tree.getPath(e) + aux;
					found.add(path);
				}
			}
		}
		if(!found.isEmpty())
			return found.iterator();
		throw new ArquivoNaoEncontradoException(folder);
	}
	
=======
>>>>>>> refs/remotes/origin/master
	public Iterator<Object> seachByType(String type, int depth) throws TipoNaoEncontradoException{
		
		Iterator<Object> i = tree.elementIterator();
		Element e;
		String aux;
		List found = new List();
		
		while(i.hasNext()){
			e = (Element)i.next();
			if(e.getDepth() <= depth || depth == 0){
				aux = (String)e.getData();
				if(aux.endsWith(type)){
					String path = tree.getPath(e) + aux;
					found.add(path);
				}
			}
		}
		if(!found.isEmpty())
			return found.iterator();
		throw new TipoNaoEncontradoException(type);

	}
	
public void exportPath(String path, int depth, String fileName) throws ArquivoNaoEncontradoException, IOException{
		
		String[] split;
		Iterator<Object> i = tree.elementIterator();
		Element e = null;
		String aux;
		boolean found = false;
		List files = new List();
		
		split = path.split("/");
		
		while(i.hasNext() && !found){
			e = (Element)i.next();
			if(e.getDepth() <= depth || depth == 0){
				aux = (String)e.getData();
				if(aux.equals(split[split.length - 1] + "/")){
					String str = tree.getPath(e) + aux;
					if(str.equals(path)){
						found = true;
					}
				}
			}
		}
		
		if(found){
			i = tree.elementIterator(e);
			while(i.hasNext()){
				e = (Element)i.next();
				if(e.getDepth() <= depth || depth == 0){
					aux = (String)e.getData();
					String str = tree.getPath(e) + aux;
					files.add(str);
				}
			}
		}
		
		if(!files.isEmpty()){
			i = files.iterator();
			try{
				FileWriter file = new FileWriter(new File(fileName + ".txt"));
				while(i.hasNext()){
					file.write((String)i.next() + "\n");
				}
				file.close();
			} catch(IOException ex){
				throw ex;
			}
		} else{
			throw new ArquivoNaoEncontradoException(path);
		}
	}
	
}
