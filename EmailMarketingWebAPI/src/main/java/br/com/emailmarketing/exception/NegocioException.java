package br.com.emailmarketing.exception;

public class NegocioException extends RuntimeException {
	
	private static final long serialVersionUID = -8823231711327581692L;

	public NegocioException(String message) {
		super(message);
	}
	
	public NegocioException(String message, Throwable e) {
		super(message, e);
	}
	
}
