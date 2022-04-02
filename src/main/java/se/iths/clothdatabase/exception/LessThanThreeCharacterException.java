package se.iths.clothdatabase.exception;

public class LessThanThreeCharacterException extends Exception{
    public LessThanThreeCharacterException(String message) {
        super(message);
    }
}
