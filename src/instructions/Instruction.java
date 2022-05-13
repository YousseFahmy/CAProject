package instructions;

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
    
}
