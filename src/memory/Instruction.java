package memory;

import exceptions.FilletException;

public class Instruction extends Word{

    public Instruction(int decimalValue) {
        super(decimalValue);
    }

    public Instruction(String binaryValue){
        super(binaryValue);
    }

    public String getOpcode(){
        return super.binaryContent.substring(0, 4);
    }

    public int execute(){
        throw new FilletException();
    }
    
}
