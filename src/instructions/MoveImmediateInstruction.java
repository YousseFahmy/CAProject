package instructions;

import memory.Instruction;

public class MoveImmediateInstruction extends ITypeInstruction{

    public MoveImmediateInstruction(Instruction instruction) {
        super(instruction);
        this.needsWriteBack = true;
    }

    @Override
    public int execute() {
        this.executionResult = this.getImmediate();
        return this.executionResult;
    }
    
}
