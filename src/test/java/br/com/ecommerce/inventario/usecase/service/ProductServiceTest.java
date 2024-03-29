package br.com.ecommerce.inventario.usecase.service;

import br.com.ecommerce.inventario.drivers.ProductRepository;
import br.com.ecommerce.inventario.entities.dto.ProductModelDto;
import br.com.ecommerce.inventario.entities.enuns.EnumCategory;
import br.com.ecommerce.inventario.entities.model.ProductModel;
import br.com.ecommerce.inventario.usecase.exception.NotFoundException;
import br.com.ecommerce.inventario.usecase.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceTest {
    @InjectMocks
    ProductServiceImpl productService;
    @Mock
    ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        ProductModelDto productModelDto = new ProductModelDto();
        productModelDto.setCategory("ELETRONICOS");
        ProductModel productModel = new ProductModel();
        when(productRepository.save(any(ProductModel.class))).thenReturn(productModel);

        ProductModel result = productService.create(productModelDto);

        assertEquals(productModel, result);
        verify(productRepository, times(1)).save(any(ProductModel.class));
    }

    @Test
    void testGetById() {
        ProductModel productModel = new ProductModel();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(productModel));

        ProductModel result = productService.getById(1L);

        assertEquals(productModel, result);
        verify(productRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGetByCategory() {
        List<ProductModel> productModel = new ArrayList<>();
        ProductModel pm1 = new ProductModel(1L, "Produto 1", "ELETRONICOS", EnumCategory.ELETRONICOS, 100.0, 10);
        productModel.add(pm1);
        when(productRepository.findByCategory(any())).thenReturn(productModel);

        List<ProductModel> result = productService.getByCategory("ELETRONICOS");

        assertEquals(productModel, result);
        verify(productRepository, times(1)).findByCategory(any());
    }

    @Test
    void testDeleteById() {
        when(productRepository.existsById(anyLong())).thenReturn(true);

        productService.deleteById(1L);

        verify(productRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testDeleteNotFound() {
        when(productRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(NotFoundException.class, () -> productService.deleteById(1L));
        verify(productRepository, times(1)).existsById(anyLong());
    }

}
