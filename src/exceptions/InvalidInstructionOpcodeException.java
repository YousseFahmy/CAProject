package exceptions;

public class InvalidInstructionOpcodeException extends FilletException {
    
    public InvalidInstructionOpcodeException(){
        super();
    }
    
    public InvalidInstructionOpcodeException(String message){
        super(message);
    }
}
