package br.com.ecommerce.inventario.adapters.handler;

import br.com.ecommerce.inventario.usecase.exception.BadRequestException;
import br.com.ecommerce.inventario.usecase.exception.ForbiddenException;
import br.com.ecommerce.inventario.usecase.exception.InternalServerErrorException;
import br.com.ecommerce.inventario.usecase.exception.InvalidTokenException;
import br.com.ecommerce.inventario.usecase.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ControllerExceptionHandlerTest {
    @InjectMocks
    private ControllerExceptionHandler customExceptionHandler;

    private WebRequest webRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        webRequest = new ServletWebRequest(new MockHttpServletRequest());
    }

    @Test
    void testHandleNotFoundException() {
        NotFoundException exception = new NotFoundException("Not found");
        ResponseEntity<Object> responseEntity = customExceptionHandler.handleNotFoundException(exception, webRequest);
        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    void testHandleForbiddenException() {
        ForbiddenException exception = new ForbiddenException("Forbidden");
        ResponseEntity<Object> responseEntity = customExceptionHandler.handleForbiddenException(exception, webRequest);
        assertEquals(403, responseEntity.getStatusCodeValue());
    }

    @Test
    void testHandleInvalidTokenException() {
        InvalidTokenException exception = new InvalidTokenException("Forbidden");
        ResponseEntity<Object> responseEntity = customExceptionHandler.handleTokenException(exception, webRequest);
        assertEquals(401, responseEntity.getStatusCodeValue());
    }

    @Test
    void testHandleInternalServerError() {
        InternalServerErrorException exception = new InternalServerErrorException("Internal server error");
        ResponseEntity<Object> responseEntity = customExceptionHandler.handleInternalServerErrorException(exception, webRequest);
        assertEquals(500, responseEntity.getStatusCodeValue());
    }

    @Test
    void testBadRequest() {
        BadRequestException exception = new BadRequestException("bad request error");
        ResponseEntity<Object> responseEntity = customExceptionHandler.handleBadRequestException(exception, webRequest);
        assertEquals(400, responseEntity.getStatusCodeValue());
    }


    @Test
    void testHandleMethodArgumentNotValid() {
        FieldError fieldError = new FieldError("objectName", "field", "defaultMessage");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

        WebRequest request = new ServletWebRequest(new MockHttpServletRequest());

        ResponseEntity<Object> responseEntity = customExceptionHandler.handleMethodArgumentNotValid(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());


        Map<String, Object> body = (Map<String, Object>) responseEntity.getBody();
        assertEquals("Dados de requisição incorretos", body.get("mensagem"));

        List<Map<String, String>> validacoes = (List<Map<String, String>>) body.get("validacoes");
        assertEquals(1, validacoes.size());
        assertEquals("field", validacoes.get(0).get("campo"));
        assertEquals("defaultMessage", validacoes.get(0).get("mensagem"));
    }
}
