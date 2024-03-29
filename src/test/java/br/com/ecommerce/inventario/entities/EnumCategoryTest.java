package br.com.ecommerce.inventario.entities;

import br.com.ecommerce.inventario.entities.enuns.EnumCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnumCategoryTest {

    @Test
    void testValueOfWithValidString() {
        EnumCategory category = EnumCategory.valueOf("ELETRONICOS");
        assertEquals(EnumCategory.ELETRONICOS, category);

        category = EnumCategory.valueOf("VESTUARIO");
        assertEquals(EnumCategory.VESTUARIO, category);
    }

    @Test
    void testValueOfWithInvalidString() {
        assertThrows(IllegalArgumentException.class, () -> EnumCategory.valueOf("INVALID"));
    }
}
