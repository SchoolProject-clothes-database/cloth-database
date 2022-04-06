package se.iths.clothdatabase.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import se.iths.clothdatabase.entity.ProductEntity;
import se.iths.clothdatabase.exception.product.PriceIsLessThanZeroException;
import se.iths.clothdatabase.service.ProductService;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProductExceptionsTest {

    @Autowired
    ProductService productService;

    @Test
    void priceIsMoreThanZeroExceptionTest(){
        assertThatThrownBy(() -> productService.createProduct(new ProductEntity("name", -10.0, 2),2L)).isInstanceOf(PriceIsLessThanZeroException.class);
    }
}
