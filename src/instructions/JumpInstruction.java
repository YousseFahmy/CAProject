package instructions;

public class JumpInstruction extends JTypeInstruction {

    public JumpInstruction(Instruction instruction) {
        super(instruction);
    }

    @Override
    public int execute() {
        return this.getAddress();
    }

}
