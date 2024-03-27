package br.com.ecommerce.inventario.adapters.handler;

import br.com.ecommerce.inventario.usecase.exception.BadRequestException;
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

@ControllerAdvice
public class ControllerExceptionHandler {

    public static final String MENSAGEM = "mensagem";
    public static final String VALIDACOES = "validacoes";

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        Map<String, String> body = configException(MENSAGEM, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
        Map<String, String> body = configException(MENSAGEM, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Object> handleTokenException(InvalidTokenException ex, WebRequest request) {
        Map<String, String> body = configException(MENSAGEM, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> handleInternalServerErrorException(InternalServerErrorException ex, WebRequest request) {
        Map<String, String> body = configException(MENSAGEM, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put(MENSAGEM, "Dados de requisição incorretos");

        List<Map<String, String>> validacoes = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> {
                    Map<String, String> error = configException("campo", fieldError.getField());
                    error.put(MENSAGEM, fieldError.getDefaultMessage());
                    return error;
                })
                .collect(Collectors.toList());

        body.put(VALIDACOES, validacoes);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    private static Map<String, String> configException(String mensagem, String ex) {
        Map<String, String> body = new HashMap<>();
        body.put(mensagem, ex);
        return body;
    }
}
