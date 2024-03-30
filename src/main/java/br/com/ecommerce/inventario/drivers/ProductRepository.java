package br.com.ecommerce.inventario.drivers;

import br.com.ecommerce.inventario.entities.enuns.EnumCategory;
import br.com.ecommerce.inventario.entities.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    @Query(value = "SELECT * FROM tb_produto p WHERE category = ?1", nativeQuery = true)
    Page<ProductModel> findByCategory(EnumCategory category, Pageable pageable);
}
