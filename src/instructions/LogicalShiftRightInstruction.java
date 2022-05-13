package instructions;

import memory.Instruction;

public class LogicalShiftRightInstruction extends RTypeInstruction{

    public LogicalShiftRightInstruction(Instruction instruction) {
        super(instruction);
    }

    @Override
    public int execute() {
        return this.getR2() >> this.getShamt();
    }
    
}
