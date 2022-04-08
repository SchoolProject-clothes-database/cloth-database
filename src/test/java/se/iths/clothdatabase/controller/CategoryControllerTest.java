package se.iths.clothdatabase.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.clothdatabase.entity.CategoryEntity;
import se.iths.clothdatabase.repository.RoleRepository;
import se.iths.clothdatabase.security.WebSecurityConfig;
import se.iths.clothdatabase.service.CategoryService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CategoryController.class)
@Import({WebSecurityConfig.class})
class CategoryControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private RoleRepository roleRepository;

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "ADMIN")
    void creatNewCategoryShouldReturnStatus201() throws Exception {
        var category = new CategoryEntity();
        category.setCategoryName("categoryName");
        category.setType("type");
        when(categoryService.createCategory(any(CategoryEntity.class))).thenReturn(category);

        mvc.perform(post("/category").contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                	"categoryName" : "categoryName",
                                	"type" : "type"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.categoryName").value("categoryName"))
                .andExpect(jsonPath("$.type").value("type"));
    }

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "ADMIN")
    void getCategoryByIdShouldReturnStatus200() throws Exception {
        mvc.perform(get("/category/find/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "ADMIN")
    void getAllCategoryShouldReturnStatus200() throws Exception {
        mvc.perform(get("/category/findAll"))
                .andExpect(status().isOk());
    }
}