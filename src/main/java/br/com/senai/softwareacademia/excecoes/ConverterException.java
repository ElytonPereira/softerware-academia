package br.com.senai.softwareacademia.excecoes;

public class ConverterException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ConverterException(String mensagem) {
		super(mensagem);
	}

}
