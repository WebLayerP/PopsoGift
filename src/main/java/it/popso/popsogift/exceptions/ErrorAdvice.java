package it.popso.popsogift.exceptions;

import it.popso.popsogift.utils.ClasseFault;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorAdvice extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        BindingResult result = ex.getBindingResult();

        List<String> errorList = new ArrayList<>();
        result.getFieldErrors().forEach(fieldError -> errorList.add(fieldError.getObjectName() + "." + fieldError.getField() + " : " + fieldError.getDefaultMessage() + " : rejected value [" + fieldError.getRejectedValue() + "]")
        );
        result.getGlobalErrors().forEach(fieldError -> errorList.add(fieldError.getObjectName() + " : " + fieldError.getDefaultMessage())
        );
        return handleExceptionInternal(ex, new CustomErrorResponse(status, errorList.toString(), ClasseFault.SYSTEM_FAULT), new HttpHeaders(), status, request);
    }


    private ResponseEntity<Object> handleException(
            RuntimeException ex, HttpStatus httpStatus, WebRequest request, ClasseFault classeFault) {

        return handleExceptionInternal(ex, new CustomErrorResponse(httpStatus, ex.getMessage(),classeFault), new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(ApplicationFaultMsgException.class)
    public ResponseEntity<Object> handleApplicationFaultMsgException(RuntimeException ex, WebRequest request){
        return handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR, request,ClasseFault.APPLICATION_FAULT);
    }


    @ExceptionHandler(InputFaultMsgException.class)
    public ResponseEntity<Object> handleInputFaultMsgException(RuntimeException ex, WebRequest request){
        return handleException(ex, HttpStatus.BAD_REQUEST, request,ClasseFault.INPUT_FAULT);
    }

    @ExceptionHandler(MyHttpErrorException.class)
    public ResponseEntity<Object> handleMyHttpErrorException(RuntimeException ex, WebRequest request){
        return handleException(ex, HttpStatus.FORBIDDEN, request,ClasseFault.FORBIDDEN);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleMyDataIntegrityViolationException(RuntimeException ex, WebRequest request){
        return handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR, request,ClasseFault.APPLICATION_FAULT);
    }
    @ExceptionHandler(CannotCreateTransactionException.class)
    public ResponseEntity<Object> handleMyCannotCreateTransactionException(RuntimeException ex, WebRequest request){
        return handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR, request,ClasseFault.APPLICATION_FAULT);
    }
}
