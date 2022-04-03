package se.iths.clothdatabase.exception.product;

public class ProductIsNotInStockException extends Exception{
    public ProductIsNotInStockException(String message) {
        super(message);
    }
}
