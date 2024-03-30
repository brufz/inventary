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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import static br.com.ecommerce.inventario.utils.Constants.AUTHORIZATION;
import static br.com.ecommerce.inventario.utils.Constants.CODE_200_STRING;
import static br.com.ecommerce.inventario.utils.Constants.CODE_201_STRING;
import static br.com.ecommerce.inventario.utils.Constants.CODE_400_STRING;
import static br.com.ecommerce.inventario.utils.Constants.CODE_500_STRING;
import static br.com.ecommerce.inventario.utils.Constants.DESCRIPTION_400;
import static br.com.ecommerce.inventario.utils.Constants.DESCRIPTION_500;

@RestController
@RequestMapping(value = "/produto", produces = "application/json", headers = "Accept=*/*")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @Operation(summary = "Obtém todos os produtos do estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_200_STRING, description = "Busca de produtos  feita com sucesso"),
            @ApiResponse(responseCode = CODE_400_STRING, description = DESCRIPTION_400, content = @Content(schema = @Schema(implementation = PayloadError.class))),
            @ApiResponse(responseCode = CODE_500_STRING, description = DESCRIPTION_500, content = @Content(schema = @Schema(implementation = PayloadError.class)))
    })
    public ResponseEntity<Page<ProductModel>> getAll(@RequestHeader(AUTHORIZATION) String authorization,
                                                     Pageable pageable) {
        var productList = productService.getAll(pageable);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém os dados de um produto pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_200_STRING, description = "Busca de produto por Id feita com sucesso"),
            @ApiResponse(responseCode = CODE_400_STRING, description = DESCRIPTION_400, content = @Content(schema = @Schema(implementation = PayloadError.class))),
            @ApiResponse(responseCode = CODE_500_STRING, description = DESCRIPTION_500, content = @Content(schema = @Schema(implementation = PayloadError.class)))
    })
    public ResponseEntity<ProductModel> getById(@PathVariable("id") Long id,
                                                @RequestHeader(AUTHORIZATION) String authorization) {
        var product = productService.getById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Obtém os dados de um produto pela categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_200_STRING, description = "Busca de produto por categoria feita com sucesso"),
            @ApiResponse(responseCode = CODE_400_STRING, description = DESCRIPTION_400, content = @Content(schema = @Schema(implementation = PayloadError.class))),
            @ApiResponse(responseCode = CODE_500_STRING, description = DESCRIPTION_500, content = @Content(schema = @Schema(implementation = PayloadError.class)))
    })
    public ResponseEntity<Page<ProductModel>> getByCategory(@PathVariable("categoria") String categoria,
                                                            @RequestHeader(AUTHORIZATION) String authorization,
                                                            Pageable pageable) {
        var product = productService.getByCategory(categoria, pageable);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_201_STRING, description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = CODE_400_STRING, description = DESCRIPTION_400, content = @Content(schema = @Schema(implementation = PayloadError.class))),
            @ApiResponse(responseCode = CODE_500_STRING, description = DESCRIPTION_500, content = @Content(schema = @Schema(implementation = PayloadError.class)))
    })
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModelDto produtoDto,
                                                      @RequestHeader(AUTHORIZATION) String authorization) {
        var produtoCriado = productService.create(produtoDto);
        return new ResponseEntity<>(produtoCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edita um produto a partir de um id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_201_STRING, description = "Edição de produto feita com sucesso"),
            @ApiResponse(responseCode = CODE_400_STRING, description = DESCRIPTION_400, content = @Content(schema = @Schema(implementation = PayloadError.class))),
            @ApiResponse(responseCode = CODE_500_STRING, description = DESCRIPTION_500, content = @Content(schema = @Schema(implementation = PayloadError.class)))
    })
    public ResponseEntity<ProductModel> editById(@PathVariable("id") Long id, @RequestBody ProductModelDto produtoDto,
                                                 @RequestHeader(AUTHORIZATION) String authorization){
        var produto = productService.editById(id, produtoDto);
        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta  um produto a partir do id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleção de produto feita com sucesso"),
            @ApiResponse(responseCode = CODE_400_STRING, description = DESCRIPTION_400, content = @Content(schema = @Schema(implementation = PayloadError.class))),
            @ApiResponse(responseCode = CODE_500_STRING, description = DESCRIPTION_500, content = @Content(schema = @Schema(implementation = PayloadError.class)))
    })
    public ResponseEntity<ProductModel> deleteById(@PathVariable("id") Long id,
                                                   @RequestHeader(AUTHORIZATION) String authorization){
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
