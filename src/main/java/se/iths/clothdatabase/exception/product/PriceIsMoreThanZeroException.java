package se.iths.clothdatabase.exception.product;

public class PriceIsMoreThanZeroException extends Exception{
    public PriceIsMoreThanZeroException(String message) {
        super(message);
    }
}
