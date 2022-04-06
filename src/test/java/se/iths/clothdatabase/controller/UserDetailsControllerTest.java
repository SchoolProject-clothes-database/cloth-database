package se.iths.clothdatabase.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.clothdatabase.entity.UserDetailsEntity;
import se.iths.clothdatabase.repository.RoleRepository;
import se.iths.clothdatabase.security.WebSecurityConfig;
import se.iths.clothdatabase.service.UserDetailsService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserDetailsController.class)
@Import({WebSecurityConfig.class})
class UserDetailsControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserDetailsService userDetailService;

    @MockBean
    private RoleRepository roleRepository;

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "ADMIN")
    void creatNewUserDetailsShouldReturnStatus201() throws Exception {
        var userDetail = new UserDetailsEntity();
        userDetail.setFirstName("FirstName");
        userDetail.setLastName("LastName");
        userDetail.setEmail("Email@test");
        when(userDetailService.createUserDetail(any(UserDetailsEntity.class))).thenReturn(userDetail);

        mvc.perform(post("/userDetails").contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                	"firstName":"FirstName",
                                 	"lastName":"LastName",
                                 	"email":"Email@test"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("FirstName"))
                .andExpect(jsonPath("$.lastName").value("LastName"))
                .andExpect(jsonPath("$.email").value("Email@test"));
    }

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "ADMIN")
    void getUserDetailsByIdShouldReturnStatus200() throws Exception {
        mvc.perform(get("/userDetails/find/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "ADMIN")
    void getAllUserDetailsShouldReturnStatus200() throws Exception {
        mvc.perform(get("/userDetails/findAll"))
                .andExpect(status().isOk());
    }
}