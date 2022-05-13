package instructions;

import memory.Instruction;

public class AndInstruction extends RTypeInstruction{

    public AndInstruction(Instruction instruction) {
        super(instruction);
    }

    @Override
    public int execute() {
        return this.getR2() & this.getR1();
    }

    
    
}
