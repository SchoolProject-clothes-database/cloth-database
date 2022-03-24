package se.iths.clothdatabase.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import se.iths.clothdatabase.controller.ProductController;
import se.iths.clothdatabase.entity.ProductEntity;
import se.iths.clothdatabase.service.ProductService;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultHandlers.exportTestSecurityContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class UserSecurityTests {

    @Autowired
    private WebApplicationContext context;


    private MockMvc mvc;

    @MockBean
    ProductService productService;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

    }
    @Test
    @WithMockUser()
    void noneUserIsNotAuthenticated() throws Exception{
        mvc
                .perform(formLogin().password("invalid"))
                .andExpect(unauthenticated());
    }

    @Test
    @WithMockUser(username = "user",password = "pass",roles = "USER")
    void userShouldNotBeAbleToAccessAdminLevelPage() throws Exception{
        mvc
                .perform(get("/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user",password = "pass",roles = "USER")
    void userShouldBeAbleToAccessUserLevelPage() throws Exception{
        mvc
                .perform(get("/category/findAll"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user",password = "pass",roles = "ADMIN")
    void adminUserShouldBeAbleToAccessUserLevelPage() throws Exception{
        mvc
                .perform(get("/category/findAll"))
                .andExpect(status().isOk());
    }

}
