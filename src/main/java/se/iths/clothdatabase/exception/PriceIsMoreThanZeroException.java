package se.iths.clothdatabase.exception;

public class PriceIsMoreThanZeroException extends Exception{
    public PriceIsMoreThanZeroException(String message) {
        super(message);
    }
}
