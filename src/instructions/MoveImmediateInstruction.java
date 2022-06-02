package instructions;

import memory.Instruction;
import translators.Decoder;

public class MoveImmediateInstruction extends ITypeInstruction{

    public MoveImmediateInstruction(Instruction instruction) {
        super(instruction);
        this.needsWriteBack = true;
    }

    @Override
    public int execute() {
        this.executionResult = this.getImmediate();
        Decoder.setLastInstruction(this.getR1(), this.executionResult);
        return this.executionResult;
    }
    
}
