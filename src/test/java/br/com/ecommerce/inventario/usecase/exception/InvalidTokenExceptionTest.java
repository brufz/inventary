package br.com.ecommerce.inventario.usecase.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidTokenExceptionTest {
    @Test
    void testMessage() {
        String message = "Test message";
        InvalidTokenException exception = new InvalidTokenException(message);
        assertEquals(message, exception.getMessage());
    }
}
