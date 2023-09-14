package it.popso.popsogift.utils;

import org.springframework.http.HttpStatus;

public class CustomErrorResponse {
	private ClasseFault classeFault;
	private String codice;
	private String messaggio;
	private String layer;

	public CustomErrorResponse() {
	}

	public CustomErrorResponse(HttpStatus httpStatus, Exception ex, ClasseFault classeFault) {
		this.codice = "" + httpStatus.value();
		this.messaggio = ex.getMessage();
		this.layer = "Applicazione Dipartimentale POPSODOC";
		this.classeFault = classeFault;
	}

	public CustomErrorResponse(HttpStatus httpStatus, String message, ClasseFault classeFault) {
		this.codice = "" + httpStatus.value();
		this.messaggio = message;
		this.layer = "Applicazione Dipartimentale POPSODOC";
		this.classeFault = classeFault;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}

	public String getCodice() {
		return this.codice;
	}

	public String getMessaggio() {
		return this.messaggio;
	}

	public String getLayer() {
		return this.layer;
	}

	public void setClasseFault(ClasseFault classeFault) {
		this.classeFault = classeFault;
	}

	public ClasseFault getClasseFault() {
		return this.classeFault;
	}
}
