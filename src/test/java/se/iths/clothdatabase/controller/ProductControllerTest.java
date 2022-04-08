package se.iths.clothdatabase.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.clothdatabase.repository.RoleRepository;
import se.iths.clothdatabase.security.WebSecurityConfig;
import se.iths.clothdatabase.service.ProductService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@Import({WebSecurityConfig.class})
class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private RoleRepository roleRepository;

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "ADMIN")
    void getProductByIdShouldReturnStatus200() throws Exception {
        mvc.perform(get("/product/find/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "ADMIN")
    void getAllProductShouldReturnStatus200() throws Exception {
        mvc.perform(get("/product/findAll"))
                .andExpect(status().isOk());
    }
}