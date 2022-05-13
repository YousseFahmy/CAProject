package instructions;

import memory.Instruction;

public class MoveToRegisterInstruction extends ITypeInstruction{

    public MoveToRegisterInstruction(Instruction instruction) {
        super(instruction);
    }

    @Override
    public int execute() {
        return this.getR2() + this.getImmediate();
    }
    
}
