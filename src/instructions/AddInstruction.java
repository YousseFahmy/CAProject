package instructions;

import memory.Instruction;

public class AddInstruction extends RTypeInstruction{

    public AddInstruction(Instruction instruction) {
        super(instruction);
    }

    @Override
    public int execute(){
        return this.getR2() + this.getR3();
    }
    
}
