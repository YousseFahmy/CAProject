package instructions;

public abstract class JTypeInstruction extends Instruction {

    private int address;

    public JTypeInstruction(Instruction instruction) {
        super(instruction.getBinaryContent());
    }

    public abstract int execute();

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

}
