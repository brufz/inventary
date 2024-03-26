package br.com.ecommerce.inventario.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductModelDto {
    @NotBlank
    @JsonProperty("nome")
    private String name;
    @NotBlank
    @JsonProperty("descricao")
    private String description;
    @NotBlank
    @JsonProperty("categoria")
    private String category;
    @NotNull
    @JsonProperty("preco")
    private Double price;
    @NotNull
    @JsonProperty("quantidade")
    private Integer quantity;

}
