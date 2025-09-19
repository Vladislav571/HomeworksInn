package homeworks.homework12.exception;

public class PhoneNumberFormatException extends RuntimeException{
    public PhoneNumberFormatException(){
        super();
    }
    public PhoneNumberFormatException(String message){
        super(message);
    }
}
