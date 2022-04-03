package se.iths.clothdatabase.exception.user;

public class LessThanThreeCharacterException extends Exception{
    public LessThanThreeCharacterException(String message) {
        super(message);
    }
}
