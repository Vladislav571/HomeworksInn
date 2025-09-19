package homeworks.homework12.exception;

public class EmptyFieldException extends Exception{
    public EmptyFieldException(){
        super();
    }
    public EmptyFieldException(String message){
        super(message);
    }
}
