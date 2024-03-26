package br.com.ecommerce.inventario.service.impl;

import br.com.ecommerce.inventario.adapter.ProductRepository;
import br.com.ecommerce.inventario.model.ProductModel;
import br.com.ecommerce.inventario.model.ProductModelDto;
import br.com.ecommerce.inventario.model.enuns.EnumCategory;
import br.com.ecommerce.inventario.service.ProductService;
import br.com.ecommerce.inventario.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductModel create(ProductModelDto productDto) {
//        ProductMapper.INSTANCE.toEntity(productDto);
        var productModel = ProductModel.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .category(EnumCategory.getCategory(productDto.getCategory()))
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build();
        return productRepository.save(productModel);
    }

    @Override
    public ProductModel getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    public ProductModel getByCategory(String categoria) {
        return null;
    }
}
