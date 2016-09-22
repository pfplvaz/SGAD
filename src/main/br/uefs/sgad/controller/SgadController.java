package main.br.uefs.sgad.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import main.br.uefs.sgad.util.*;

/**
 * Classe controladora responsável por realizar todas as operações do programa.
 * 
 * @author pfplvaz
 */

public class SgadController{
	
	/**
	 * Atributo que receberá a árvore genérica que amazenará os dados.
	 */
	
	GenericTree tree;
	
	public SgadController(){
		
		// O construtor cria o objeto da árvore genérica.
		
		this.tree = new GenericTree();
	}
	
	/**
	 * Método da classe controladora responsável por receber um 
	 * caminho de diretório e montar a representação da sua hierarquia
	 * de sub-diretórios e arquivos na estrutura genérica de árvore.
	 * 
	 * @param path
	 * @throws PastaNaoEncontradaException
	 * @throws NaoEhPastaException
	 */
	
	public void mountPath(String path) throws PastaNaoEncontradaException, NaoEhPastaException{
		
		File f = new File(path);
		String[] sons;
		
		// Se o diretório não existir, sua exceção é lançada.
		
		if(!f.exists()){ 
			throw new PastaNaoEncontradaException(path);
			
			// Se o caminho não representar um diretório, outra exceção será lançada.
			
		} else if(!f.isDirectory()){
			
			throw new NaoEhPastaException(path);
		}
		
		/* A raíz da árvore é montada primeiramente, tendo com pai o valor NULL.
		 * Seus filhos são então armazenados em uma lista (sons).
		 */
		
		Object o = "/" + f.getName() + "/";
		tree.add(null, o);
		sons = f.list();
		
		/*  Método que agirá de forma recursiva para montar a 
		 *  hierarquia de diretórios é chamado.
		 */
		
		mountTree(tree.getElement(o), f, sons);
	}
	
	/**
	 * Método que será chamado de forma recursiva até que as folhas da árvore sejam alcançadas
	 * 
	 * @param parent
	 * @param fa
	 * @param sons
	 */
	
	private void mountTree(Element parent, File fa, String[] sons){
		String[] aux;
		Object o;
		File fb;
		
		// Condição de parada é o diretório não conter mais sub-diretórios ou arquivos a serem adicionados.
		
		if(sons == null){
			return;
		}
		
		// Percorre a lista de filhos de um diretório
		
		for(int i = 0; i < sons.length; i++){
			fb = new File(fa.getPath() + "/" + sons[i]);
			
			/* Verificação será realizada para distinguir diretório de arquivo
			 * na montagem da árvore. Caso seja arquivo, o objeto recebéra uma / (barra) no final.
			 */
			
			o = (fb.isDirectory()) ? sons[i] + "/" : sons[i];
			tree.add(parent, o);
			
			/*  Os filhos do diretório que foi adicionado são armazenados e usados
			 *  como parâmetro para mais uma chamada recursiva. 
			 */
			
			aux = fb.list();
			mountTree(tree.getElement(o), fb, aux);
		}
	}
	
	/**
	 * Metódo que procura um arquivo na árvore pelo seu nome e profundidade,
	 * retornando um iterador de uma lista de resultados encontrados até
	 * a profundidade determinada pelo parâmetro.
	 * 
	 * @param name
	 * @param depth
	 * @return
	 * @throws ArquivoNaoEncontradoException
	 */
	
	public Iterator<Object> seachByName(String name, int depth) throws ArquivoNaoEncontradoException{
		
		Iterator<Object> i = tree.elementIterator();
		Element e;
		String aux;
		List found = new List();
		
		while(i.hasNext()){
			e = (Element)i.next();
			
			/* Verifica se a profundiade do elemento ainda é menor ou igual	
			 * a passada como parâmentro. Além disso, se ela é igual a 0, permitindo assim
			 * a busca por toda a estrutura.
			 */
			
			if(e.getDepth() <= depth || depth == 0){
				aux = (String)e.getData();
				
				/* Verifica se o nome do elemento corresponde ao passado como
				 * parâmetro e, além disso, se ele não é um diretório.
				 */
				
				if(aux.startsWith(name) && !aux.endsWith("/")){
					String path = tree.getPath(e) + aux;
					
					// Caso ele corresponda as requisitos, é adicionado à lista.
					
					found.add(path);
				}
			}
		}
		
		/* Se a lista não estiver vazia siginifica que ao menos um elemento
		 * foi encontrado e o iterador da lista de itens encontrados é
		 * retornado.
		 * Caso nada seja encontrado, o método lança uma exceção.
		 */
		
		if(!found.isEmpty())
			return found.iterator();
		throw new ArquivoNaoEncontradoException(name);
	}
	
	/**
	 * Metódo que procura um diretório na árvore pelo seu nome,
	 * retornando um iterador de uma lista de resultados encontrados até
	 * a profundidade determinada pelo parâmetro.
	 * 
	 * @param folder
	 * @param depth
	 * @return
	 * @throws PastaNaoEncontradaException
	 */

	public Iterator<Object> seachByFolder(String folder, int depth) throws PastaNaoEncontradaException{
		
		Iterator<Object> i = tree.elementIterator();
		Element e;
		String aux;
		List found = new List();
		
		while(i.hasNext()){
			e = (Element)i.next();
			
			/* Verifica se a profundiade do elemento ainda é menor ou igual	
			 * a passada como parâmentro. Além disso, se ela é igual a 0, permitindo assim
			 * a busca por toda a estrutura.
			 */
			
			if(e.getDepth() <= depth || depth == 0){
				aux = (String)e.getData();
				
				/* Verifica se o nome do elemento corresponde ao passado como
				 * parâmetro.
				 */
				
				if(aux.equals(folder + "/")){
					String path = tree.getPath(e) + aux;
					
					// Caso ele corresponda as requisitos, é adicionado à lista.
					
					found.add(path);
				}
			}
		}
		
		/* Se a lista não estiver vazia siginifica que ao menos um elemento
		 * foi encontrado e o iterador da lista de itens encontrados é
		 * retornado.
		 * Caso nada seja encontrado, o método lança uma exceção.
		 */
		
		if(!found.isEmpty())
			return found.iterator();
		throw new PastaNaoEncontradaException(folder);
	}
	
	/**
	 * Metódo que procura arquivos na árvore pela extensão e profundidade,
	 * retornando um iterador de uma lista de resultados encontrados até
	 * a profundidade determinada pelo parâmetro.
	 * 
	 * @param type
	 * @param depth
	 * @return
	 * @throws TipoNaoEncontradoException
	 */

	public Iterator<Object> seachByType(String type, int depth) throws TipoNaoEncontradoException{
		
		Iterator<Object> i = tree.elementIterator();
		Element e;
		String aux;
		List found = new List();
		
		while(i.hasNext()){
			e = (Element)i.next();
			
			/* Verifica se a profundiade do elemento ainda é menor ou igual	
			 * a passada como parâmentro. Além disso, se ela é igual a 0, permitindo assim
			 * a busca por toda a estrutura.
			 */
			
			if(e.getDepth() <= depth || depth == 0){
				aux = (String)e.getData();
				
				/* Verifica se a extensão do elemento corresponde a passada como
				 * parâmetro.
				 */
				
				if(aux.endsWith(type)){
					String path = tree.getPath(e) + aux;
					
					// Caso ele corresponda as requisitos, é adicionado à lista.
					
					found.add(path);
				}
			}
		}
		
		/* Se a lista não estiver vazia siginifica que ao menos um elemento
		 * foi encontrado e o iterador da lista de itens encontrados é
		 * retornado.
		 * Caso nada seja encontrado, o método lança uma exceção.
		 */
		
		if(!found.isEmpty())
			return found.iterator();
		throw new TipoNaoEncontradoException(type);

	}
	
	/**
	 * Metodo responsável por encontrar um diretório passado e
	 * exportar um arquivo contendo todos os caminhos dos outros 
	 * diretórios e arquivos até a profundidade desejada.
	 * 
	 * @param path
	 * @param depth
	 * @param fileName
	 * @throws ArquivoNaoEncontradoException
	 * @throws IOException
	 */
	
	public void exportPath(String path, int depth, String fileName) throws ArquivoNaoEncontradoException, IOException{
		
		String[] split;
		Iterator<Object> i = tree.elementIterator();
		Element e = null;
		String aux;
		boolean found = false;
		List files = new List();
		
		// Divisão do caminho passado como parâmetro para se ter os diretórios
		
		split = path.split("/");
		
		// Loop até que haja elementos e que o caminho não tenha sido encontrado.
		
		while(i.hasNext() && !found){
			e = (Element)i.next();
			
			/* Verifica se a profundiade do elemento ainda é menor ou igual	
			 * a passada como parâmentro. Além disso, se ela é igual a 0, permitindo assim
			 * a busca por toda a estrutura.
			 */
			
			if(e.getDepth() <= depth || depth == 0){
				aux = (String)e.getData();
				
				/* Uma vez tendo na ultima possisão do vetor o nome
				 * do ultimo diretório, achando-o basta comparar o seu
				 * caminho completo com o passado como parâmetro.
				 */
				
				if(aux.equals(split[split.length - 1] + "/")){
					String str = tree.getPath(e) + aux;
					
					// Comparando os caminhos completos para validar o elemento encontrado.
					
					if(str.equals(path)){
						found = true;
					}
				}
			}
		}
		
		/* Uma vez encontrado, um noto iterador é gerado tendo como raiz
		 * esse elemento.
		 */
		
		if(found){
			i = tree.elementIterator(e);
			while(i.hasNext()){
				e = (Element)i.next();
				if(e.getDepth() <= depth || depth == 0){
					aux = (String)e.getData();
					String str = tree.getPath(e) + aux;
					
					/* Os caminhos de todos os arquivos e diretórios
					 * são salvos em uma lista.
					 */
					
					files.add(str);
				}
			}
		}
		
		/* Caso a lista esteja vazia, nenhum elemento foi encontrado
		 * e uma exceção é lançada.
		 */
		
		if(!files.isEmpty()){
			i = files.iterator();
			
			/*  Se durante a manipulação do arquivo houver algum erro, do tipo IOException,
			 *  a exceção será lançada.
			 */
			
			try{
				
				// Criação do arquivo conforme o nome passado como parâmetro.
				
				FileWriter file = new FileWriter(new File(fileName + ".txt"));
				
				// Escreve todos os elementos da lista no arquivo.
				
				while(i.hasNext()){
					file.write((String)i.next() + "\n");
				}
				
				// O arquivo é fechado.
				
				file.close();
			} catch(IOException ex){
				throw ex;
			}
		} else{
			throw new ArquivoNaoEncontradoException(path);
		}
	}
	
}
