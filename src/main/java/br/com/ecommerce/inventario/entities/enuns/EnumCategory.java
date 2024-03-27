package br.com.ecommerce.inventario.entities.enuns;

import br.com.ecommerce.inventario.usecase.exception.NotFoundException;

import java.util.Arrays;

public enum EnumCategory {
    ELETRONICOS,
    MOVEIS,
    BRINQUEDOS,
    ALIMENTOS,
    BEBIDAS,
    LIMPEZA,
    HIGIENE,
    VESTUARIO,
    CALCADOS,
    ACESSORIOS,
    OUTROS;

    public static EnumCategory getCategory(final String category) {
        return Arrays.stream(values())
                .filter(type -> type.name().equalsIgnoreCase(category))
                .findAny()
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));
    }
}
