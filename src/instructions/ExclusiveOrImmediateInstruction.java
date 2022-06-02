package instructions;

import memory.Instruction;

public class ExclusiveOrImmediateInstruction extends ITypeInstruction{

    public ExclusiveOrImmediateInstruction(Instruction instruction) {
        super(instruction);
        this.needsWriteBack = true;
    }

    @Override
    public int execute() {
        this.executionResult = this.getR2Contents() ^ this.getImmediate();
        return this.executionResult;
    }
    
}
