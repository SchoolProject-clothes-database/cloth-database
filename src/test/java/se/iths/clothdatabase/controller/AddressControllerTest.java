package se.iths.clothdatabase.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.clothdatabase.entity.AddressEntity;
import se.iths.clothdatabase.repository.RoleRepository;
import se.iths.clothdatabase.security.WebSecurityConfig;
import se.iths.clothdatabase.service.AddressService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AddressController.class)
@Import({WebSecurityConfig.class})
class AddressControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AddressService addressService;

    @MockBean
    private RoleRepository roleRepository;

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "ADMIN")
    void creatNewAddressShouldReturnStatus201() throws Exception {
        var address = new AddressEntity();
        address.setStreet("street");
        address.setZipCode(12345);
        address.setHouseNumber(10);
        address.setCountry("country");
        address.setProvince("province");
        address.setCity("city");
        address.setPhoneNumber("phoneNumber");
        when(addressService.createAddress(any(AddressEntity.class))).thenReturn(address);


        mvc.perform(post("/address").contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {      
                  "street":"street",
                  "zipCode":12345,
                  "houseNumber":10,
                  "country":"country",
                  "province":"province",
                  "city":"city",
                  "phoneNumber":"phoneNumber"
                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.street").value("street"))
                .andExpect(jsonPath("$.zipCode").value(12345))
                .andExpect(jsonPath("$.houseNumber").value(10))
                .andExpect(jsonPath("$.country").value("country"))
                .andExpect(jsonPath("$.province").value("province"))
                .andExpect(jsonPath("$.city").value("city"))
                .andExpect(jsonPath("$.phoneNumber").value("phoneNumber"));
    }

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "ADMIN")
    void getAddressByIdShouldReturnStatus200() throws Exception {
        mvc.perform(get("/address/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "ADMIN")
    void getAllAddressShouldReturnStatus200() throws Exception {
        mvc.perform(get("/address/findAll"))
                .andExpect(status().isOk());
    }
}