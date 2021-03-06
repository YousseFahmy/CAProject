package memory;

import java.util.ArrayList;

public class RegisterFile {

    private static RegisterFile instance;

    private ArrayList<Register> registers;

    private RegisterFile(){
        this.registers = new ArrayList<>(33);
        
        for(int i = 0; i < 33; i++){
            registers.add(new Register());
        }
    }

    public static RegisterFile getInstance(){
        if(instance == null) instance = new RegisterFile();
        return instance;
    }

    public void printContent(){
        System.out.println("PC: " + getRegisterDecimalContent(32));
        for(int i = 0; i < 32; i++){
            System.out.println("R"+i+": " + getRegisterDecimalContent(i));
        }
    }

    public int getRegisterDecimalContent(int registerNumber){
        return registers.get(registerNumber).getValue();
    }

    public String getRegisterBinaryContent(int registerNumber){
        return registers.get(registerNumber).getBinaryValue();
    }

    public void setRegisterContent(int registerNumber, int decimalValue){
        registers.get(registerNumber).setValue(decimalValue);
    }
}
