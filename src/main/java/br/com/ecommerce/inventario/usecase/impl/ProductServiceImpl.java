package br.com.ecommerce.inventario.usecase.impl;

import br.com.ecommerce.inventario.drivers.InOutRepository;
import br.com.ecommerce.inventario.drivers.ProductRepository;
import br.com.ecommerce.inventario.entities.dto.ProductModelDto;
import br.com.ecommerce.inventario.entities.enuns.EnumCategory;
import br.com.ecommerce.inventario.entities.enuns.EnumType;
import br.com.ecommerce.inventario.entities.model.InOutModel;
import br.com.ecommerce.inventario.entities.model.ProductModel;
import br.com.ecommerce.inventario.usecase.ProductService;
import br.com.ecommerce.inventario.usecase.exception.BadRequestException;
import br.com.ecommerce.inventario.usecase.exception.NotFoundException;
import br.com.ecommerce.inventario.usecase.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final InOutRepository inOutRepository;

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
    public List<ProductModel> getByCategory(String categoria) {
        EnumCategory enumCategory = EnumCategory.getCategory(categoria);
        return Optional.ofNullable(productRepository.findByCategory(enumCategory))
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

    @Override
    @Transactional
    public ProductModel editQuantity(Long id) {
        InOutModel inOutModel = inOutRepository.findById(id).orElseThrow(() -> new NotFoundException("Registro de entrada/saída com id " + id + " não encontrado"));
        ProductModel productModel = productRepository.findById(inOutModel.getProductId()).orElseThrow(() -> new NotFoundException("Produto com id " + inOutModel.getProductId() + " não encontrado"));

        switch (EnumType.valueOf(inOutModel.getTypeMov())) {
            case ENTRADA -> productModel.setQuantity(productModel.getQuantity() + inOutModel.getQuantity());
            case SAIDA -> {
                if (productModel.getQuantity() < inOutModel.getQuantity()) {
                    throw new BadRequestException("Quantidade de produtos insuficiente");
                }
                productModel.setQuantity(productModel.getQuantity() - inOutModel.getQuantity());
            }
            default -> throw new IllegalArgumentException("Tipo de movimento inválido: " + inOutModel.getTypeMov());
        }
        return productRepository.save(productModel);
    }


}
