package se.iths.clothdatabase.exception;

public class ProductIsNotInStockException extends Exception{
    public ProductIsNotInStockException(String message) {
        super(message);
    }
}
