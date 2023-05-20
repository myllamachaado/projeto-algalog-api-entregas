package com.algaworks.algalog.api.exceptionHandler;

import com.algaworks.algalog.api.exceptionHandler.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algalog.api.exceptionHandler.message.Campo;
import com.algaworks.algalog.api.exceptionHandler.message.Erro;
import com.algaworks.algalog.api.exceptionHandler.exceptions.NegocioException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        String msgErro = "Um ou mais campos estão inválidos.";
        List<Campo> campos = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()){
            String nomeCampo = ((FieldError )error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            campos.add(new Campo(nomeCampo, mensagem));
        }
        Erro erro = new Erro(status.value(), OffsetDateTime.now(), msgErro, campos);
        return super.handleExceptionInternal(ex, erro, headers, status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Erro erro = new Erro(status.value(), OffsetDateTime.now(), ex.getMessage(), null);
        return super.handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex,
                                                              WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Erro erro = new Erro(status.value(), OffsetDateTime.now(), ex.getMessage(), null);
        return super.handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
    }


}
