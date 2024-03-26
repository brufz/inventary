package br.com.ecommerce.inventario.service.impl;

import br.com.ecommerce.inventario.adapter.ProductRepository;
import br.com.ecommerce.inventario.model.ProductModel;
import br.com.ecommerce.inventario.model.ProductModelDto;
import br.com.ecommerce.inventario.model.enuns.EnumCategory;
import br.com.ecommerce.inventario.service.ProductService;
import br.com.ecommerce.inventario.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductModel create(ProductModelDto productDto) {
        var productModel = ProductMapper.INSTANCE.toEntity(productDto);
        return productRepository.save(productModel);
    }

    @Override
    public ProductModel getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    public List<ProductModel> getByCategory(String categoria) {
        EnumCategory enumCategory = EnumCategory.getCategory(categoria);
        List<ProductModel> categoryList = productRepository.findByCategory(enumCategory);
        if(categoryList.isEmpty()){
            throw new RuntimeException("Nenhum produto encontrado nesta categoria");
        }
        return categoryList;
    }
}
