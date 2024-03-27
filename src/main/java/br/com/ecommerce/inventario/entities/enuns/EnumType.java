package br.com.ecommerce.inventario.entities.enuns;

import br.com.ecommerce.inventario.usecase.exception.NotFoundException;

import java.util.Arrays;

public enum EnumType {
    ENTRADA,
    SAIDA;

    //TODO TROCAR PARA EXCEPTION ADEQUADA
    public static EnumType getType(final String type) {
        return Arrays.stream(values())
                .filter(t -> t.name().equalsIgnoreCase(type))
                .findAny()
                .orElseThrow(() -> new NotFoundException("Tipo só pode ser ENTRADA ou SAIDA"));
    }
}
