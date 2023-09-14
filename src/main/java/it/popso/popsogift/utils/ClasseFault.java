package it.popso.popsogift.utils;


public enum ClasseFault {
	APPLICATION_FAULT,
	DATI_TESTATA_FAULT,
	INPUT_FAULT,
	SERVIZIO_NON_DISPONIBILE_FAULT,
	SYSTEM_FAULT,
	DUBBIA_RISOTTOMISSIBILE_FAULT,
	DUBBIA_NON_RISOTTOMISSIBILE_FAULT,
	DUBBIA_RISOTTOMISSIBILE_CON_INTERVENTO_MANUALE_FAULT,
	FORBIDDEN;

	private ClasseFault() {
	}
}
