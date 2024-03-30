package br.com.ecommerce.inventario.adapters.endpoint;

import br.com.ecommerce.inventario.entities.dto.ProductModelDto;
import br.com.ecommerce.inventario.entities.enuns.EnumCategory;
import br.com.ecommerce.inventario.entities.model.ProductModel;
import br.com.ecommerce.inventario.usecase.impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)

class ProductControllerTest {

    public static final String ENDPOINT_PRODUTO = "/produto";
    public static final String TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiQURNSU4iLCJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.yCdPVMPz5eIv_oZ4Js08DhLE4BoOuuXLOnKm6YIsN6A";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productService;

    @Mock
    private Pageable pageable;

    List<ProductModel> products = new ArrayList<>();
    Page<ProductModel> productPage = new PageImpl<>(products);
    ProductModel product = new ProductModel();

    ProductModelDto productModelDto = new ProductModelDto();


    @BeforeEach
    public void setup() {
        productModelDto.setName("Test Product");
        productModelDto.setCategory("ELETRONICOS");
        productModelDto.setPrice(10.0);
        productModelDto.setQuantity(10);
        productModelDto.setDescription("Test Description");


        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(10.0);
        product.setQuantity(10);
        product.setCategory(EnumCategory.ELETRONICOS);
        product.setDescription("Test Description");

        products.add(product);
    }

    @Test
    void testGetAll() throws Exception {
        when(productService.getAll(any(Pageable.class))).thenReturn(productPage);
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_PRODUTO)
                .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", TOKEN)
                .content(new ObjectMapper().writeValueAsString(products)))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        when(productService.getById(1L)).thenReturn(product);
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_PRODUTO + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", TOKEN)
                .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(status().isOk());
    }

    @Test
    void getByCategory() throws Exception {
        when(productService.getByCategory(anyString(), any(Pageable.class))).thenReturn(productPage);
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_PRODUTO + "/categoria/ELETRONICOS")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", TOKEN)
                .content(new ObjectMapper().writeValueAsString(products)))
                .andExpect(status().isOk());
    }

    @Test
    void createProduct() throws Exception {
        when(productService.create(productModelDto)).thenReturn(product);
        mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_PRODUTO)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", TOKEN)
                .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(status().isCreated());
    }

    @Test
    void editProduct() throws Exception{
        when(productService.editById(1L, productModelDto)).thenReturn(product);
        mockMvc.perform(MockMvcRequestBuilders.put(ENDPOINT_PRODUTO + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", TOKEN)
                .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteProduct() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_PRODUTO + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", TOKEN)
                .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(status().isNoContent());
    }
}
