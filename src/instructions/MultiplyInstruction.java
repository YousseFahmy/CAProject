package instructions;

import memory.Instruction;

public class MultiplyInstruction extends RTypeInstruction{

    public MultiplyInstruction(Instruction instruction) {
        super(instruction);
    }

    @Override
    public int execute() {
        return this.getR2() * this.getR3();
    }
    
}
