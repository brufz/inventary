package br.com.ecommerce.inventario.service.mapper;

import br.com.ecommerce.inventario.model.ProductModel;
import br.com.ecommerce.inventario.model.ProductModelDto;
import br.com.ecommerce.inventario.model.enuns.EnumCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = getMapper(ProductMapper.class);

    @Mapping(target = "category", source = "category", qualifiedByName = "stringToEnum")
    ProductModel toEntity(ProductModelDto model);


    @Named("stringToEnum")
    default EnumCategory stringToEnum(String categoria) {
        return EnumCategory.getCategory(categoria);
    }

}
