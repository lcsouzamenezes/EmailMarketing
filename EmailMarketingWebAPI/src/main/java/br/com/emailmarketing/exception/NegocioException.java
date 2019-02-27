package br.com.emailmarketing.exception;

public class NegocioException extends RuntimeException {
	
	public NegocioException(String message) {
		super(message);
	}
	
	public NegocioException(String message, Throwable e) {
		super(message, e);
	}
	
}
