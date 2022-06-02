package instructions;

import memory.Instruction;

public class LogicalShiftLeftInstruction extends RTypeInstruction{

    public LogicalShiftLeftInstruction(Instruction instruction) {
        super(instruction);
        this.needsWriteBack = true;
    }

    @Override
    public int execute() {
        this.executionResult = this.getR2Contents() << this.getShamt();
        return this.executionResult;
    }
    
}
