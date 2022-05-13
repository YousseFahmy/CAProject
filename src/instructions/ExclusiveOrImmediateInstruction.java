package instructions;

import memory.Instruction;

public class ExclusiveOrImmediateInstruction extends ITypeInstruction{

    public ExclusiveOrImmediateInstruction(Instruction instruction) {
        super(instruction);
    }

    @Override
    public int execute() {
        return this.getR2() ^ this.getImmediate();
    }
    
}
