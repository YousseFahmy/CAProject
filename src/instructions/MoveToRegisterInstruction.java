package instructions;

import memory.Instruction;

public class MoveToRegisterInstruction extends ITypeInstruction{

    public MoveToRegisterInstruction(Instruction instruction) {
        super(instruction);
        this.needsMemory = true;
        this.needsWriteBack = true;
    }

    @Override
    public int execute() {
        this.executionResult = this.getR2Contents() + this.getImmediate();
        return this.executionResult;
    }
    
}
