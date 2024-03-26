package br.com.ecommerce.inventario.adapter;

import br.com.ecommerce.inventario.model.ProductModel;
import br.com.ecommerce.inventario.model.enuns.EnumCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    @Query(value = "SELECT * FROM tb_produto p WHERE category = ?1", nativeQuery = true)
    List<ProductModel> findByCategory(EnumCategory category);
}
