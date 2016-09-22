package main.br.uefs.sgad.view;

import java.io.IOException;

/**
 * @author Paulo Fernando Pimentel Lima Vaz
 *
 * Classe de inicialização
 */

public class SGAD {
	
	/**
	 * Método main que inicializa o programa criando a interface.
	 */
	
	public static void main(String[] args) throws IOException{
		
		Interface main = new Interface();
		main.mountMenu();
		
	}
}
