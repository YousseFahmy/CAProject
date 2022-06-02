package instructions;

import memory.Instruction;

public class LogicalShiftRightInstruction extends RTypeInstruction{

    public LogicalShiftRightInstruction(Instruction instruction) {
        super(instruction);
        this.needsWriteBack = true;
    }

    @Override
    public int execute() {
        this.executionResult = this.getR2Contents() >> this.getShamt();
        return this.executionResult;
    }
    
}
