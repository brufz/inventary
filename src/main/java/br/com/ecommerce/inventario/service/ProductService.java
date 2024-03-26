package br.com.ecommerce.inventario.service;

import br.com.ecommerce.inventario.model.ProductModel;
import br.com.ecommerce.inventario.model.ProductModelDto;

import java.util.List;

public interface ProductService {
    ProductModel create(ProductModelDto produtoDto);
    ProductModel getById(Long id);
    List<ProductModel> getByCategory(String categoria);
}
