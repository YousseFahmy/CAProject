package instructions;

public abstract class ITypeInstruction extends Instruction {

    private int r1;
    private int r2;
    private int immediate;

    public ITypeInstruction(Instruction instruction) {
        super(instruction.getBinaryContent());
    }

    public abstract int execute();

    public int getR1() {
        return r1;
    }

    public void setR1(int r1) {
        this.r1 = r1;
    }

    public int getR2() {
        return r2;
    }

    public void setR2(int r2) {
        this.r2 = r2;
    }

    public int getImmediate() {
        return immediate;
    }

    public void setImmediate(int immediate) {
        this.immediate = immediate;
    }

    

}
