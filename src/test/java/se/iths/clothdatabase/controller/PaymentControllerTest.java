package se.iths.clothdatabase.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.clothdatabase.entity.PaymentEntity;
import se.iths.clothdatabase.repository.RoleRepository;
import se.iths.clothdatabase.security.WebSecurityConfig;
import se.iths.clothdatabase.service.PaymentService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PaymentController.class)
@Import({WebSecurityConfig.class})
class PaymentControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PaymentService paymentService;

    @MockBean
    private RoleRepository roleRepository;

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "ADMIN")
    void creatNewPaymentShouldReturnStatus201() throws Exception {
        var payment = new PaymentEntity();
        payment.setAmount(1000.00);
        when(paymentService.createPayment(any(PaymentEntity.class))).thenReturn(payment);

        mvc.perform(post("/payment").contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                	"amount" : 1000.00
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.amount").value(1000));
    }

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "ADMIN")
    void getPaymentByIdShouldReturnStatus200() throws Exception {
        mvc.perform(get("/payment/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "ADMIN")
    void getAllPaymentShouldReturnStatus200() throws Exception {
        mvc.perform(get("/payment/findAll"))
                .andExpect(status().isOk());
    }
}