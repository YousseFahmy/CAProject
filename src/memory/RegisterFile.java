package memory;

public class RegisterFile {
    
    private static final String registerFilePath = "";

    private static RegisterFile instance;

    private RegisterFile(){
        // instantiaste registers
        // read registerFile contents
        // 
    }

    public static RegisterFile getInstance(){
        if(instance == null) instance = new RegisterFile();
        return instance;
    }

    public int getRegisterDecimalContent(int registerNumber){
        return 0; //TODO
    }

    public String getRegisterBinaryContent(int registerNumber){
        return ""; //TODO
    }

    public void setRegisterContent(int registerNumber, int decimalValue){
        // TODO
    }

    public void setRegisterContent(int registerNumber, String binaryValue){
        // TODO
    }

}
