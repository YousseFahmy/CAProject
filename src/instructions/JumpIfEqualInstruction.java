package instructions;

import memory.Instruction;

public class JumpIfEqualInstruction extends ITypeInstruction{

    public JumpIfEqualInstruction(Instruction instruction) {
        super(instruction);
    }

    @Override
    public int execute() {
        return this.getR1() == this.getR2() ? this.getImmediate() : 0;
    }
    
}
