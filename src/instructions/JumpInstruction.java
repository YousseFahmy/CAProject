package instructions;

import memory.Instruction;

public class JumpInstruction extends JTypeInstruction {

    public JumpInstruction(Instruction instruction) {
        super(instruction);
    }

    @Override
    public int execute() {
        this.executionResult = this.getAddress();
        return this.executionResult;
    }

}
