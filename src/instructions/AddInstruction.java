package instructions;

import memory.Instruction;

public class AddInstruction extends RTypeInstruction{

    public AddInstruction(Instruction instruction) {
        super(instruction);
        this.needsWriteBack = true;

    }

    @Override
    public int execute(){
        this.executionResult = this.getR2Contents() + this.getR3Contents();
        return this.executionResult;
    }
    
}
