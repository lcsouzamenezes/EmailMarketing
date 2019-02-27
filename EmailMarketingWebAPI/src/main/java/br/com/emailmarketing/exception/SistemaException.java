package br.com.emailmarketing.exception;

public class SistemaException extends RuntimeException {
	
	public SistemaException(String message) {
		super(message);
	}

	public SistemaException(Throwable e) {
		super(e);
	}

	public SistemaException(String message, Throwable e) {
		super(message, e);
	}

}
