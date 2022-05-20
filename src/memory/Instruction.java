package memory;

import exceptions.FilletException;

public class Instruction extends Word{

    public Instruction(int decimalValue) {
        super(decimalValue);
    }

    public Instruction(String binaryValue){
        super(binaryValue);
    }

    public int getOpcode(){
        int opCodeMask = 0b11110000000000000000000000000000;
        int opcode = (super.getDecimalContent() & opCodeMask) >> 28;
        return opcode;
    }

    public int execute(){
        throw new FilletException();
    }
    
}
