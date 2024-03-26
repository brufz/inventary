package br.com.ecommerce.inventario.endpoint;

import br.com.ecommerce.inventario.model.ProductModel;
import br.com.ecommerce.inventario.model.ProductModelDto;
import br.com.ecommerce.inventario.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModelDto produtoDto) {
        var produtoCriado = productService.create(produtoDto);
        return new ResponseEntity<>(produtoCriado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getById(@PathVariable("id") Long id) {
        var produto = productService.getById(id);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<ProductModel> getByCategory(@PathVariable("categoria") String categoria) {
        var produto = productService.getByCategory(categoria);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }
}
