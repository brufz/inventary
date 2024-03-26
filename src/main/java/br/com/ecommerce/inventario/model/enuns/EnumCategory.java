package br.com.ecommerce.inventario.model.enuns;

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
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
    }
}
