package instructions;

public abstract class RTypeInstruction extends Instruction {

    private int r1;
    private int r2;
    private int r3;
    private int shamt;

    public RTypeInstruction(Instruction instruction) {
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

    public int getR3() {
        return r3;
    }

    public void setR3(int r3) {
        this.r3 = r3;
    }

    public int getShamt() {
        return shamt;
    }

    public void setShamt(int shamt) {
        this.shamt = shamt;
    }
    

}