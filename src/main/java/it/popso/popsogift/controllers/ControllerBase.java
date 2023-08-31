package it.popso.popsogift.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import it.popso.popsogift.dto.Fault;
import it.popso.popsogift.exceptions.*;
import jakarta.validation.ConstraintViolationException;

public abstract class ControllerBase {

    abstract ResponseEntity<Object> getFaultResponse(HttpStatus status, RuntimeException ex);

    protected Fault getFaultObject(Fault.ClasseFault classeFault, String codice, String messaggio, String layer) {
        return Fault.builder()
                .classeFault(classeFault)
                .codice(codice)
                .messaggio(messaggio)
                .layer(layer)
                .build();
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> applicationFaultException(ApplicationFault e) {

        return getFaultResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> datiTestataFaultException(DatiTestataFault e) {

        return getFaultResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> inputFaultException(InputFault e) {

        return getFaultResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<Object> servizioNonDisponibileFaultException(ServizioNonDisponibileFault e) {

        return getFaultResponse(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<Object> systemFaultException(SystemFault e) {

        return getFaultResponse(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<Object> dubbiaRisottomissibileFaultException(DubbiaRisottomissibileFault e) {

        return getFaultResponse(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<Object> dubbiaNonRisottomissibileFaultException(DubbiaNonRisottomissibileFault e) {

        return getFaultResponse(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<Object> dubbiaRisottomissibileConInterventoManualeFaultException(DubbiaRisottomissibileConInterventoManualeFault e) {

        return getFaultResponse(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> validationException(ConstraintViolationException e) {

        return getFaultResponse(HttpStatus.BAD_REQUEST, e);
    }



}