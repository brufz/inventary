package br.com.ecommerce.inventario.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static br.com.ecommerce.inventario.utils.Constants.NOT_BLANK_MESSAGE;
import static br.com.ecommerce.inventario.utils.Constants.NOT_NULL_MESSAGE;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductModelDto {
    @NotBlank(message = NOT_BLANK_MESSAGE)
    @JsonProperty("nome")
    private String name;
    @NotBlank(message = NOT_BLANK_MESSAGE)
    @JsonProperty("descricao")
    private String description;
    @NotBlank(message = NOT_BLANK_MESSAGE)
    @JsonProperty("categoria")
    private String category;
    @NotNull(message = NOT_NULL_MESSAGE)
    @JsonProperty("preco")
    private Double price;
    @NotNull(message = NOT_NULL_MESSAGE)
    @JsonProperty("quantidade")
    private Integer quantity;

}
