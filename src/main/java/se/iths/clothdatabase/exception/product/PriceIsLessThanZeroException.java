package se.iths.clothdatabase.exception.product;

public class PriceIsLessThanZeroException extends Exception{
    public PriceIsLessThanZeroException(String message) {
        super(message);
    }
}
