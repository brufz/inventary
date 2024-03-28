package br.com.ecommerce.inventario.usecase.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IllegalArgumentExceptionTest {
    @Test
    void testMessage() {
        String message = "Test message";
        IllegalArgumentException exception = new IllegalArgumentException(message);
        assertEquals(message, exception.getMessage());
    }
}
