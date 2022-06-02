package instructions;

import memory.Instruction;
import translators.Decoder;

public class AndInstruction extends RTypeInstruction{

    public AndInstruction(Instruction instruction) {
        super(instruction);
        this.needsWriteBack = true;
    }

    @Override
    public int execute() {
        this.executionResult = this.getR2Contents() & this.getR3Contents();
        Decoder.setLastInstruction(this.getR1(), this.executionResult);
        return this.executionResult;
    }

    
    
}
