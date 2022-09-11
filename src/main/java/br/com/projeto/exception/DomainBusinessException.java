package br.com.projeto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class DomainBusinessException extends RuntimeException {

    public DomainBusinessException(String mensagem) {
        super(mensagem);
    }
}
