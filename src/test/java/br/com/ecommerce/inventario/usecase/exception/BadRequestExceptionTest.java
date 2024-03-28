package br.com.ecommerce.inventario.usecase.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BadRequestExceptionTest {
    @Test
    void testMessage() {
        String message = "Test message";
        BadRequestException exception = new BadRequestException(message);
        assertEquals(message, exception.getMessage());
    }
}
