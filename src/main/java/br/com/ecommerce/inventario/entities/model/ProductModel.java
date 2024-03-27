package br.com.ecommerce.inventario.entities.model;

import br.com.ecommerce.inventario.entities.enuns.EnumCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_produto")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @JsonProperty("nome")
    private String name;
    @NotBlank
    @JsonProperty("descricao")
    private String description;
    @NotNull
    @JsonProperty("categoria")
    private EnumCategory category;
    @NotNull
    @JsonProperty("preco")
    private Double price;
    @NotNull
    @JsonProperty("quantidade")
    private Integer quantity;
}
