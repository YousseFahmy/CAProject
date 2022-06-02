package instructions;

import memory.Instruction;

public class JumpIfEqualInstruction extends ITypeInstruction{

    public JumpIfEqualInstruction(Instruction instruction) {
        super(instruction);
    }

    @Override
    public int execute() {
        this.executionResult = this.getR1Contents() == this.getR2Contents() ? this.getImmediate() : 0;
        return this.executionResult;
    }
    
}
