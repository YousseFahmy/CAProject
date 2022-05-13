package instructions;

public class MoveImmediateInstruction extends ITypeInstruction{

    public MoveImmediateInstruction(Instruction instruction) {
        super(instruction);
    }

    @Override
    public int execute() {
        return this.getImmediate();
    }
    
}
