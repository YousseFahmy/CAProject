package instructions;

import memory.Instruction;

public class LogicalShiftLeftInstruction extends RTypeInstruction{

    public LogicalShiftLeftInstruction(Instruction instruction) {
        super(instruction);
    }

    @Override
    public int execute() {
        return this.getR2() << this.getShamt();
    }
    
}
