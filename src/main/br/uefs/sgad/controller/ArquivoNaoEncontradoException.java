package main.br.uefs.sgad.controller;

public class ArquivoNaoEncontradoException extends Exception {
	
	/**
	 * Necessário para a serialização da exception.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor passa a mensagem de erro para a classe Throwable, que imprimira-la na tela.
	 * @param msg
	 */
	
	public ArquivoNaoEncontradoException(String msg) {
		super(msg);
	}

}
