package br.com.ecommerce.inventario.usecase.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotFoundExceptionTest {
    @Test
    void testMessage() {
        String message = "Test message";
        NotFoundException exception = new NotFoundException(message);
        assertEquals(message, exception.getMessage());
    }
}
