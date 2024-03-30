package br.com.ecommerce.inventario.usecase.impl;

import br.com.ecommerce.inventario.drivers.ProductRepository;
import br.com.ecommerce.inventario.entities.dto.ProductModelDto;
import br.com.ecommerce.inventario.entities.enuns.EnumCategory;
import br.com.ecommerce.inventario.entities.model.ProductModel;
import br.com.ecommerce.inventario.usecase.ProductService;
import br.com.ecommerce.inventario.usecase.exception.NotFoundException;
import br.com.ecommerce.inventario.usecase.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductModel create(ProductModelDto productDto) {
        var productModel = ProductMapper.INSTANCE.toEntity(productDto);
        return productRepository.save(productModel);
    }

    @Override
    public ProductModel getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Produto com id " + id + " não encontrado"));
    }

    @Override
    public Page<ProductModel> getAll(Pageable pageable) {
        return (productRepository.findAll(pageable));
    }

    @Override
    public Page<ProductModel> getByCategory(String categoria, Pageable pageable){
        EnumCategory enumCategory = EnumCategory.getCategory(categoria);
        return Optional.ofNullable(productRepository.findByCategory(enumCategory, pageable))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new NotFoundException("Nenhum produto encontrado na categoria " + categoria));
    }

    @Override
    public ProductModel editById(Long id, ProductModelDto produtoDto) {
        if(productRepository.existsById(id)){
            ProductModel productById = ProductModel.builder().id(id)
                    .name(produtoDto.getName())
                    .category(EnumCategory.getCategory(produtoDto.getCategory()))
                    .description(produtoDto.getDescription())
                    .price(produtoDto.getPrice())
                    .quantity(produtoDto.getQuantity())
                    .build();
            return productRepository.save(productById);
        }
        throw new NotFoundException("Produto com id " + id + " não encontrado");
    }

    @Override
    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Produto com id " + id + " não encontrado");
        }
        productRepository.deleteById(id);
    }
}
