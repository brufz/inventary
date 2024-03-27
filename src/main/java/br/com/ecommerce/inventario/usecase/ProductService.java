package br.com.ecommerce.inventario.usecase;

import br.com.ecommerce.inventario.entities.dto.ProductModelDto;
import br.com.ecommerce.inventario.entities.model.ProductModel;

import java.util.List;

public interface ProductService {
    ProductModel create(ProductModelDto produtoDto);
    ProductModel getById(Long id);
    List<ProductModel> getByCategory(String categoria);
    ProductModel editById(Long id, ProductModelDto produtoDto);
    void deleteById(Long id);
    ProductModel editQuantity(Long id);
}
