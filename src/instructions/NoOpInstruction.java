package instructions;

import memory.Instruction;

public class NoOpInstruction extends RTypeInstruction {

    public static final int NO_OP_INSTRUCTION_BINARY = 0b0000_00000_00001_00000_0000000000000;

    private NoOpInstruction(Instruction instruction) {
        super(instruction);
    }

    public static NoOpInstruction get(){
        return new NoOpInstruction(new Instruction(NO_OP_INSTRUCTION_BINARY));
    }

    @Override
    public int execute() {
        return 0;
    }
    
}
