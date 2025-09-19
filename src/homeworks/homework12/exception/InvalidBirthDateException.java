package homeworks.homework12.exception;

public class InvalidBirthDateException extends Exception{

    public InvalidBirthDateException(){
        super();
    }
    public InvalidBirthDateException(String message){
        super(message);
    }
}
