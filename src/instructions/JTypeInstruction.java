package instructions;

import memory.Instruction;

public abstract class JTypeInstruction extends Instruction {

    private int address;

    public JTypeInstruction(Instruction instruction) {
        super(instruction.getBinaryContent());
    }

    public abstract int execute();

    @Override
    public void printParameters(){
        System.out.println("- Parameters");
        System.out.println("-- Type: " + this.getClass().getSimpleName());
        System.out.println("-- Address: " + address);
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

}
