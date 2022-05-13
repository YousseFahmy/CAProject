package instructions;

public class SubtractInstruction extends RTypeInstruction {

    public SubtractInstruction(Instruction instruction) {
        super(instruction);
    }

    @Override
    public int execute() {
        return this.getR2() - this.getR3();
    }

}
