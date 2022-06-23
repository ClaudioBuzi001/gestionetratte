package it.prova.gestionetratte.web.api.exception;

public class TrattaNotFoundException extends RuntimeException {
	public TrattaNotFoundException(String messaggio) {
		super(messaggio);
	}

}
