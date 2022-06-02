package instructions;

import memory.Instruction;

public class NoOpInstruction extends RTypeInstruction {

    private static int noOpInstruction = 0b0000_00000_00000_00000_0000000000000;

    private NoOpInstruction(Instruction instruction) {
        super(instruction);
    }

    public static NoOpInstruction get(){
        return new NoOpInstruction(new Instruction(noOpInstruction));
    }

    @Override
    public int execute() {
        return 0;
    }
    
}
