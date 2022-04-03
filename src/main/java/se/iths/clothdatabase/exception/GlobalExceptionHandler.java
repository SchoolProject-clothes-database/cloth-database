package se.iths.clothdatabase.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError){
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler({NotEnoughMoneyException.class})
    public ResponseEntity<Object> notEnoughMoneyException(NotEnoughMoneyException exception){
        logger.info(exception.getClass().getName());
        String errorMessage = "Not enough money!";

        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, errorMessage,exception));
    }
    @ExceptionHandler({ProductIsNotInStockException.class})
    public ResponseEntity<Object> productIsNotInStockException(ProductIsNotInStockException exception){
        logger.info(exception.getClass().getName());
        String errorMessage = "Out of stock";

        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, errorMessage,exception));
    }

    @ExceptionHandler({LessThanThreeCharacterException.class})
    public ResponseEntity<Object> lessThanThreeCharacterException (LessThanThreeCharacterException exception){
        logger.info(exception.getClass().getName());
        String errorMessage = "Needs to be longer than 3 characters";

        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, errorMessage,exception));
    }

    @ExceptionHandler({PriceIsMoreThanZeroException.class})
    public ResponseEntity<Object> priceIsMoreThanZeroException (PriceIsMoreThanZeroException exception){
        logger.info(exception.getClass().getName());
        String errorMessage = "Price can't be less than 0";

        return buildResponseEntity(new ApiError(HttpStatus.NOT_ACCEPTABLE, errorMessage,exception));
    }

    @ExceptionHandler({IncorrectZIPCodeException.class})
    public ResponseEntity<Object> incorrectZIPCodeException (IncorrectZIPCodeException exception){
        logger.info(exception.getClass().getName());
        String errorMessage = "ZIP code needs to be 5 digits";

        return buildResponseEntity(new ApiError(HttpStatus.NOT_ACCEPTABLE, errorMessage,exception));
    }



    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("Error: ", ex);
        String errorMessage = "An unexpected error occurred.";

        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, ex));
    }


}
