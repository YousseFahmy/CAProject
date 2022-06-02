package instructions;

import memory.Instruction;

public class MoveToMemoryInstruction extends ITypeInstruction{

    public MoveToMemoryInstruction(Instruction instruction) {
        super(instruction);
        this.needsMemory = true;
    }

    @Override
    public int execute() {
        this.executionResult = this.getR1Contents();
        return this.executionResult;
    }
    
}
