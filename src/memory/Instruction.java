package memory;

import exceptions.FilletException;

public class Instruction extends Word {
    protected int executionResult;
    protected boolean needsWriteBack;
    protected boolean needsMemory;

    public Instruction(int decimalValue) {
        super(decimalValue);
        this.needsMemory = false;
        this.needsWriteBack = false;
    }

    public Instruction(String binaryValue) {
        super(binaryValue);
        this.needsMemory = false;
        this.needsWriteBack = false;
    }

    public int getOpcode() {
        int opCodeMask = 0b11110000000000000000000000000000;
        int opcode = (super.getDecimalContent() & opCodeMask) >>> 28;
        return opcode;
    }

    public int execute() {
        throw new FilletException();
    }

    public void printParameters(){
        throw new FilletException();
    }

    public int getExecutionResult(){
        return this.executionResult;
    }

    public void setExecutionResult(int newValue){
        this.executionResult = newValue;
    }

    public boolean needsWriteBack(){
        return needsWriteBack;
    }

    public boolean needsMemory(){
        return needsMemory;
    }

}
