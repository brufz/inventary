package br.com.ecommerce.inventario.service;

import br.com.ecommerce.inventario.model.ProductModel;
import br.com.ecommerce.inventario.model.ProductModelDto;

public interface ProductService {
    ProductModel create(ProductModelDto produtoDto);
    ProductModel getById(Long id);
    ProductModel getByCategory(String categoria);
}
