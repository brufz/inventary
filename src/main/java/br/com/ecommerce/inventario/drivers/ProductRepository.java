package br.com.ecommerce.inventario.drivers;

import br.com.ecommerce.inventario.entities.model.ProductModel;
import br.com.ecommerce.inventario.entities.enuns.EnumCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    @Query(value = "SELECT * FROM tb_produto p WHERE category = ?1", nativeQuery = true)
    List<ProductModel> findByCategory(EnumCategory category);

//    @Query(value = )
//    ProductModel editById(Long id, ProductModel produtoDto);
}
