package br.com.ecommerce.inventario.adapters.handler;

import br.com.ecommerce.inventario.usecase.exception.BadRequestException;
import br.com.ecommerce.inventario.usecase.exception.ForbiddenException;
import br.com.ecommerce.inventario.usecase.exception.InternalServerErrorException;
import br.com.ecommerce.inventario.usecase.exception.InvalidTokenException;
import br.com.ecommerce.inventario.usecase.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static br.com.ecommerce.inventario.utils.Constants.CAMPO;
import static br.com.ecommerce.inventario.utils.Constants.ERROS;
import static br.com.ecommerce.inventario.utils.Constants.MENSAGEM;
import static br.com.ecommerce.inventario.utils.Constants.STATUS;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        Map<String, String> body = configException(MENSAGEM, ex.getMessage(), STATUS, HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
        Map<String, String> body = configException(MENSAGEM, ex.getMessage(), STATUS, HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Object> handleTokenException(InvalidTokenException ex, WebRequest request) {
        Map<String, String> body = configException(MENSAGEM, ex.getMessage(), STATUS, HttpStatus.UNAUTHORIZED.toString());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException ex, WebRequest request) {
        Map<String, String> body = configException(MENSAGEM, ex.getMessage(), STATUS, HttpStatus.FORBIDDEN.toString());
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> handleInternalServerErrorException(InternalServerErrorException ex, WebRequest request) {
        Map<String, String> body = configException(MENSAGEM, ex.getMessage(), STATUS, HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        List<Map<String, String>> validacoes = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(CAMPO, fieldError.getField());
                    error.put(MENSAGEM, fieldError.getDefaultMessage());
                    return error;
                })
                .collect(Collectors.toList());
        body.put(MENSAGEM, "Dados de requisição incorretos");
        body.put(STATUS, HttpStatus.BAD_REQUEST.toString());
        body.put(ERROS, validacoes);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    private static Map<String, String> configException(String mensagem, String ex, String status, String httpStatus) {
        Map<String, String> body = new HashMap<>();
        body.put(mensagem, ex);
        body.put(status, httpStatus);
        return body;
    }
}
