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

import static br.com.ecommerce.inventario.utils.Constants.NOT_BLANK_MESSAGE;
import static br.com.ecommerce.inventario.utils.Constants.NOT_NULL_MESSAGE;

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
    @NotBlank(message = NOT_BLANK_MESSAGE)
    @JsonProperty("nome")
    private String name;
    @NotBlank(message = NOT_BLANK_MESSAGE)
    @JsonProperty("descricao")
    private String description;
    @NotNull(message = NOT_NULL_MESSAGE)
    @JsonProperty("categoria")
    private EnumCategory category;
    @NotNull(message = NOT_NULL_MESSAGE)
    @JsonProperty("preco")
    private Double price;
    @NotNull(message = NOT_NULL_MESSAGE)
    @JsonProperty("quantidade")
    private Integer quantity;
}
