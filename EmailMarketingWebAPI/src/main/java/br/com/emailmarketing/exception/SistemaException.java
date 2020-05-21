package br.com.emailmarketing.exception;

public class SistemaException extends RuntimeException {
	
	private static final long serialVersionUID = -3518225145837382234L;

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
