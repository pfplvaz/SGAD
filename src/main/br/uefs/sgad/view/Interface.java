package main.br.uefs.sgad.view;

import java.io.IOException;
import java.util.Iterator;

import main.br.uefs.sgad.util.Console;
import main.br.uefs.sgad.controller.*;

/**
 * @author Paulo Vaz
 * 
 * Classe de interface responsável por realizar a interação entre o usuário e o programa.
 *
 */

public class Interface {
	
	/**
	 * Controlador que será utilizado pela interface durante a execução do programa.
	 */
	
	private SgadController controller;
	
	public Interface(){
		// O construtor cria o objeto controlador.
		this.controller = new SgadController();
	}
	
	/**
	 * Método responsável por realizar a interação no momento da montagem da hierarquia de pastas.
	 * @throws IOException
	 */
	
	public void mountMenu() throws IOException{
		
		// Variável utilizada para validar a entrada do usuário.
		
		boolean valid = false;
		
		System.out.println("SGAD - Sistema de Gerenciamento de \n    Arquivos e Diretórios\n\n");
		System.out.println("Digite o caminho completo da pasta a ser montada: ");
		
		// Enquanto o caminho não for válido o programa continuará solicitando.
		
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
		
		/* Quando o caminho for válido uma mensagem de confirmação é exibida.
		   e a chamada ao menu principal é realizada*/
		
		System.out.println("Hierarquia montada com sucesso !");
		this.mainMenu();
	}
	
	/**
	 * Método responsável pela interação no menu principal de opções.
	 * @throws IOException
	 */
	
	private void mainMenu() throws IOException{
		
		/* Variáveis para validação de entrada, armazenamento
		   da opção selecionada e permitir a finalização do aplicativo.*/
		
		boolean valid = false, exit = false;
		int option = 0;
		
		// Enquanto o usuário não pressionar a opção de sair.
		
		while(!exit){
			
			System.out.println("\n          Menu Principal\n");
			System.out.println("1 - Pesquisar arquivo através do nome");
			System.out.println("2 - Pesquisar diretório através do nome");
			System.out.println("3 - Pesquisar arquivo por tipo");
			System.out.println("4 - Exportar estrutura de diretório");
			System.out.println("9 - Sair");

			// Validação da opção selecionada
			
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
			
			// Switch case de acordo com a opção selecionada pelo usuário no menu.
			
			switch(option){
			case 1:
				System.out.print("Digite o nome do arquivo: ");
				String name = Console.readString();
				System.out.print("\nDigite o nível de profundidade: ");
				int depth1 = Console.readInt();
				
				//O metodo de busca retornará um iterador da lista a ser exibida
				
				Iterator<Object> i1 = null;
				try {
					i1 = controller.seachByName(name, depth1);
				} catch (ArquivoNaoEncontradoException e) {
					
					// Caso a exception seja capturada, uma mensagem de erro é exibida ao usuário
					
					System.out.println("Arquivo não encontrado !");
				}
				
				// Exibição da lista de resultados encontrados
				
				if(i1 != null){
					System.out.println("Resultados: ");
					while(i1.hasNext()){
						System.out.println((String)i1.next());
					}
				}
				
				break;
				
			case 2:
				System.out.print("Digite o nome do diretório: ");
				String folder = Console.readString();
				System.out.print("\nDigite o nível de profundidade: ");
				int depth2 = Console.readInt();

				//O metodo de busca retornará um iterador da lista a ser exibida
				
				Iterator<Object> i2 = null;
				try {
					i2 = controller.seachByFolder(folder, depth2);
				} catch (PastaNaoEncontradaException e) {
					
					// Caso a exception seja capturada, uma mensagem de erro é exibida ao usuário
					
					System.out.println("Diretório não encontrado !");
				}
				
				// Exibição da lista de resultados encontrados
				
				if(i2 != null){
					System.out.println("Resultados: ");
					while(i2.hasNext()){
						System.out.println((String)i2.next());
					}
				}

				break;
				
			case 3:
				System.out.print("Digite o tipo: ");
				String type = Console.readString();
				System.out.print("\nDigite o nível de profundidade: ");
				int depth3 = Console.readInt();
				
				//O metodo de busca retornará um iterador da lista a ser exibida
				
				Iterator<Object> i3 = null;
				try {
					i3 = controller.seachByType(type, depth3);
				} catch (TipoNaoEncontradoException e) {
					
					// Caso a exception seja capturada, uma mensagem de erro é exibida ao usuário
					
					System.out.println("Tipo não encontrado !");
				}
				
				// Exibição da lista de resultados encontrados
				
				if(i3 != null){
					System.out.println("Resultados: ");
					while(i3.hasNext()){
						System.out.println((String)i3.next());
					}
				}
				break;
				
			case 4:
				System.out.print("Digite o caminho: ");
				String path = Console.readString();
				System.out.print("\nDigite o nível de profundidade: ");
				int depth4 = Console.readInt();
				System.out.print("\nDigite o nome do arquivo que será gerado: ");
				String fileName = Console.readString();
				
				/*/ Caso a chamada ao controller retorne uma exception, uma mensagem de erro
				    será exibida na tela */
				
				try {
					controller.exportPath(path, depth4, fileName);
					System.out.println("\nArquivo criado com sucesso !");
				} catch (ArquivoNaoEncontradoException e) {
					System.out.println("Diretório não encontrado !");
				} catch (IOException e){
					System.out.println("Não foi possível criar o arquivo !");
				}
				
				break;
				
			case 9:
				exit = true;
				
				break;
			}
		}
		
			
		
	}

}
