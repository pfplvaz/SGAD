package main.br.uefs.sgad.view;

import java.io.IOException;
import java.util.Iterator;

import main.br.uefs.sgad.util.Console;
import main.br.uefs.sgad.controller.*;

public class Interface {
	
	private SgadController controller;
	
	public Interface(){
		this.controller = new SgadController();
	}
	
	public void mountMenu() throws IOException{
		
		boolean valid = false;
		
		System.out.println("SGAD - Sistema de Gerenciamento de \n    Arquivos e Diretórios\n\n");
		System.out.println("Digite o caminho completo da pasta a ser montada: ");
		
		while(!valid){
			try {
				valid = true;
				System.out.print("Caminho: ");
				String path = Console.readString();
				controller.mountPath(path);
			} catch(PastaNaoEncontradaException e){
				System.out.println("Pasta não encontrada !");
				System.out.println("Digite um caminho válido.");
				valid = false;
			} catch(NaoEhPastaException e){
				System.out.println("Não é uma pasta !");
				System.out.println("Digite um caminho de diretório válido.");
				valid = false;
			} catch(IOException e) {
				System.out.println("Digite um caractere válido !");
				valid = false;
			}
		}
		System.out.println("Hierarquia montada com sucesso !");
		this.mainMenu();
	}
	
	private void mainMenu() throws IOException{
		
		boolean valid = false, exit = false;
		int option = 0;
		
		while(!exit){
			
			System.out.println("\n          Menu Principal\n");
			System.out.println("1 - Pesquisar arquivo através do nome");
			System.out.println("2 - Pesquisar arquivo através da pasta");
			System.out.println("3 - Pesquisar arquivo por tipo");
			System.out.println("4 - Exportar estrutura de diretório");
			System.out.println("9 - Sair");

			do{
				valid = true;
				System.out.print("\nOpção: ");
				try {
					option = Console.readInt();
				} catch (NumberFormatException | IOException e) {
					valid = false;
					System.out.println("Digite um caractere válido !");
				}
				if(option < 1 || option > 4 && option != 9){
					valid = false;
					System.out.println("Digite uma opção válida !");
				}
			}while(!valid);
			
			switch(option){
			case 1:
				System.out.print("Digite o nome do arquivo: ");
				String name = Console.readString();
				System.out.print("\nDigite o nível de profundidade: ");
				int depth1 = Console.readInt();
				
				Iterator<Object> i1 = null;
				try {
					i1 = controller.seachByName(name, depth1);
				} catch (ArquivoNaoEncontradoException e) {
					System.out.println("Arquivo não encontrado !");
				}
				if(i1 != null){
					System.out.println("Resultados: ");
					while(i1.hasNext()){
						System.out.println((String)i1.next());
					}
				}
				break;
				
			case 2:
				System.out.print("Digite o caminho: ");
				String path = Console.readString();
				System.out.print("\nDigite o nível de profundidade: ");
				int depth2 = Console.readInt();
				
				Iterator<Object> i2 = null;
				try {
					i2 = controller.seachByPath(path, depth2);
				} catch (ArquivoNaoEncontradoException e) {
					System.out.println("Diretório não encontrado !");
				}
				if(i2 != null){
					System.out.println("Resultados: ");
					while(i2.hasNext()){
						System.out.println((String)i2.next());
					}
				}
				break;
				
			case 9:
				exit = true;
				break;
			}
		}
		
			
		
	}

}
