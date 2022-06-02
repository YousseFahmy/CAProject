package instructions;

import memory.Instruction;
import translators.Decoder;

public class LogicalShiftRightInstruction extends RTypeInstruction{

    public LogicalShiftRightInstruction(Instruction instruction) {
        super(instruction);
        this.needsWriteBack = true;
    }

    @Override
    public int execute() {
        this.executionResult = this.getR2Contents() >> this.getShamt();
        Decoder.setLastInstruction(this.getR1(), this.executionResult);
        return this.executionResult;
    }
    
}
