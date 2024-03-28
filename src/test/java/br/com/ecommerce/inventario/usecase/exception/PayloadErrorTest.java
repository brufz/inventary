package br.com.ecommerce.inventario.usecase.exception;

import org.junit.jupiter.api.Test;

class PayloadErrorTest {

    @Test
    void testPayloadError() {
        PayloadError payloadError = new PayloadError();
        payloadError.setMensagem("mensagem");
        assert(payloadError.getMensagem().equals("mensagem"));
    }
}
