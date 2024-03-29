package br.com.ecommerce.inventario.adapters.endpoint;

import br.com.ecommerce.inventario.entities.dto.ProductModelDto;
import br.com.ecommerce.inventario.entities.model.ProductModel;
import br.com.ecommerce.inventario.usecase.ProductService;
import br.com.ecommerce.inventario.usecase.exception.PayloadError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/produto", produces = "application/json", headers = "Accept=*/*")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    @Operation(summary = "Obtém todos os produtos do estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de produtos  feita com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema(implementation = PayloadError.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = PayloadError.class)))
    })
    public ResponseEntity<List<ProductModel>> getAll(@RequestHeader("Authorization") String authorization) {
        var productList = productService.getAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém os dados de um produto pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de produto por Id feita com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema(implementation = PayloadError.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = PayloadError.class)))
    })
    public ResponseEntity<ProductModel> getById(@PathVariable("id") Long id,
                                                @RequestHeader("Authorization") String authorization) {
        var product = productService.getById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Obtém os dados de um produto pela categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de produto por categoria feita com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema(implementation = PayloadError.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = PayloadError.class)))
    })
    public ResponseEntity<List<ProductModel>> getByCategory(@PathVariable("categoria") String categoria,
                                                            @RequestHeader("Authorization") String authorization) {
        var product = productService.getByCategory(categoria);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema(implementation = PayloadError.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = PayloadError.class)))
    })
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModelDto produtoDto,
                                                      @RequestHeader("Authorization") String authorization) {
        var produtoCriado = productService.create(produtoDto);
        return new ResponseEntity<>(produtoCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edita um produto a partir de um id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Edição de produto feita com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema(implementation = PayloadError.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = PayloadError.class)))
    })
    public ResponseEntity<ProductModel> editById(@PathVariable("id") Long id, @RequestBody ProductModelDto produtoDto,
                                                 @RequestHeader("Authorization") String authorization){
        var produto = productService.editById(id, produtoDto);
        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta  um produto a partir do id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleção de produto feita com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema(implementation = PayloadError.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = PayloadError.class)))
    })
    public ResponseEntity<ProductModel> deleteById(@PathVariable("id") Long id,
                                                   @RequestHeader("Authorization") String authorization){
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
