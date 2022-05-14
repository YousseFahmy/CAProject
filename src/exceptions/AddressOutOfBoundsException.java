package exceptions;

public class AddressOutOfBoundsException extends FilletException{
    public AddressOutOfBoundsException(){
        super();
    }
    
    public AddressOutOfBoundsException(String message){
        super(message);
    }
}
