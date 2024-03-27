package br.com.ecommerce.inventario.drivers;

import br.com.ecommerce.inventario.entities.model.InOutModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InOutRepository extends JpaRepository<InOutModel, Long> {
}
