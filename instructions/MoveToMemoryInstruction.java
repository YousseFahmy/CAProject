package instructions;

public class MoveToMemoryInstruction extends ITypeInstruction{

    public MoveToMemoryInstruction(Instruction instruction) {
        super(instruction);
    }

    @Override
    public int execute() {
        return this.getR1();
    }
    
}
