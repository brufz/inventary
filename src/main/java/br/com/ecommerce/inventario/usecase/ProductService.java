package br.com.ecommerce.inventario.usecase;

import br.com.ecommerce.inventario.entities.dto.ProductModelDto;
import br.com.ecommerce.inventario.entities.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductModel create(ProductModelDto produtoDto);
    ProductModel getById(Long id);
    Page<ProductModel> getAll(Pageable pageable);
    Page<ProductModel> getByCategory(String categoria, Pageable pageable);
    ProductModel editById(Long id, ProductModelDto produtoDto);
    void deleteById(Long id);
}
