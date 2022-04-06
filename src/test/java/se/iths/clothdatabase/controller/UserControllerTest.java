package se.iths.clothdatabase.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.clothdatabase.entity.UserEntity;
import se.iths.clothdatabase.repository.RoleRepository;
import se.iths.clothdatabase.security.WebSecurityConfig;
import se.iths.clothdatabase.service.UserService;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@Import({WebSecurityConfig.class})
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private RoleRepository roleRepository;

    @Test
    void createNewUserShouldReturnStatus201() throws Exception {
        var user = new UserEntity();
        user.setUsername("userTest");
        user.setPassword("passwordTest");
        when(userService.createUser(any(UserEntity.class))).thenReturn(user);

        mvc.perform(post("/users/signup").contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                   "username":"userTest",
                   "password":"passwordTest"
                }              
                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("userTest"));
    }

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "ADMIN")
    void getUserByIdShouldReturnStatus200() throws Exception {

        mvc.perform(get("/users/find/1000"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "ADMIN")
    void getAllUsersShouldReturnStatus200() throws Exception {

        mvc.perform(get("/users/findAll"))
                .andExpect(status().isOk());
    }
}