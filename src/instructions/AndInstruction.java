package instructions;

import memory.Instruction;

public class AndInstruction extends RTypeInstruction{

    public AndInstruction(Instruction instruction) {
        super(instruction);
        this.needsWriteBack = true;
    }

    @Override
    public int execute() {
        this.executionResult = this.getR2Contents() & this.getR1Contents();
        return this.executionResult;
    }

    
    
}
