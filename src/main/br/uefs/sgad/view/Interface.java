package main.br.uefs.sgad.view;

import java.io.IOException;

import main.br.uefs.sgad.util.Console;
import main.br.uefs.sgad.controller.*;

public class Interface {
	
	private SgadController controller;
	
	public Interface(){
		this.controller = new SgadController();
	}
	
	public void mountMenu(){
		
		boolean valid = true;
		
		System.out.println("SGAD - Sistema de Gerenciamento de \n    Arquivos e Diretórios\n\n");
		System.out.println("Digite o caminho completo da pasta a ser montada");
		
		while(!valid){
			try {
				valid = true;
				System.out.println("Caminho: ");
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
		

		
	}

}
