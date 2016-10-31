package br.edu.facisa.prorio.dao;

public class DAOException extends RuntimeException {

	private static final long serialVersionUID = 3617607762279845496L;

	public DAOException() {
		super();
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

}
