package br.com.ecommerce.inventario.entities.model;

import br.com.ecommerce.inventario.entities.enuns.EnumType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_movimentacao")
public class InOutModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "product_id", updatable = false)
    @JsonProperty("produtoId")
    private Long productId;

    @NotNull
    @JsonProperty("quantidade")
    private Integer quantity;

    @NotNull
    @JsonProperty("tipo")
    @Column(name="type_mov")
    @Pattern(regexp = "ENTRADA|SAIDA")
    private String typeMov;
}
